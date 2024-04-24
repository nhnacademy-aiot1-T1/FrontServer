package com.nhnacademy.front.server.adapter;

import com.nhnacademy.front.server.dto.LoginResponseDto;
import com.nhnacademy.front.server.dto.UserLoginRequestDto;
import com.nhnacademy.front.server.dto.register.RegisterRequestDto;

/**
 * Javadoc 인증 클래스
 * @see #logout(String)
 * @see #registerUser(RegisterRequestDto)
 * @see #checkAccessToken(String, String)
 */
public interface AuthAdapter {

  /**
   * 유저의 로그인 정보를 보내 token value를 가져오는 메서드 입니다.
   * @see LoginResponseDto
   */
  LoginResponseDto login(UserLoginRequestDto requestDto, String userIpAddress);


  /**
   * 유저의 로그아웃 요청에 따라 토큰을 보내 사용자를 검증하고 로그아웃을 실행하는 메서드 입니다.
   * @param accessToken 로그아웃에 필요한 client의 token
   */
  void logout(String accessToken);

  /**
   * 회원가입한 유저의 정보를 보내기 위한 메서드 입니다.
   */
  void registerUser(RegisterRequestDto registerRequestDto);

  /**
   * accessToken가 유효한지 검증하는 메서드 입니다.
   * @param token 현재 가지고 있는 accessToken의 정보입니다.
   * @param address 요청자의 IP주소를 지정합니다.
   */
  void checkAccessToken(String token, String address);
}
