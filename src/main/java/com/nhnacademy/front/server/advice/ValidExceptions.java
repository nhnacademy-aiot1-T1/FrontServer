package com.nhnacademy.front.server.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ValidExceptions {

  @ExceptionHandler(BindException.class)
  public String handelMethodArgumentNotValid(BindException ex, Model model){
    if(ex.getBindingResult().getObjectName().equals("registerCheckDto")){
      model.addAttribute("message",ex.getMessage());
      return "pages/auth/register";
    }
    log.warn(ex.getMessage());
    log.warn(ex.getBindingResult().toString());
    return null;
  }
}
