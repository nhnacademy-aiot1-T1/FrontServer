package com.nhnacademy.front.server.advice;

import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
@Slf4j
public class HttpExceptions {

  @ExceptionHandler(value = {HttpClientErrorException.class})
  public String clientErrorState(HttpServletRequest req, HttpServletResponse res, HttpClientErrorException exception, RedirectAttributes redirectAttributes){
    if(exception.getStatusCode() == HttpStatus.FORBIDDEN){
      Cookie[] cookies = req.getCookies();
      if(Arrays.stream(cookies).filter(cookie -> "authorization".equals(cookie.getName()))
          .findFirst().isEmpty()){
        redirectAttributes.addFlashAttribute("error","don't have authorization token");
        return "redirect:/pages/auth/login";
      }
    }
    return "redirect:/pages/auth/login";
  }

}
