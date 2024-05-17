package com.nhnacademy.front.server.adapter;

import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.dto.LoginResponseDto;
import com.nhnacademy.front.server.dto.UserLoginRequestDto;
import com.nhnacademy.front.server.dto.register.RegisterRequestDto;

public interface AuthAdapter {

  CommonResponse<LoginResponseDto> login(UserLoginRequestDto requestDto);

  CommonResponse<LoginResponseDto> oauthLogin(String authCode);

  void logout(String accessToken);

  void registerUser(RegisterRequestDto registerRequestDto);

  CommonResponse<String> requestTokenRefresh(String accessToken);
}
