package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @GetMapping("/login")
    public String showLoginForm(){return "pages/auth/login";}

    @PostMapping("/login")
    public String doLogin(HttpServletResponse res){
        String token = authService.getToken();
        return null;
    }
    @PostMapping("/logout")
    public String doLogout(){
        return null;
    }

}
