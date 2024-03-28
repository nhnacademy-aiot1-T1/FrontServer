package com.nhnacademy.front.server.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserLoginRequestDto {
    private String id;
    private String password;
}
