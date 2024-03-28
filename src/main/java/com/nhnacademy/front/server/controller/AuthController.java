package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.domain.UserLoginResponseDto;
import com.nhnacademy.front.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @GetMapping("/login")
    public String showLoginForm(){return "pages/auth/login";}

    @PostMapping("/login")
    public String doLogin(HttpServletResponse res){
        UserLoginResponseDto token = authService.getToken().orElse(null);
        String jwt = token.getAccessToken();
        if(jwt.equals(null)){
            return "redirect:pages/auth/login";
        }
        Cookie cookie = new Cookie("authorization",jwt);
        res.addCookie(cookie);
        return "main";
    }
    @PostMapping("/logout")
    public String doLogout(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            String token = Arrays.stream(cookies)
                    .filter(cookie -> "authorization".equals(cookie.getName()))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
            if(token == null){
                //Todo 비정상적인 접근
            }else{
                //Todo sendToken의 return 타입의 관해... 그리고 값에 따른 처리
                authService.sendToken(token);
            }

        }
        return null;
    }

}
