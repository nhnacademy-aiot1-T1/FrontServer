package com.nhnacademy.front.server.adapter.impl;

import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.adapter.MonitoringAdaptor;
import com.nhnacademy.front.server.dto.MotorInfoOverviewDto;
import com.nhnacademy.front.server.dto.MotorsDto;
import com.nhnacademy.front.server.dto.SectorsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MonitoringAdaptorImpl implements MonitoringAdaptor {

  private final RestTemplate restTemplate;


  // 종합 정보
  @Override
  public CommonResponse<MotorInfoOverviewDto> getMotorInfoOverview(){
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);

    ResponseEntity<CommonResponse<MotorInfoOverviewDto>> exchange = restTemplate.exchange(
        "https://run.mocky.io/v3/4e6d763c-2f1f-48f2-a9e6-b16bb14beabb",
        HttpMethod.GET,
        request,
        new ParameterizedTypeReference<>() {
        }
    );

    if(exchange.getStatusCode() != HttpStatus.OK){
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();
  }

  // 구역 목록
  @Override
  public CommonResponse<SectorsDto> getSectorsInfo(){
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);

    ResponseEntity<CommonResponse<SectorsDto>> exchange = restTemplate.exchange(
        "https://run.mocky.io/v3/9a1947cd-320d-4dad-855c-88c643353e24",
        HttpMethod.GET,
        request,
        new ParameterizedTypeReference<>() {}
    );

    if(exchange.getStatusCode() != HttpStatus.OK){
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();
  }

  // 모터 목록
  @Override
  public CommonResponse<MotorsDto> getMotors(){
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));


    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);

    ResponseEntity<CommonResponse<MotorsDto>> exchange = restTemplate.exchange(
        "https://run.mocky.io/v3/01da6de5-fd42-41a9-b18c-740560273635",
        HttpMethod.GET,
        request,
        new ParameterizedTypeReference<>() {}
    );

    if(exchange.getStatusCode() != HttpStatus.OK){
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();
  }




}
