package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.domain.JwtToken;
import com.nhnacademy.front.server.domain.UserLoginRequestDto;
import com.nhnacademy.front.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @GetMapping("/login")
    public String showLoginForm(){return "pages/auth/login";}

    //Id -> email 상황따라 변경
    @PostMapping("/login")
    public String doLogin(@RequestParam("email")String email,
                          @RequestParam("password")String password,
                            HttpServletResponse res,
                            RedirectAttributes redirectAttributes){
        UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto(email,password);
        JwtToken token = authService.getLoginToken(userLoginRequestDto).orElse(null);
        if(token==null){
            //redirect 해도 1번은 정보가 넘어가는 session 오류정보를 전달함.
            redirectAttributes.addFlashAttribute("error","do not match Id or Password.");
            return "redirect:/pages/auth/login";
        }
        String accessToken = token.getAccessToken();
        Cookie cookie = new Cookie("authorization",accessToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        res.addCookie(cookie);
        return "pages/main/index";
    }
    @PostMapping("/logout")
    public String doLogout(HttpServletRequest req,Model model,RedirectAttributes redirectAttributes){
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            String token = Arrays.stream(cookies)
                    .filter(cookie -> "authorization".equals(cookie.getName()))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
            if(token == null){
                throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
            }else{
                redirectAttributes.addFlashAttribute("state","success");
                authService.tokenLogout(token);
            }

        }
      return "redirect:/pages/auth/login";
    }

}
