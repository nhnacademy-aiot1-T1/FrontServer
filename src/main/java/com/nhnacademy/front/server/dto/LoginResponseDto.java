package com.nhnacademy.front.server.dto;

import lombok.*;

/**
 * 유저의 accessToken의 정보가 들어있는 domain Class 입니다!
 * @author AoiTuNa
 * @version 1.0
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Deprecated // common lib 이슈 해결되면 account api와 동일한 객체를 배포할 예정
public class LoginResponseDto {
  String userId;
  String accessToken;
}

