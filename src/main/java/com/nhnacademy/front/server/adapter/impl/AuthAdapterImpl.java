package com.nhnacademy.front.server.adapter.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.domain.CommonResponse;
import com.nhnacademy.front.server.domain.LoginResponseDto;
import com.nhnacademy.front.server.domain.UserLoginRequestDto;
import com.nhnacademy.front.server.domain.register.CreateRegisterRequestDto;
import com.nhnacademy.front.server.exception.LoginFailedException;
import com.nhnacademy.front.server.exception.RegisterFailException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthAdapterImpl implements AuthAdapter {

  private static final String TOKEN_TYPE = "Bearer";

  private final RestTemplate restTemplate;

  public AuthAdapterImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;

  }

  @Override
  public LoginResponseDto userLogin(String id, String password, String userAddress) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    headers.add("userId",userAddress);

    UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto(id,password);

    HttpEntity<UserLoginRequestDto> requestEntity = new HttpEntity<>(userLoginRequestDto,headers);

    ResponseEntity<CommonResponse<LoginResponseDto>> exchange = restTemplate.exchange(
        "http://192.168.71.99:8080/api/auth/login",
        HttpMethod.POST,
        requestEntity,
        new ParameterizedTypeReference<>() {
        }
    );
    return Objects.requireNonNull(exchange.getBody()).dataOrElseThrow(() -> new LoginFailedException("로그인 실패"));
  }

  @Override
  public void logout(String accessToken) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("authorization",TOKEN_TYPE+" "+accessToken);

    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
    restTemplate.exchange(
        "http://192.168.0.27:8080/logout",
        HttpMethod.POST,
        requestEntity,
        Void.class
    );
  }

  @Override
  public void registerUser(CreateRegisterRequestDto createRegisterRequestDto) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<CreateRegisterRequestDto> requestEntity = new HttpEntity<>(createRegisterRequestDto,headers);

    try {
      restTemplate.exchange(
          "http://192.168.0.27:8080/register",
          HttpMethod.POST,
          requestEntity,
          new ParameterizedTypeReference<CommonResponse<Void>>() {}
      );
    } catch (HttpClientErrorException e) {
      if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
        String responseBody = e.getResponseBodyAsString();
        try {
          ObjectMapper objectMapper = new ObjectMapper();
          JsonNode rootNode = objectMapper.readTree(responseBody);
          String message = rootNode.path("message").asText(); // "message" 속성 값 추출

          throw new RegisterFailException(message);
        } catch (IOException ioException) {
          // JSON 파싱 실패 처리
          throw new RuntimeException("JSON 파싱 오류", ioException);
        }
      }
    }
  }
}
