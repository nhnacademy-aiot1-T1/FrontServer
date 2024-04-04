package com.nhnacademy.front.server.domain;

import lombok.*;

/**
 * 유저의 accessToken의 정보가 들어있는 domain Class 입니다!
 * @author AoiTuNa
 * @version 1.0
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
  String userId;
  String accessToken;
  String userAddress;
}

