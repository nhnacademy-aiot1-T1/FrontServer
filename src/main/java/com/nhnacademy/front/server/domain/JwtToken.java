package com.nhnacademy.front.server.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class JwtToken {
    private String accessToken;
}
