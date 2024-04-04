package com.nhnacademy.front.server.domain.register;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateRegisterRequestDto {
  private String id;
  private String password;
}
