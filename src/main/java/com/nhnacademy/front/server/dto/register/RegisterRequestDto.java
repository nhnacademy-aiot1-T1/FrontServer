package com.nhnacademy.front.server.dto.register;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * gateway server를 통해 api server로 회원가입한 유저의 정보를 보내기위해 만들어진 domain객체 입니다!
 * @author AoiTuNa
 * @version 1.0
 */
@Getter
@AllArgsConstructor
@Deprecated // common lib 이슈 해결되면 account api와 동일한 객체를 배포할 예정
public class RegisterRequestDto {
  private String userId;
  private String password;
  private String name;
  private String email;
}
