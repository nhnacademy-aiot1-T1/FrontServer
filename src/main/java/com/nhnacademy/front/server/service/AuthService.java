package com.nhnacademy.front.server.service;

import com.nhnacademy.front.server.domain.UserLoginResponseDto;

import java.util.Optional;

public interface AuthService {
    Optional<UserLoginResponseDto> getToken();
}
