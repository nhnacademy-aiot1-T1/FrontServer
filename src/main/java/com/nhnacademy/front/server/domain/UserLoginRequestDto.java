package com.nhnacademy.front.server.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserLoginRequestDto {
    private String email;
    private String password;
}
