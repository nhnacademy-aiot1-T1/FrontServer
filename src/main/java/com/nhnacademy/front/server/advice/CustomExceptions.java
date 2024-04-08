package com.nhnacademy.front.server.advice;

import com.nhnacademy.front.server.exception.JsonParseFailException;
import com.nhnacademy.front.server.exception.LoginFailedException;
import com.nhnacademy.front.server.exception.LogoutNotFoundTokenException;
import com.nhnacademy.front.server.exception.RegisterFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 각각의 커스텀 예외의 대한 설정입니다!
 * @author AoiTuNa
 * @version 1.0
 * @see #jsonParserFailActivePage(JsonParseFailException, Model)
 * @see #logoutFailedReason(LoginFailedException, RedirectAttributes)
 * @see #logoutFailedReason(LoginFailedException, RedirectAttributes)
 */
@ControllerAdvice
@Slf4j
public class CustomExceptions {

  /**
   * json의 파싱에 실패 했을 때 발생하는 예외 처리에 대한 advice 입니다!
   * @param parseFailException 발생한 예외의 정보를 가지고 있습니다!
   * @param model 전달할 페이지에 대한 정보를 담고 있습니다!
   * @return 요청을 전달할 페이지를 로드합니다!
   */
  @ExceptionHandler(value = JsonParseFailException.class)
  public String jsonParserFailActivePage(JsonParseFailException parseFailException, Model model){
    if(parseFailException.getOriginClass().equals("AuthAdapterImpl")){
      model.addAttribute("message",parseFailException.getMessage());
      return "pages/auth/register";
    }
    return null;
  }

  /**
   * 로그인 실패에 대해 발생한 예외를 처리해주는 advice 입니다!
   * @param loginFailedException 발생한 예외의 정보를 담고 있습니다!
   * @param redirectAttributes Redirect 된 페이지에 정보를 전달 하기 위해 사용합니댜!
   * @return 요청을 전달할 페이지를 로드합니다!
   */
  @ExceptionHandler(value = LoginFailedException.class)
  public String showLoginFailedReason(LoginFailedException loginFailedException, RedirectAttributes redirectAttributes){

      redirectAttributes.addFlashAttribute("error",loginFailedException.getMessage());
      return "redirect:pages/auth/login";
  }

  /**
   * 로그아웃 시 토큰의 값이 null이거나 없을 시 발생하는 예외를 처리하는 advice입니다!
   * @param loginFailedException 발생한 예외의 정보를 담고 있습니다!
   * @param redirectAttributes Redirect 된 페이지에 정보를 전달 하기 위해 사용합니댜!
   * @return 요청을 전달할 페이지를 로드합니다!
   */
  @ExceptionHandler(value = LogoutNotFoundTokenException.class)
  public String logoutFailedReason(LoginFailedException loginFailedException,RedirectAttributes redirectAttributes){
    redirectAttributes.addAttribute("error",loginFailedException.getMessage());
    return "redirect:/pages/auth/login";
  }

}
