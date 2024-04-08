package com.nhnacademy.front.server.adapter;

import com.nhnacademy.front.server.domain.LoginResponseDto;
import com.nhnacademy.front.server.domain.register.RegisterRequestDto;

/**
 * Javadoc 인증 클래스
 * @author AoiTuNa
 * @version 1.1
 * @see #userLogin(String, String, String)
 * @see #logout(String)
 */
public interface AuthAdapter {

  /**
   * 유저의 로그인 정보를 보내 token value를 가져오는 메서드 입니다!
   * @param id 로그인에 필요한 유저 id
   * @param password 로그인에 필요한 유저 password
   * @param userAddress accessToken의 발급을 위한 유저의 ip정보
   * @return accessToken이 포함된 domain class
   * @see LoginResponseDto
   */
  LoginResponseDto userLogin(String id, String password,String userAddress);

  /**
   * 유저의 로그아웃 요청에 따라 토큰을 보내 사용자를 검증하고 로그아웃을 실행하는 메서드 입니다!
   * @param accessToken 로그아웃에 필요한 client의 token
   */
  void logout(String accessToken);

  /**
   * 회원가입한 유저의 정보를 보내기 위한 메서드 입니다!
   * @param registerRequestDto 1.1V 기준 id pw 값이 포함되어 있습니다! 추후 추가될 가능성이 있습니다!.
   */
  void registerUser(RegisterRequestDto registerRequestDto);

  void checkAccessToken(String token,String address);

}
