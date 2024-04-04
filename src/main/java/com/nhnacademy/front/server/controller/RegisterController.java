package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.domain.register.RegisterRequestDto;
import com.nhnacademy.front.server.domain.register.ValidationResult;
import com.nhnacademy.front.server.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegisterController {

  private final RegisterService registerService;

  @GetMapping("/register")
  public String showRegisterForm(){
    return "pages/auth/register";
  }
  @PostMapping("/register")
  public String doRegister(@ModelAttribute RegisterRequestDto registerRequestDto, Model model){
    ValidationResult validationResult = registerService.validationRegisterRequest(registerRequestDto);
    if(!validationResult.isValid()){
      model.addAttribute("failedResult", validationResult.getMessage());
      return "pages/auth/register";
    }

    return "pages/main/index";
  }
}
