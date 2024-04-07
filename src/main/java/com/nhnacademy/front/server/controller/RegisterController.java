package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.domain.register.RegisterCheckDto;
import com.nhnacademy.front.server.domain.register.RegisterRequestDto;
import com.nhnacademy.front.server.domain.register.ValidationResult;
import com.nhnacademy.front.server.exception.RegisterFailException;
import com.nhnacademy.front.server.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * @see #doRegister(RegisterCheckDto, Model, RedirectAttributes) 회원가입을 최종 처리하는 메서드 입니다! 다양한 실패조건과 성공에 대한 처리가 들어있습니다!
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class RegisterController {
  private static final String REGISTER_PAGE = "/pages/auth/register";
  private static final String REASON_MESSAGE = "message";

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
   * @param registerCheckDto 유저가 입력한 정보가 담겨있는 domain class입니다!
   * @param model 실패한 이유를 회원가입 화면에 출력합니다!
   * @param redirectAttributes 성공을 알리기 위한 새 화면에 정보를 보내기 위해 사용합니다!
   * @return 실패하면 회원가입 창을, 성공하면 성공을 알리는 alert를 출력하는 page로 리다이렉트 합니다!
   * @see RegisterCheckDto
   */
  @PostMapping("/register")
  public String doRegister(@ModelAttribute RegisterCheckDto registerCheckDto, Model model, RedirectAttributes redirectAttributes){
    log.debug("회원가입 로직 실행");
    ValidationResult validationResult = registerService.validationRegisterRequest(registerCheckDto);
    if(!validationResult.isValid()){
      model.addAttribute(REASON_MESSAGE, validationResult.getMessage());
      return REGISTER_PAGE;
    }
    RegisterRequestDto registerRequestDto = new RegisterRequestDto(registerCheckDto.getId(),registerCheckDto.getPassword(),registerCheckDto.getName(),registerCheckDto.getEmail());
    try{
      registerService.registerCreated(registerRequestDto);
    }catch (RegisterFailException e){
      log.warn(e.getMessage());
      model.addAttribute(REASON_MESSAGE, e.getMessage());
      return REGISTER_PAGE;
    }
  redirectAttributes.addFlashAttribute(REASON_MESSAGE,"회원가입이 완료되었습니다!");
    //Todo 임시로 연결하는 페이지 modal 적용 검토
    return "redirect:pages/auth/registerSuccess";
  }
}
