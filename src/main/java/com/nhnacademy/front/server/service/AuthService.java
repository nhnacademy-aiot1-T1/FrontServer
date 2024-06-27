package com.nhnacademy.front.server.service;

import com.nhnacademy.front.server.dto.LoginResponseDto;
import com.nhnacademy.front.server.dto.register.RegisterRequestDto;
import com.nhnacademy.front.server.dto.user.UserLoginRequestDto;

public interface AuthService {

  LoginResponseDto login(UserLoginRequestDto requestDto);

  LoginResponseDto paycoLogin(String authCode);

  void logout(String accessToken);

  void registerUser(RegisterRequestDto registerRequestDto);

  String requestTokenRefresh(String accessToken);
}
