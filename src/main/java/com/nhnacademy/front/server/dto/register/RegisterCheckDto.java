package com.nhnacademy.front.server.dto.register;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

/**
 * 회원가입 폼에서 유저가 입력한 정보를 저장하기 위해 사용하는 domain객체 입니다!
 * @author AoiTuNa
 * @version 1.0
 */
@Getter
public class RegisterCheckDto {
  @NotBlank(message = "id는 필수 입력 항목입나다!")
  @Length(min = 5, message = "id는 5자 이상이어야 합니다!")
  private String id;

  @NotBlank(message = "pw는 필수 입력 항목입니다!")
  @Length(min = 8, message = "pw는 8글자 이상이어야 합니다!")
  private String password;

  @NotBlank(message = "pw확인은 필수 입력 항목입니다!")
  @Length(min = 8, message = "pw확인은 8글자 이상이어야 합니다!") // todo, 비밀번호 재확인, 어노테이션 만들기
  private String passwordRetype;

  private String name;

  @Email(message = "이메일 형식이 아닙니다!")
  private String email;
}
