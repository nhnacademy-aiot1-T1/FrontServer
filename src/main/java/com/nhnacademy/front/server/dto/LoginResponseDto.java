package com.nhnacademy.front.server.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 유저의 accessToken의 정보가 들어있는 domain Class 입니다.
 */

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDto {

  String userId;
  String accessToken;
}

