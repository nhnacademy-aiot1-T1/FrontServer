package com.nhnacademy.front.server.service;

import com.nhnacademy.front.server.domain.register.RegisterRequestDto;

/**
 * 회원 가입 관련의 로직을 처리하는 서비스 클래스 입니다
 * @author AoiTuNa
 * @version 1.0
 * @see #registerCreated(RegisterRequestDto)
 */
public interface RegisterService {

  /**
   * 회원가입을 요청한 유저가 올바른 값을 입력했고 생성이 되었는지 확인하는 메서드입니다!<p></p>
   * 저장도중 문제가 생기거나 Id 중복이 발생하면 예외를 던집니다!
   * @param registerRequestDto 유저가 유효한 값을 입력했을 때 Api 서버로 요청을 보내기위란 domain객체 입니다!
   */
  void registerCreated(RegisterRequestDto registerRequestDto);
}
