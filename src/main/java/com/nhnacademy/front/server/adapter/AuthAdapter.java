package com.nhnacademy.front.server.adapter;

import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.dto.LoginResponseDto;
import com.nhnacademy.front.server.dto.register.RegisterRequestDto;
import com.nhnacademy.front.server.dto.user.UserLoginRequestDto;

public interface AuthAdapter {

  CommonResponse<LoginResponseDto> login(UserLoginRequestDto requestDto);

  CommonResponse<LoginResponseDto> paycoLogin(String authCode);

  void logout(String accessToken);

  void registerUser(RegisterRequestDto registerRequestDto);

  CommonResponse<String> requestTokenRefresh(String accessToken);
}
