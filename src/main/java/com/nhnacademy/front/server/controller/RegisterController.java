package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.domain.register.CreateRegisterRequestDto;
import com.nhnacademy.front.server.domain.register.RegisterRequestDto;
import com.nhnacademy.front.server.domain.register.ValidationResult;
import com.nhnacademy.front.server.exception.RegisterFailException;
import com.nhnacademy.front.server.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class RegisterController {

  private final RegisterService registerService;

  @GetMapping("/register")
  public String showRegisterForm(){
    return "/pages/auth/register";
  }
  @PostMapping("/register")
  public String doRegister(@ModelAttribute RegisterRequestDto registerRequestDto, Model model, RedirectAttributes redirectAttributes){
    ValidationResult validationResult = registerService.validationRegisterRequest(registerRequestDto);
    if(!validationResult.isValid()){
      model.addAttribute("failedResult", validationResult.getMessage());
      return "pages/auth/register";
    }
    CreateRegisterRequestDto createRegisterRequestDto = new CreateRegisterRequestDto(registerRequestDto.getId(),registerRequestDto.getPassword());
    try{
      registerService.isCreated(createRegisterRequestDto);
    }catch (RegisterFailException e){
      model.addAttribute("failedResult", e.getMessage());
      return "pages/auth/register";
    }
  redirectAttributes.addFlashAttribute("successMessage","회원가입이 완료되었습니다!");
    return "redirect::pages/auth/registerSuccess";
  }
}
