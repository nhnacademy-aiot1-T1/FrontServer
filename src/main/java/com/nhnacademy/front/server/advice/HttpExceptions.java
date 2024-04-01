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

/**
 * 4xx번대 요청이 왔을 때 알맞은 처리를 위해서 만들어진 advice입니다!
 * @author AoiTuNa
 * @version 1.0
 * @see #clientErrorState(HttpServletRequest, HttpServletResponse, HttpClientErrorException, RedirectAttributes)
 */
@ControllerAdvice
@Slf4j
public class HttpExceptions {

  /**
   * 4xx 번대 에러인 HttpClientException을 받아서 처리하는 advice의 로직입니다!
   * @param req request 요청입니다!
   * @param res response 요청입니다!
   * @param exception 해당하는 HttpClientException입니다!
   * @param redirectAttributes 다시 연결된 html page에 flash를 추가하기 위한 코드입니다
   * @return 403 요청이 들어오면 인증되지 않은 코인이라는 메시지를 flash로 보내 login 화면으로 redirect 합니다!
   */
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
