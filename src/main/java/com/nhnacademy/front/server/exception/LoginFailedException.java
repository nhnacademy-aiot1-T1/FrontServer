package com.nhnacademy.front.server.exception;

import lombok.Getter;

/**
 * 로그인에 실패했을 때 유저에게 전달하기 위한 메시지를 전달하는 예외 입니다!
 * @author AoiTuNa
 * @version 1.0
 */
@Getter
public class LoginFailedException extends RuntimeException{

  private final String originClass;
  public LoginFailedException(String message,String originClass) {
    super(message);
    this.originClass = originClass;
  }
}
