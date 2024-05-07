package com.nhnacademy.front.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 유저의 로그인을 요청하기 위해 입력값을 저장하는 domain class입니다!
 * @author AoiTuNa
 * @version 1.0
 */
@Getter
@AllArgsConstructor
@Deprecated // common lib 이슈 해결되면 account api와 동일한 객체를 배포할 예정
public class UserLoginRequestDto {
  private String loginId;
  private String password;
}
