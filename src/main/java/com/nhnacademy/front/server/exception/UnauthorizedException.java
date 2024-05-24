package com.nhnacademy.front.server.exception;

public class UnauthorizedException extends RuntimeException {
    private static final String MESSAGE = "401 unauthorized exception";

    public UnauthorizedException() {
        this(MESSAGE);
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
