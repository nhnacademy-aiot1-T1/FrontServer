package com.nhnacademy.front.server.exception;

public class NotFoundTokenException extends UnauthorizedException {
    public NotFoundTokenException(String message) {
        super(message);
    }

    public NotFoundTokenException() {
        super();
    }
}
