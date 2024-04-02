package com.nhnacademy.front.server.domain;

import lombok.*;

/**
 * 유저의 로그인을 요청하기 위해 입력값을 저장하는 domain class입니다!
 * @author AoiTuNa
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public class UserLoginRequestDto {
    private String email;
    private String password;
}
