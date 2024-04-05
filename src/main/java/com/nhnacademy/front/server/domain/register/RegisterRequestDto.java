package com.nhnacademy.front.server.domain.register;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * gateway server를 통해 api server로 회원가입한 유저의 정보를 보내기위해 만들어진 domain객체 입니다!
 * @author AoiTuNa
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public class RegisterRequestDto {
  private String userId;
  private String password;
  private String name;
  private String email;
}
