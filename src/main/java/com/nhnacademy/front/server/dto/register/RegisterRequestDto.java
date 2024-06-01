package com.nhnacademy.front.server.dto.register;

import lombok.*;

/**
 * gateway server를 통해 api server로 회원가입한 유저의 정보를 보내기위해 만들어진 domain객체 입니다!
 */
@Getter
@Setter
@NoArgsConstructor
public class RegisterRequestDto {
  private String loginId;
  private String password;
  private String name;
  private String email;
}
