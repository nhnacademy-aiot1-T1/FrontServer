package com.nhnacademy.front.server.domain.register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterRequestDto {
  private String id;
  private String password;
  private String passwordRetype;
}
