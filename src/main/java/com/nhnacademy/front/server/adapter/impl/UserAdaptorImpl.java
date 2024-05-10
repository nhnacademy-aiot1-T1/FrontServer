package com.nhnacademy.front.server.adapter.impl;

import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.adapter.UserAdaptor;
import com.nhnacademy.front.server.dto.UserDetailDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class UserAdaptorImpl implements UserAdaptor {

  private final RestTemplate restTemplate;

  private static final MediaType CONTENT_TYPE = MediaType.APPLICATION_JSON;
  private static final String GET_USER_DETAIL_URL = "https://run.mocky.io/v3/33acb19b-6557-44f6-914c-4a8ef12b0fa4";
  private static final String POST_USER_DETAIL_URL = "";

  @Override
  public CommonResponse<UserDetailDto> getUserDetail(Long userId) {
    HttpEntity<String> request = setupHttpHeaders();

    ResponseEntity<CommonResponse<UserDetailDto>> exchange = restTemplate.exchange(
        GET_USER_DETAIL_URL,
        HttpMethod.GET,
        request,
        new ParameterizedTypeReference<>() {
        });

    if (!exchange.getStatusCode().equals(HttpStatus.OK)) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }
    return exchange.getBody();
  }

  @Override
  public CommonResponse<UserDetailDto> updateUserDetail(UserDetailDto userDetailDto) {
    HttpEntity<String> request = setupHttpHeaders();

    ResponseEntity<CommonResponse<UserDetailDto>> exchange = restTemplate.exchange(
        POST_USER_DETAIL_URL,
        HttpMethod.POST,
        request,
        new ParameterizedTypeReference<>() {
        });

    if (!exchange.getStatusCode().equals(HttpStatus.OK)) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }
    return exchange.getBody();
  }

  private HttpEntity<String> setupHttpHeaders() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(CONTENT_TYPE);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

    return new HttpEntity<>(httpHeaders);
  }
}
