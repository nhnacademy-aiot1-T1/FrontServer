package com.nhnacademy.front.server.service.impl;

import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.domain.LoginResponseDto;
import com.nhnacademy.front.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthAdapter authAdapter;


    @Override
    public String getLoginToken(String id, String password,String userAddress) {
        LoginResponseDto loginResponseDto = authAdapter.userLogin(id,password,userAddress);
        return loginResponseDto.getAccessToken();
    }

    @Override
    public void tokenLogout(String token) {
        try {
            authAdapter.logout(token);
        }catch (HttpClientErrorException e){
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }
}
