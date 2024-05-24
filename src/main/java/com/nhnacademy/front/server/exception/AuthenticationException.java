package com.nhnacademy.front.server.exception;

public class AuthenticationException extends RuntimeException {
    private static final String MESSAGE = "인증에 실패했습니다.";

    public AuthenticationException() {
        this(MESSAGE);
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
