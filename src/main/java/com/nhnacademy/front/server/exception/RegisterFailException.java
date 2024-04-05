package com.nhnacademy.front.server.exception;

import lombok.Getter;

/**
 * 회원가입에 실패하면 던지는 예외 입니다 메시지에 이유가 담겨 있습니다!
 * @author AoiTuNA
 * @version 1.0
 */
@Getter
public class RegisterFailException extends RuntimeException{
  private final String originClass;

  public RegisterFailException(String message,String originClass) {
    super(message);
    this.originClass = originClass;
  }
}
