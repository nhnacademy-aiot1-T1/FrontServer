package com.nhnacademy.front.server.adapter.impl;

import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.dto.LoginResponseDto;
import com.nhnacademy.front.server.dto.UserLoginRequestDto;
import com.nhnacademy.front.server.dto.register.RegisterRequestDto;
import com.nhnacademy.front.server.exception.LoginFailedException;
import com.nhnacademy.front.server.exception.RegisterFailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthAdapterImpl implements AuthAdapter {

    private static final String TOKEN_TYPE = "Bearer";

    private final RestTemplate restTemplate;

    @Override
    public LoginResponseDto login(UserLoginRequestDto requestDto, String userIpAddress) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-IP", userIpAddress);
        HttpEntity<UserLoginRequestDto> requestEntity = new HttpEntity<>(requestDto, headers);

        ResponseEntity<CommonResponse<LoginResponseDto>> response = restTemplate.exchange(
                "http://GATEWAY-SERVICE/api/auth/login",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        // todo, common type으로 들어오는 걸로 알고 있음, 테스트 하고 수정해야 함.

        return Objects.requireNonNull(response.getBody()).dataOrElseThrow(() -> new LoginFailedException("로그인 실패"));
    }

    @Override
    public void logout(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        String authorization = String.format("%s %s", TOKEN_TYPE, accessToken);

        HttpEntity<String> requestEntity = new HttpEntity<>(authorization, headers);
        restTemplate.exchange(
                "http://GATEWAY-SERVICE/api/auth/logout",
                HttpMethod.POST,
                requestEntity,
                Void.class // todo, test : void?
        );
    }

    @Override
    public void registerUser(RegisterRequestDto registerRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<RegisterRequestDto> requestEntity = new HttpEntity<>(registerRequestDto, headers);

        ResponseEntity<CommonResponse<LoginResponseDto>> response = restTemplate.exchange(
                "http://GATEWAY-SERVICE/api/users",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        if (!response.getStatusCode().equals(HttpStatus.CREATED)) {
            String message = response.getBody().getMessage();

            throw new RegisterFailException(message);
        }
    }

//    @Override
    public void a() {

    }

    @Override
    public void requestTokenRefresh(String token, String address) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-IP", address);

        String authorization = String.format("%s %s", TOKEN_TYPE, address);
        HttpEntity<String> requestEntity = new HttpEntity<>(authorization, headers);

        var res = restTemplate.exchange(
                "http://GATEWAY-SERVICE/api/regenerate",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );
    }
}
