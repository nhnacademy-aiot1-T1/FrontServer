package com.nhnacademy.front.server.dto;

import com.nhnacademy.front.server.enums.OauthType;
import lombok.*;

/**
 * 로그인 요청 dto
 */
@Getter
@Setter
@AllArgsConstructor
public class OauthLoginRequestDto {
  private OauthType type;
  private String authCode;
}
