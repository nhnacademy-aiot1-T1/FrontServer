package com.nhnacademy.front.server.adapter.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.domain.CommonResponse;
import com.nhnacademy.front.server.domain.LoginResponseDto;
import com.nhnacademy.front.server.domain.UserLoginRequestDto;
import com.nhnacademy.front.server.domain.register.RegisterRequestDto;
import com.nhnacademy.front.server.exception.JsonParseFailException;
import com.nhnacademy.front.server.exception.LoginFailedException;
import com.nhnacademy.front.server.exception.RegisterFailException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        "GATEWAY-SERVICE/api/auth/login",
        HttpMethod.POST,
        requestEntity,
        new ParameterizedTypeReference<>() {
        }
    );
    return Objects.requireNonNull(exchange.getBody()).dataOrElseThrow(() -> new LoginFailedException("로그인 실패",this.getClass().getSimpleName()));
  }

  @Override
  public void logout(String accessToken) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("authorization",TOKEN_TYPE+" "+accessToken);

    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
    restTemplate.exchange(
        "GATEWAY-SERVICE/logout",
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

    HttpEntity<RegisterRequestDto> requestEntity = new HttpEntity<>(registerRequestDto,headers);
    log.warn("여기까지옴 adapter");
    try {
      restTemplate.exchange(
          "http://GATEWAY-SERVICE/api/users",
          HttpMethod.POST,
          requestEntity,
          Void.class
      );
    } catch (HttpClientErrorException e) {
      if (e.getStatusCode() == HttpStatus.CONFLICT) {
        String responseBody = e.getResponseBodyAsString();
        try {
          ObjectMapper objectMapper = new ObjectMapper();
          JsonNode rootNode = objectMapper.readTree(responseBody);
          String message = rootNode.path("message").asText(); // "message" 속성 값 추출

          throw new RegisterFailException(message,this.getClass().getSimpleName());
        } catch (IOException ioException) {
          // JSON 파싱 실패 처리
          throw new JsonParseFailException("JSON 파싱 오류", ioException,this.getClass().getSimpleName());
        }
      }
    }
  }
}
