package com.nhnacademy.front.server.adapter.impl;

import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.adapter.MonitoringAdaptor;
import com.nhnacademy.front.server.dto.controlLog.ControlLogsDto;
import com.nhnacademy.front.server.dto.motorDetail.MotorDetailDto;
import com.nhnacademy.front.server.dto.motorInfoOverview.MotorInfoOverviewDto;
import com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod.MotorsRunningRatesByTimePeriod;
import com.nhnacademy.front.server.dto.motorScore.MotorScoresDto;
import com.nhnacademy.front.server.dto.motor.MotorsDto;
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

  public static final String MOTOR_INFO_OVERVIEW = "https://run.mocky.io/v3/2fdf345f-24d8-4eb9-acf9-56b94bcdab84";
  public static final String SECTORS_INFO = "https://run.mocky.io/v3/fbea79ab-4ba0-4d6e-8bcf-9d961fa43020";
  public static final String MOTORS = "https://run.mocky.io/v3/c092c66d-b352-46c7-b283-5c96dffbd272";
  public static final String MOTOR_DETAIL = "https://run.mocky.io/v3/652d7a44-6913-4ff7-bc7f-289e9ad64ff4";
  public static final String MOTOR_SCORES = "https://run.mocky.io/v3/ef52835f-8b16-4d70-abbd-c1cde05d6fd5";
  public static final String CONTR0L_LOGS = "https://run.mocky.io/v3/f76094b7-f650-431f-a9e4-bb1d5224b5fe";
  public static final String RUNNING_RATES_BY_TIME1 = "https://run.mocky.io/v3/fc91f26c-1883-41b3-a65d-406681cc7060"; // day data
  public static final String RUNNING_RATES_BY_TIME2 = "https://run.mocky.io/v3/8eda357b-8c12-41ff-aa6f-87b7940df3fb"; // week data
  public static final String RUNNING_RATES_BY_TIME3 = "https://run.mocky.io/v3/eae23440-8507-4205-9efd-e06fe6daa6be"; // month data

  private final RestTemplate restTemplate;


  // 종합 정보
  @Override
  public CommonResponse<MotorInfoOverviewDto> getMotorInfoOverview() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);

    ResponseEntity<CommonResponse<MotorInfoOverviewDto>> exchange = restTemplate.exchange(
        MOTOR_INFO_OVERVIEW, HttpMethod.GET, request,
        new ParameterizedTypeReference<>() {
        });

    if (exchange.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();
  }

  // 구역 목록
  @Override
  public CommonResponse<SectorsDto> getSectorsInfo() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);

    ResponseEntity<CommonResponse<SectorsDto>> exchange = restTemplate.exchange(
        SECTORS_INFO, HttpMethod.GET, request,
        new ParameterizedTypeReference<>() {
        });

    if (exchange.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();
  }

  // 모터 목록
  @Override
  public CommonResponse<MotorsDto> getMotors() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);

    ResponseEntity<CommonResponse<MotorsDto>> exchange = restTemplate.exchange(
        MOTORS, HttpMethod.GET, request,
        new ParameterizedTypeReference<>() {
        });

    if (exchange.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();
  }

  @Override
  public CommonResponse<MotorDetailDto> getMotorDetail(Long motorId) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);

    ResponseEntity<CommonResponse<MotorDetailDto>> exchange = restTemplate.exchange(
        MOTOR_DETAIL, HttpMethod.GET, request,
        new ParameterizedTypeReference<>() {
        });

    if (exchange.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();
  }

  @Override
  public CommonResponse<MotorScoresDto> getMotorScores(Long motorId) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);

    ResponseEntity<CommonResponse<MotorScoresDto>> exchange = restTemplate.exchange(
        // TODO 프로퍼티로 뺴기 ( Monitoring
        MOTOR_SCORES, HttpMethod.GET, request,
        new ParameterizedTypeReference<>() {
        });
    if (exchange.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();
  }

  @Override
  public CommonResponse<ControlLogsDto> getControlLogs() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);

    ResponseEntity<CommonResponse<ControlLogsDto>> exchange = restTemplate.exchange(
        CONTR0L_LOGS, HttpMethod.GET, request,
        new ParameterizedTypeReference<>() {
        });

    if (exchange.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();


  }

  @Override
  public CommonResponse<MotorsRunningRatesByTimePeriod> getMotorsRunningRatesByTimePeriod(
      String timePeriod) {

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);

    if (timePeriod.equals("day")) {

      ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplate.exchange(
          RUNNING_RATES_BY_TIME1, HttpMethod.GET, request,
          new ParameterizedTypeReference<>() {
          });
      if (exchange.getStatusCode() != HttpStatus.OK) {
        throw new ResponseStatusException(exchange.getStatusCode());
      }
      return exchange.getBody();

    }
    if (timePeriod.equals("week")) {

      ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplate.exchange(
          RUNNING_RATES_BY_TIME2, HttpMethod.GET, request,
          new ParameterizedTypeReference<>() {
          });
      if (exchange.getStatusCode() != HttpStatus.OK) {
        throw new ResponseStatusException(exchange.getStatusCode());
      }
      return exchange.getBody();

    }
    if (timePeriod.equals("month")) {

      ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplate.exchange(
          RUNNING_RATES_BY_TIME3, HttpMethod.GET, request,
          new ParameterizedTypeReference<>() {
          });
      if (exchange.getStatusCode() != HttpStatus.OK) {
        throw new ResponseStatusException(exchange.getStatusCode());
      }
      return exchange.getBody();
    }

    return null;
  }


}
