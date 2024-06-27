package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.dto.register.RegisterCheckDto;
import com.nhnacademy.front.server.dto.register.RegisterRequestDto;
import com.nhnacademy.front.server.exception.RegisterFailException;
import com.nhnacademy.front.server.service.AuthService;
import com.nhnacademy.front.server.util.WebUtils;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
@Slf4j
public class RegisterController {

  private static final String REGISTER_PAGE = "register";
  private static final String REASON_ATTRIBUTE_NAME = "Message";

  private final AuthService authService;

  /**
   * 회원가입 페이지로 이동합니다.
   */
  @GetMapping
  public String showRegisterForm() {
    return REGISTER_PAGE;
  }

  @PostMapping
  public String doRegister(@ModelAttribute @Valid RegisterCheckDto registerCheckDto,
      Model model,
      RedirectAttributes redirectAttributes) {
    if (!registerCheckDto.getPassword().equals(registerCheckDto.getPasswordRetype())) {
      model.addAttribute(REASON_ATTRIBUTE_NAME, "pw와 pw 확인이 일치하지 않습니다!");
      throw new RegisterFailException("pw와 pw 확인이 일치하지 않습니다!");
    }

    RegisterRequestDto registerRequestDto = new RegisterRequestDto();
    BeanUtils.copyProperties(registerCheckDto, registerRequestDto);

    authService.registerUser(registerRequestDto);
    redirectAttributes.addFlashAttribute(REASON_ATTRIBUTE_NAME, "회원가입이 완료되었습니다!");

    return WebUtils.REDIRECT_PREFIX + "login";
  }
}
