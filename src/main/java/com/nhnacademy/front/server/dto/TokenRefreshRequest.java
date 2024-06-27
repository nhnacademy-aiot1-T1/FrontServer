package com.nhnacademy.front.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenRefreshRequest {

  private String expiredToken;
}
