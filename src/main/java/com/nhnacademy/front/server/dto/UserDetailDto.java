package com.nhnacademy.front.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDto {

  Long id;
  String name;
  String phone;
  String email;
  UserRole role;

}
