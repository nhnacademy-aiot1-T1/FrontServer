package com.nhnacademy.front.server.exception;

public class LogoutNotFoundTokenException extends RuntimeException{
    public LogoutNotFoundTokenException(String message) {
        super(message);
    }
}
