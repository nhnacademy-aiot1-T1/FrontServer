package com.nhnacademy.front.server.advice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 4xx번대 요청이 왔을 때 알맞은 처리를 위해서 만들어진 advice입니다!
 * @author AoiTuNa
 * @version 1.0
 * @see #clientErrorState(String, HttpClientErrorException, RedirectAttributes)
 */
@ControllerAdvice
@Slf4j
public class HttpExceptions {

  /**
   * 4xx 번대 에러인 HttpClientException을 받아서 처리하는 advice의 로직입니다!
   * @param token "authorizaion" 을 값으로 가지는 토큰 입니다!
   * @param exception 해당하는 HttpClientException입니다!
   * @param redirectAttributes 다시 연결된 html page에 flash를 추가하기 위한 코드입니다
   * @return 403 요청이 들어오면 인증되지 않은 코인이라는 메시지를 flash로 보내 login 화면으로 redirect 합니다!
   */
  @ExceptionHandler(value = {HttpClientErrorException.class})
  public String clientErrorState(@CookieValue(value = "authorization",required = false)String token, HttpClientErrorException exception, RedirectAttributes redirectAttributes){
    if(exception.getStatusCode() == HttpStatus.UNAUTHORIZED){
      if(token == null){
        redirectAttributes.addFlashAttribute("error","비 정상적인 사용이 감지되었습니다!");
        return "redirect:/pages/auth/login";
      }
      redirectAttributes.addFlashAttribute("error","로그인에 실패했습니다! Id,Pw를 확인하세요!");
      return "redirect:/pages/auth/login";
    }
    //추가
    return "/pages/error/403";
  }

}
