package com.nhnacademy.front.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

  @GetMapping("/register")
  public String showRegisterForm(){
    return "pages/auth/register";
  }
  @PostMapping("/register")
  public String doRegister(){
    return null;
  }
}
