package com.nhnacademy.front.server.exception;

public class ClientIpMismatchException extends UnauthorizedException {

  public ClientIpMismatchException() {
  }

  public ClientIpMismatchException(String message) {
    super(message);
  }
}
