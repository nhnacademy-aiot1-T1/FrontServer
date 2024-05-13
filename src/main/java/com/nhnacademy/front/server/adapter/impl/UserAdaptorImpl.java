package com.nhnacademy.front.server.adapter.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.adapter.UserAdaptor;
import com.nhnacademy.front.server.dto.UserDetailDto;
import java.util.List;
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
import org.springframework.web.server.ResponseStatusException;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserAdaptorImpl implements UserAdaptor {

  private final RestTemplate restTemplate;

  private static final MediaType CONTENT_TYPE = MediaType.APPLICATION_JSON;
  private static final String GET_USER_DETAIL_URL = "https://run.mocky.io/v3/1dc226c9-8189-49ef-8bbe-28616b6d2f1f";
  private static final String POST_USER_DETAIL_URL = "https://run.mocky.io/v3/cfd28bcd-01f2-4de3-9db2-53253d403a71";
  private static final String DELETE_USER_DETAIL_URL = "";
  private static final String JSON_PARSING_ERROR_MESSAGE = "JSON parsing error";

  @Override
  public CommonResponse<UserDetailDto> getUserDetail(Long id) {
    HttpHeaders headers = setupHttpHeaders();
    HttpEntity<String> request = new HttpEntity<>(headers);

    ResponseEntity<CommonResponse<UserDetailDto>> exchange = restTemplateExchange(
        GET_USER_DETAIL_URL, HttpMethod.GET, request);

    log.error("it is :{}", exchange.getBody());
    if (!exchange.getStatusCode().equals(HttpStatus.OK)) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }
    return exchange.getBody();
  }

  @Override
  public CommonResponse<UserDetailDto> updateUserDetail(Long id, UserDetailDto userDetailDto) {
    ObjectMapper objectMapper = new ObjectMapper();
    String requestBody = null;

    try {
      requestBody = objectMapper.writeValueAsString(userDetailDto);
    } catch (JsonProcessingException e) {
      log.error(JSON_PARSING_ERROR_MESSAGE + ":{}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          JSON_PARSING_ERROR_MESSAGE);
    }

    HttpEntity<String> request = new HttpEntity<>(requestBody, setupHttpHeaders());

    ResponseEntity<CommonResponse<UserDetailDto>> exchange;
    try {
      exchange = restTemplateExchange(POST_USER_DETAIL_URL, HttpMethod.POST, request);
    } catch (ResponseStatusException e) {
      log.error("RestTemplate exchange error :{}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Error occurred while posting user detail");
    }

    log.info("it is :{}", exchange.getBody());
    if (!exchange.getStatusCode().equals(HttpStatus.OK)) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }
    return exchange.getBody();
  }

  @Override
  public CommonResponse<UserDetailDto> deleteUserDetail(Long id) {
    HttpHeaders headers = setupHttpHeaders();
    HttpEntity<String> request = new HttpEntity<>(headers);

    ResponseEntity<CommonResponse<UserDetailDto>> exchange = restTemplateExchange(
        DELETE_USER_DETAIL_URL, HttpMethod.DELETE, request);

    if (!exchange.getStatusCode().equals(HttpStatus.OK)) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();

  }

  private HttpHeaders setupHttpHeaders() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(CONTENT_TYPE);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

    return httpHeaders;
  }

  private ResponseEntity<CommonResponse<UserDetailDto>> restTemplateExchange(String url,
      HttpMethod httpMethod, HttpEntity<String> request) {
    return restTemplate.exchange(
        url,
        httpMethod,
        request,
        new ParameterizedTypeReference<>() {
        });
  }
}