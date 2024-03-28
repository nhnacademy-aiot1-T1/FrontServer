package com.nhnacademy.front.server.service.impl;

import com.nhnacademy.front.server.domain.UserLoginResponseDto;
import com.nhnacademy.front.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {



    @Override
    public Optional<UserLoginResponseDto> getToken() {
        //Todo adapter에서 responseEntity 나 토큰값을 가지고 오기!
        return Optional.empty();
    }
}
