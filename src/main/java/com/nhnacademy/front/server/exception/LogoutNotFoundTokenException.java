package com.nhnacademy.front.server.exception;

/**
 * 로그아웃시 토큰을 찾지 못했을 때 발생하는 예외입니다!
 * @author AoiTuNa
 * @version 1.0
 */
public class LogoutNotFoundTokenException extends RuntimeException{
    public LogoutNotFoundTokenException(String message) {
        super(message);
    }
}
