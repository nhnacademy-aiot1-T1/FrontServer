package com.nhnacademy.front.server.exception;

import lombok.Getter;

/**
 * 보낸 요청의 응답이 깨졌거나 json형식을 요청했으나 기대하던 값을 받지 못할때 발생하는 Exception입니다!
 * @author AoiTuNa
 * @version 1.0
 * @see #JsonParseFailException(String, Throwable, String)  JsonParseFailException
 */
@Getter
public class JsonParseFailException extends RuntimeException{
  private final String originClass;

  /**
   * 해당 예외를 처리하는 코드입니다!
   * @param message 예외에 담아갈 메시지를 설정합니다!
   * @param cause 해당 예외가 발생한 원인이 되는 예외를 설정합니다!
   * @param originClass 발생한 클래스의 SimpleName을 입력 받습니다 advice에서 각각의 처리를 위해 사용합니다!
   */
  public JsonParseFailException(String message, Throwable cause, String originClass) {
    super(message, cause);
    this.originClass = originClass;
  }
}
