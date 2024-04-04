package com.nhnacademy.front.server.exception;public class JsonParseFailException extends RuntimeException{

  public JsonParseFailException(String message, Throwable cause) {
    super(message, cause);
  }
}
