package com.nhnacademy.front.server.adapter;

import com.nhnacademy.front.server.domain.LoginResponseDto;

/**
 * Javadoc 인증 클래스
 * @author AoiTuNa
 * @version 1.0
 * @see #userLogin(String, String)
 * @see #logout(String)
 */
public interface AuthAdapter {

  /**
   * 유저의 로그인 정보를 보내 token value를 가져오는 메서드 입니다!
   * @param id 로그인에 필요한 유저 email
   * @param password 로그인에 필요한 유저 password
   * @return accessToken이 포함된 domain class
   * @see LoginResponseDto
   */
  LoginResponseDto userLogin(String id, String password);

  /**
   * 유저의 로그아웃 요청에 따라 토큰을 보내 사용자를 검증하고 로그아웃을 실행하는 메서드 입니다!
   * @param accessToken 로그아웃에 필요한 client의 token
   */
  void logout(String accessToken);

}
