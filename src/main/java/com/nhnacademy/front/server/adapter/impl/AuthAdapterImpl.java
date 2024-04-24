package com.nhnacademy.front.server.adapter.impl;

import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.dto.LoginResponseDto;
import com.nhnacademy.front.server.dto.UserLoginRequestDto;
import com.nhnacademy.front.server.dto.register.RegisterRequestDto;
import com.nhnacademy.front.server.exception.LoginFailedException;
import com.nhnacademy.front.server.exception.RegisterFailException;

import java.util.List;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthAdapterImpl implements AuthAdapter {

    private static final String TOKEN_TYPE = "Bearer";

    private final RestTemplate restTemplate;

    @Override
    public LoginResponseDto login(UserLoginRequestDto requestDto, String userIpAddress) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.add("Client-IP", userIpAddress);

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
        headers.set("authorization", TOKEN_TYPE + " " + accessToken);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        restTemplate.exchange(
                "http://GATEWAY-SERVICE/api/auth/logout",
                HttpMethod.POST,
                requestEntity,
                Void.class
        );
    }

    @Override
    public void registerUser(RegisterRequestDto registerRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<RegisterRequestDto> requestEntity = new HttpEntity<>(registerRequestDto, headers);

        ResponseEntity<CommonResponse<LoginResponseDto>> response = restTemplate.exchange(
                "http://GATEWAY-SERVICE/api/users",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        if (!response.getStatusCode().equals(HttpStatus.CREATED)) {
            String message = response.getBody().getMessage(); // non-null test.

            log.info(message); // todo, log를 여기서 처리해야 할지, 아니면 handler에서 처리하는게 맞을지?
            throw new RegisterFailException(message);
        }
    }

    @Override // todo, 여기 수정해야함
    public void checkAccessToken(String token, String address) {
        //Todo 혹시나 실행한 브라우저의 정보를 포함할 가능성 있름!
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.add("Client-IP", address);
        headers.add("Authorization", TOKEN_TYPE + address);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        restTemplate.exchange(
                "http://GATEWAY-SERVICE/api/regenerate",
                HttpMethod.POST,
                requestEntity,
                Void.class
        );

    }
}
