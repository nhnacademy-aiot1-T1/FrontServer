package com.nhnacademy.front.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDetailDto {

  Long id;
  String userName;
  String userPhone;
  String userEmail;
  UserRole userRole;

}
