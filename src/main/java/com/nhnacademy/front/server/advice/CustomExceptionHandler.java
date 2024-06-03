package com.nhnacademy.front.server.advice;

import com.nhnacademy.front.server.exception.AuthenticationException;
import com.nhnacademy.front.server.exception.AuthorizationException;
import com.nhnacademy.front.server.exception.RegisterFailException;
import com.nhnacademy.front.server.util.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

  /**
   * 로그인 실패 관련 예외 handler 입니다.
   *
   * @param e                  발생한 예외의 정보를 담고 있습니다.
   * @param redirectAttributes Redirect 된 페이지에 정보를 전달 하기 위해 사용합니다.
   * @return 다시 로그인 페이지로 리다이렉트 합니다.
   */

  @ExceptionHandler(value = {AuthenticationException.class})
  public String authenticationExceptionHandler(AuthenticationException e,
      RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("Error", e.getMessage());
    log.info(e.getMessage());

    return WebUtils.REDIRECT_PREFIX + "login";
  }

  @ExceptionHandler(value = {RegisterFailException.class, BindException.class})
  public String handleLoginFailException(Exception e, RedirectAttributes redirectAttributes) {
    String message = (e instanceof BindException) ?
        ((BindException) e).getFieldError().getDefaultMessage() : e.getMessage();

    redirectAttributes.addFlashAttribute("Error", message);
    log.info(e.getMessage());

    return WebUtils.REDIRECT_PREFIX + "register";
  }

  @ExceptionHandler(value = {AuthorizationException.class})
  public String authorizationException(AuthorizationException e) {
    log.info(e.getMessage());

    return WebUtils.REDIRECT_PREFIX + "none";
  }

  /**
   * 모든 예외 handler 입니다.
   *
   * @param e 발생한 예외의 정보를 담고 있습니다.
   * @return 500 error로 이동합니다.
   */
  @ExceptionHandler(value = {Exception.class})
  public String globalExceptionHandler(Exception e) {
    log.error(e.getMessage());

    return "/500.html";
  }
}
