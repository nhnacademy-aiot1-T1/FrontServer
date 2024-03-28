package com.nhnacademy.front.server.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserLoginResponseDto {
    private String accessToken;
    private String tokenType;
}
