package com.nhnacademy.front.server.advice;


import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * 4xx번대 요청이 왔을 때 알맞은 처리를 위해서 만들어진 advice입니다!
 * @author AoiTuNa
 * @version 1.1
 * @see #clientErrorState(HttpClientErrorException, RedirectAttributes)
 */
@ControllerAdvice
@Slf4j
public class HttpExceptions {
  private static final String MESSAGE_NAME = "message";

  /**
   * 4xx 번대 에러인 HttpClientException을 받아서 처리하는 advice의 로직입니다!<br/>
   * 400번 badRequest 401번 unauthorized status를 처리합니다!<br/>
   * 각 예외의 message 부분의 값을 기준으로 각 예외의 발생 위치와 처리를 규정합니다!
   * @param exception 해당하는 HttpClientException입니다!
   * @param redirectAttributes 다시 연결된 html page에 flash를 추가하기 위한 코드입니다
   * @return 403 요청이 들어오면 인증되지 않은 코인이라는 메시지를 flash로 보내 login 화면으로 redirect 합니다!
   */
  @ExceptionHandler(value = {HttpClientErrorException.class})
  public String clientErrorState(HttpClientErrorException exception, RedirectAttributes redirectAttributes){
    String responseBody = exception.getResponseBodyAsString();
    JSONObject json = new JSONObject(responseBody);
    String message = json.getString(MESSAGE_NAME);
    if(exception.getStatusCode() == HttpStatus.BAD_REQUEST){
      if(message.equals("이미 토큰 재발급이 이루어진 토큰")){
        redirectAttributes.addFlashAttribute(MESSAGE_NAME,"허용되지 않은 토큰입니다!! 다시 로그인 해주세요!");
        return "redirect:pages/error/403";
      }
    }
    if(exception.getStatusCode() == HttpStatus.UNAUTHORIZED){
      if(message.equals("토큰 IP와 클라이언트 IP 불일치")){
        redirectAttributes.addFlashAttribute(MESSAGE_NAME,message);
        return "redirect:pages/auth/login";
      } else if (message.equals("로그인")) {
        redirectAttributes.addFlashAttribute(MESSAGE_NAME,"해당하는 유저가 존재하지 않습니다! ID, PW를 확인하세요!");
        return "redirect:pages/auth/login";
      }
    }
    //추가
    return "pages/error/403";
  }

}
