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

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @GetMapping("/loginpage")
    public String showLoginForm(){return "pages/auth/login";}

    //Id -> email 상황따라 변경
    @PostMapping("/login")
    public String doLogin(@RequestParam("email")String email,
                          @RequestParam("password")String password,
                            HttpServletResponse res, Model model){
        UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto(email,password);
        JwtToken token = authService.getLoginToken(userLoginRequestDto).orElse(null);
        if(token==null){
            //Todo 일단 실패시 상황을 전달 할 수 있는가?
            return "redirect:pages/auth/login";
        }
        String accessToken = token.getAccessToken();
        Cookie cookie = new Cookie("authorization",accessToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        res.addCookie(cookie);
        return "pages/main/index";
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
                return "pages/error/403";
            }else{
                //Todo sendToken의 return 타입의 관해... 그리고 값에 따른 처리
                authService.tokenLogout(token);
            }

        }
      return "redirect:pages/auth/login";
    }

}
