package com.nhnacademy.front.server.service.impl;

import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.domain.JwtToken;
import com.nhnacademy.front.server.domain.UserLoginRequestDto;
import com.nhnacademy.front.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthAdapter authAdapter;


    @Override
    public Optional<JwtToken> getLoginToken(String email, String password) {
        JwtToken token;
        token = authAdapter.userLogin(email,password);
        return Optional.ofNullable(token);
    }

    @Override
    public void tokenLogout(String token) {
        try {
            authAdapter.logout(token);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
