package com.nhnacademy.front.server.adapter.impl;

import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.dto.LoginResponseDto;
import com.nhnacademy.front.server.dto.LogoutRequest;
import com.nhnacademy.front.server.dto.OauthLoginRequestDto;
import com.nhnacademy.front.server.dto.TokenRefreshRequest;
import com.nhnacademy.front.server.dto.register.RegisterRequestDto;
import com.nhnacademy.front.server.dto.user.UserLoginRequestDto;
import com.nhnacademy.front.server.enums.OauthType;
import com.nhnacademy.front.server.exception.LoginFailedException;
import com.nhnacademy.front.server.exception.RegisterFailException;
import com.nhnacademy.front.server.exception.UnauthorizedException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
  public CommonResponse<LoginResponseDto> login(UserLoginRequestDto requestDto) {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<UserLoginRequestDto> requestEntity = new HttpEntity<>(requestDto, headers);

    ResponseEntity<CommonResponse<LoginResponseDto>> response = restTemplate.exchange(
        "http://GATEWAY-SERVICE/api/auth/login",
        HttpMethod.POST,
        requestEntity,
        new ParameterizedTypeReference<>() {
        }
    );

    if (!Objects.equals(response.getStatusCode(), HttpStatus.OK)) {
      throw new LoginFailedException("로그인에 실패했습니다.");
    }

    return response.getBody();
  }


  @Override
  public CommonResponse<LoginResponseDto> paycoLogin(String authCode) {
    OauthLoginRequestDto oauthLoginRequestDto = new OauthLoginRequestDto(OauthType.PAYCO, authCode);

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<OauthLoginRequestDto> requestEntity = new HttpEntity<>(oauthLoginRequestDto,
        headers);

    ResponseEntity<CommonResponse<LoginResponseDto>> response = restTemplate.exchange(
        "http://GATEWAY-SERVICE/api/auth/oauth-login",
        HttpMethod.POST,
        requestEntity,
        new ParameterizedTypeReference<>() {
        }
    );

    log.info("payco login response : {}", response);

    if (!Objects.equals(response.getStatusCode(), HttpStatus.OK)) {
      throw new LoginFailedException("로그인에 실패했습니다.");
    }

    return response.getBody();
  }

  @Override
  public void logout(String accessToken) {
    HttpHeaders headers = new HttpHeaders();
    String authorization = String.format("%s %s", TOKEN_TYPE, accessToken);
    headers.add("Authorization", authorization);

    HttpEntity<LogoutRequest> requestEntity = new HttpEntity<>(new LogoutRequest(accessToken),
        headers);
    ResponseEntity<CommonResponse<String>> response = restTemplate.exchange(
        "http://GATEWAY-SERVICE/api/auth/logout",
        HttpMethod.POST,
        requestEntity,
        new ParameterizedTypeReference<>() {
        }
    );

    if (!Objects.equals(response.getStatusCode(), HttpStatus.OK)) {
      String message = Objects.requireNonNull(response.getBody()).getMessage();

      log.error("response code : {}, cause : {}", response.getStatusCode(), message);
    }
  }

  @Override
  public void registerUser(RegisterRequestDto registerRequestDto) {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<RegisterRequestDto> requestEntity = new HttpEntity<>(registerRequestDto, headers);

    ResponseEntity<CommonResponse<LoginResponseDto>> response = restTemplate.exchange(
        "http://GATEWAY-SERVICE/api/account/users",
        HttpMethod.POST,
        requestEntity,
        new ParameterizedTypeReference<>() {
        }
    );

    if (!Objects.equals(response.getStatusCode(), HttpStatus.CREATED)) {
      String message = Objects.requireNonNull(response.getBody()).getMessage();

      log.error(message);
      throw new RegisterFailException(message);
    }
  }

  @Override
  public CommonResponse<String> requestTokenRefresh(String accessToken) {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<TokenRefreshRequest> requestEntity = new HttpEntity<>(
        new TokenRefreshRequest(accessToken), headers);

    ResponseEntity<CommonResponse<String>> response = restTemplate.exchange(
        "http://GATEWAY-SERVICE/api/auth/reissue",
        HttpMethod.POST,
        requestEntity,
        new ParameterizedTypeReference<>() {
        }
    );

    if (!Objects.equals(response.getStatusCode(), HttpStatus.OK)) {
      String message = Objects.requireNonNull(response.getBody()).getMessage();

      throw new UnauthorizedException(message);
    }

    return response.getBody();
  }
}
