package com.nhnacademy.front.server.service.impl;

import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.dto.LoginResponseDto;
import com.nhnacademy.front.server.dto.UserLoginRequestDto;
import com.nhnacademy.front.server.dto.register.RegisterRequestDto;
import com.nhnacademy.front.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthAdapter authAdapter;

    @Override
    public LoginResponseDto login(UserLoginRequestDto requestDto) {
        CommonResponse<LoginResponseDto> response = authAdapter.login(requestDto);
        log.info(response.getMessage());

        return response.getData();
    }

    @Override
    public LoginResponseDto oauthLogin(String authCode) {
        CommonResponse<LoginResponseDto> response = authAdapter.oauthLogin(authCode);
        log.info(response.getMessage());

        return response.getData();
    }

    @Override
    public void logout(String accessToken) {
        authAdapter.logout(accessToken);
    }

    @Override
    public void registerUser(RegisterRequestDto registerRequestDto) {
        authAdapter.registerUser(registerRequestDto);
    }

    @Override
    public String requestTokenRefresh(String accessToken) {
        CommonResponse<String> response = authAdapter.requestTokenRefresh(accessToken);
        log.info(response.getMessage());

        return response.getData();
    }
}
