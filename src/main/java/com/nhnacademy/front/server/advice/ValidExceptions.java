package com.nhnacademy.front.server.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

/**
 * 회원가입 부분의 입력 값이 validation을 통과하지 않았을 때 발생하는 예외를 처리하는 advice입니다!
 * @author AoiTuNa
 * @version 1.0
 * @see #handelMethodArgumentNotValid(BindException, Model)
 */
@ControllerAdvice
@Slf4j
public class ValidExceptions {

  /**
   * 입력된 값이 spring validation에 적합하지 않았을 때 발생하는 예외를 처리합니다!
   * @param ex 예외의 대한 정보를 가지고 있습니다!
   * @param model 전달된 페이지에 정보를 주기 위한 모델입니다!
   * @return 전달할 페이지를 리턴합니다!
   */
  @ExceptionHandler(BindException.class)
  public String handelMethodArgumentNotValid(BindException ex, Model model){
    if(ex.getBindingResult().getObjectName().equals("registerCheckDto")){
      model.addAttribute("message", Objects.requireNonNull(ex.getFieldError()).getDefaultMessage());
      return "pages/auth/register";
    }
    log.warn(ex.getMessage());
    log.warn(ex.getBindingResult().toString());
    return null;
  }
}
