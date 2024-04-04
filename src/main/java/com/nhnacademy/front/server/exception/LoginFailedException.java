package com.nhnacademy.front.server.exception;
public class LoginFailedException extends RuntimeException{
  public LoginFailedException(String message) {
    super(message);
  }
}
