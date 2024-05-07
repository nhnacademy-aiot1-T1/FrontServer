package com.nhnacademy.front.server.controller;

import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.dto.LoginResponseDto;
import com.nhnacademy.front.server.dto.UserLoginRequestDto;
import com.nhnacademy.front.server.exception.NotFoundTokenException;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.Optional;

/**
 * 인증서버와 관련된 api를 처리하는 controller입니다 각각 해당하는 위치의 html파일을 반환합니다!
 *
 * @author AoiTuNa
 * @version 1.1
 * @see #showLoginForm()
 * @see #doLogout(String, RedirectAttributes)
 */
@Controller
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private static final String LOGIN_PAGE = "/login";
    private static final String INDEX_PAGE = "/home";
    private static final String REDIRECT = "redirect:";

    private final AuthAdapter authAdapter;
    private final RestTemplate restTemplate;


    /**
     * 해당 request 와 Uri가 오면 해당 controller을 호출합니다
     *
     * @return 로그인 메인 페이지
     */
    @GetMapping("/login")
    public String showLoginForm(HttpServletRequest request) {

        if (request.getCookies() == null) return LOGIN_PAGE;

        // todo, util로 빼기.
        Optional<Cookie> token = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("authorization"))
                .findFirst();

        return (token.isEmpty()) ? LOGIN_PAGE : INDEX_PAGE;
    }


    /**
     * 로그인 인증 로직입니다 성공하면 메인으로 실패시 다시 로그인 화면을 redirect합니다!
     *
     * @param req                유저의 IPAddress 정보를 가지고 오기 위한 request입니다!
     * @param res                쿠키에 발급받은 토큰을 저장하기 위한 response입니다!
     * @param redirectAttributes 에러 발생시 flash 로 정보를 넘겨주기 위한 attribute입니다!
     * @return 성공하면 메인페이지로 실패하면 로그인 페이지로 반환합니다!
     */
    @PostMapping("/login")
    public String doLogin(@ModelAttribute UserLoginRequestDto userLoginRequestDto,
                          HttpServletRequest req,
                          HttpServletResponse res,
                          RedirectAttributes redirectAttributes) {

//        String userAddress = req.getHeader("x-forwarded-for");
        String userAddress = "192.168.0.1"; // todo, test용 코드, 지워야 함.
//        String token = authAdapter.login(userLoginRequestDto, userAddress).getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-IP", "userIpAddress");
        HttpEntity<UserLoginRequestDto> requestEntity = new HttpEntity<>(userLoginRequestDto, headers);
        ResponseEntity<CommonResponse<LoginResponseDto>> token = restTemplate.exchange("https://run.mocky.io/v3/af37bcbc-09d6-44de-92f8-87fb1e3a7185", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<>() {
        });

        log.info(token.getBody().getData().getAccessToken());

        if (token == null) {
            redirectAttributes.addFlashAttribute("Error", "do not match Id or Password.");
            return REDIRECT + LOGIN_PAGE;
        }

        Cookie cookie = new Cookie("Authorization", token.getBody().getData().getAccessToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        res.addCookie(cookie);

        return REDIRECT + INDEX_PAGE;
    }

    /**
     * 유저가 로그아웃 버튼을 눌렀을 때 호출되는 controller입니다!
     *
     * @param token              유저의 Cookie에서 authorization 쿠키를 가져옵니다!
     * @param redirectAttributes 에러 발생시 flash 로 정보를 넘겨주기 위한 attribute입니다!
     * @return 현재 둘 다 로그인 화면을 반환하지만 실패시 에러에 대한 정보를 flash 로 담아줍니다!
     */
    @PostMapping("/logout")
    public String doLogout(@CookieValue(value = "Authorization", required = false) String token, RedirectAttributes redirectAttributes) {
        if (token == null || token.isEmpty()) { // logout시 토큰이 없으면 문제가 되는가 ? -
            log.info("토큰이 없거나 비어있음 ");
            throw new NotFoundTokenException("토큰이 없거나 비어있음!");
        }

        authAdapter.logout(token);
        redirectAttributes.addFlashAttribute("State", "success");

        return REDIRECT + LOGIN_PAGE;
    }

    // todo, move to page controller.
    @GetMapping("/")
    public String temp() {
        return "home";

    }
}
