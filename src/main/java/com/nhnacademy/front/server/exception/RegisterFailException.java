package com.nhnacademy.front.server.exception;

/**
 * 회원가입에 실패하면 던지는 예외 입니다 메시지에 이유가 담겨 있습니다!
 * @author AoiTuNA
 * @version 1.0
 */
public class RegisterFailException extends RuntimeException{

  public RegisterFailException(String message) {
    super(message);
  }
}
