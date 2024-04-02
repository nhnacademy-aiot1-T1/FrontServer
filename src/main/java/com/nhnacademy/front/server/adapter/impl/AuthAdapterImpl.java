package com.nhnacademy.front.server.adapter.impl;

import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.domain.JwtToken;
import com.nhnacademy.front.server.domain.UserLoginRequestDto;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthAdapterImpl implements AuthAdapter {

  private static final String TOKEN_TYPE = "Bearer";

  private final RestTemplate restTemplate;

  public AuthAdapterImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;

  }

  @Override
  public JwtToken userLogin(String email, String password) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto(email,password);

    HttpEntity<UserLoginRequestDto> requestEntity = new HttpEntity<>(userLoginRequestDto,headers);

    ResponseEntity<JwtToken> exchange = restTemplate.exchange(
        //Todo gateway의 url이나 eureka 설정에 따른 추가
        "/test",//  <-- 임시임
        HttpMethod.POST,
        requestEntity,
        JwtToken.class
    );
    return exchange.getBody();
  }

  @Override
  public void logout(String accessToken) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("authorization",TOKEN_TYPE+" "+accessToken);

    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
    restTemplate.exchange(
        //Todo gateway의 url이나 eureka 설정에 따른 추가
        "http://localhost:8081/api/account/logout",// <-- 임시임
        HttpMethod.POST,
        requestEntity,
        Void.class
    );
  }
}
