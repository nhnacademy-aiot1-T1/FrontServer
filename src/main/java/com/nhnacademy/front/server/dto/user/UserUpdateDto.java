package com.nhnacademy.front.server.dto.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserUpdateDto {

  String name;
  String phone;
  String email;
  UserRole userRole;
  UserState userState;

  public enum UserState {
    ACTIVE, DEACTIVATED
  }
}
