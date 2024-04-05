package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.service.AuthService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 인증서버와 관련된 api를 처리하는 controller입니다 각각 해당하는 위치의 html파일을 반환합니다!
 * @author AoiTuNa
 * @version 1.1
 * @see #showLoginForm()
 * @see #doLogin(String, String, HttpServletRequest ,HttpServletResponse, RedirectAttributes)
 * @see #doLogout(String, RedirectAttributes)
 */
@Controller
@Slf4j
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;


    /**
     * 해당 request 와 Uri pattern이 오면 해당 controller을 호출합니다
     * @return 로그인 메인 페이지
     */
    @GetMapping("/login")
    public String showLoginForm(){return "pages/auth/login";}


    //Id -> email 상황따라 변경

    /**
     * 로그인 인증 로직입니다 성공하면 메인으로 실패시 다시 로그인 화면을 redirect합니다!
     * @param id 유저가 입력한 아이디 정보 입니다!
     * @param password 유저가 입력한 비밀번호 정보 입니다!
     * @param req 유저의 IPAddress 정보를 가지고 오기위한 request입니다!
     * @param res 쿠키에 발급받은 토큰을 저장하기 위한 response입니다!
     * @param redirectAttributes 에러 발생시 flash 로 정보를 넘겨주기 위한 attribute입니다!
     * @return 성고하면 메인페이지로 실패하면 로그인 페이지로 반환합니다!
     */
    @PostMapping("/login")
    public String doLogin(@RequestParam("id")String id,
                          @RequestParam("password")String password,
                            HttpServletRequest req,
                            HttpServletResponse res,
                            RedirectAttributes redirectAttributes){
        //Todo 유저의 주소 정보가 들어있는 헤더값 - 키값이 정해지면 설정 하기!!
        String userAddress = req.getHeader("userAddress");
        String token = authService.getLoginToken(id,password,userAddress);
        if(token==null){
            //redirect 해도 1번은 정보가 넘어가는 session 오류정보를 전달함.
            redirectAttributes.addFlashAttribute("error","do not match Id or Password.");
            return "redirect:/pages/auth/login";
        }
        Cookie cookie = new Cookie("authorization",token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        res.addCookie(cookie);
        return "pages/main/index";
    }

    /**
     * 유저가 로그아웃 버튼을 눌렀을 때 호출되는 controller입니다!
     * @param token 유저의 Cookie에서 authorization 쿠키를 가져옵니다!
     * @param redirectAttributes 에러 발생시 flash 로 정보를 넘겨주기 위한 attribute입니다!
     * @return 현재 둘 다 로그인 화면을 반환하지만 실패시 에러에 대한 정보를 flash 로 담아줍니다!
     */
    @PostMapping("/logout")
    public String doLogout(@CookieValue(value = "authorization",required = false)String token,RedirectAttributes redirectAttributes){
        if(token == null || token.isEmpty()){
            log.info("토큰이 없거나 비어있음 ");
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
        authService.tokenLogout(token);
        redirectAttributes.addFlashAttribute("state","success");
      return "redirect:/pages/auth/login";
    }

}
