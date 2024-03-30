package com.nhnacademy.front.server.service;

import com.nhnacademy.front.server.domain.JwtToken;

import com.nhnacademy.front.server.domain.UserLoginRequestDto;
import java.util.Optional;

public interface AuthService {
    Optional<JwtToken> getLoginToken(String email, String password);

    void tokenLogout(String token);


}
