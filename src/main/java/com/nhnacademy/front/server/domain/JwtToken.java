package com.nhnacademy.front.server.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class JwtToken {
    @JsonProperty("access_token")
    private String accessToken;
}
