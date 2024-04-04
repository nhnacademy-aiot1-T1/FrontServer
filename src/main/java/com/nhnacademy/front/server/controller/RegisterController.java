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

/**
 * 유저의 회원가입 로직을 처리하는 컨트롤러 클래스 입니다!
 * @author AoiTuNa
 * @version 1.0
 * @see #showRegisterForm() 회원가입 폼을 출력하는 메서드 입니다!
 * @see #doRegister(RegisterRequestDto, Model, RedirectAttributes) 회원가입을 최종 처리하는 메서드 입니다! 다양한 실패조건과 성공에 대한 처리가 들어있습니다!
 */
@Controller
@RequiredArgsConstructor
public class RegisterController {

  private final RegisterService registerService;

  /**
   * 회원가입 폼을 출력합니다!
   * @return 회원가입 페이지를 리턴합니다!
   */
  @GetMapping("/register")
  public String showRegisterForm(){
    return "/pages/auth/register";
  }

  /**
   * 유저의 회원가입을 처리하고 다양한 상황에 대응하는 메서드입니다!
   * @param registerRequestDto 유저가 입력한 정보가 담겨있는 domain class입니다!
   * @param model 실패한 이유를 회원가입 화면에 출력합니다!
   * @param redirectAttributes 성공을 알리기 위한 새 화면에 정보를 보내기 위해 사용합니다!
   * @return 실패하면 회원가입 창을, 성공하면 성공을 알리는 alert를 출력하는 page로 리다이렉트 합니다!
   * @see RegisterRequestDto
   */
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
    return "redirect:pages/auth/registerSuccess";
  }
}
