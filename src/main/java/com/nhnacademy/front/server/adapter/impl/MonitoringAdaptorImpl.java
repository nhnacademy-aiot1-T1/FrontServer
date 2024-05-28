package com.nhnacademy.front.server.adapter.impl;

import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.adapter.MonitoringAdaptor;
import com.nhnacademy.front.server.dto.controlLog.ControlLogsDto;
import com.nhnacademy.front.server.dto.motorDetail.MotorDetailDto;
import com.nhnacademy.front.server.dto.motorInfoOverview.MotorInfoOverviewDto;
import com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod.MotorsRunningRatesByTimePeriod;
import com.nhnacademy.front.server.dto.motorScore.MotorScoresDto;
import com.nhnacademy.front.server.dto.motor.MotorsDto;
import com.nhnacademy.front.server.dto.sector.SectorManagementDto;
import com.nhnacademy.front.server.dto.sector.SectorsDto;
import javax.servlet.http.HttpSession;
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

  public static final String MOTOR_INFO_OVERVIEW = "https://run.mocky.io/v3/08ec999b-5345-4075-84b9-2ce8619d71fc";
  public static final String SECTORS_INFO = "https://run.mocky.io/v3/60b8f744-27ca-44d4-924a-f63119ed8d3e";
  public static final String MOTORS = "https://run.mocky.io/v3/da4d361e-37ee-487a-a9c8-8c471a114638";
  public static final String MOTOR_DETAIL = "https://run.mocky.io/v3/21547def-ce33-46f4-9295-0221dcfb5761";
  public static final String MOTOR_SCORES = "https://run.mocky.io/v3/ef52835f-8b16-4d70-abbd-c1cde05d6fd5";
  public static final String CONTR0L_LOGS = "https://run.mocky.io/v3/f76094b7-f650-431f-a9e4-bb1d5224b5fe";
  public static final String RUNNING_RATES_BY_TIME1 = "https://run.mocky.io/v3/fc91f26c-1883-41b3-a65d-406681cc7060"; // day data
  public static final String RUNNING_RATES_BY_TIME2 = "https://run.mocky.io/v3/8eda357b-8c12-41ff-aa6f-87b7940df3fb"; // week data
  public static final String RUNNING_RATES_BY_TIME3 = "https://run.mocky.io/v3/eae23440-8507-4205-9efd-e06fe6daa6be"; // month data

  public static final String INDIVIDUAL_RUNNING_RATES_BY_TIME1 = "https://run.mocky.io/v3/92a16e33-fc73-44b8-bf0a-c3b37f3c78eb";
  public static final String INDIVIDUAL_RUNNING_RATES_BY_TIME2 = "https://run.mocky.io/v3/bd1925c9-ece8-4087-86d9-ffae0e0c1ae8";
  public static final String INDIVIDUAL_RUNNING_RATES_BY_TIME3 = "https://run.mocky.io/v3/890de582-586d-4ef7-9d26-5bb6a39e8b34";

  // TODO 요청 주소 입력해줘야함
  public static final String REGIST_SECTOR = "";

  private final RestTemplate restTemplateMocky;

  // 종합 정보
  @Override
  public CommonResponse<MotorInfoOverviewDto> getMotorInfoOverview() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);

    ResponseEntity<CommonResponse<MotorInfoOverviewDto>> exchange = restTemplateMocky.exchange(
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

    ResponseEntity<CommonResponse<SectorsDto>> exchange = restTemplateMocky.exchange(
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

    ResponseEntity<CommonResponse<MotorsDto>> exchange = restTemplateMocky.exchange(
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

    ResponseEntity<CommonResponse<MotorDetailDto>> exchange = restTemplateMocky.exchange(
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

    ResponseEntity<CommonResponse<MotorScoresDto>> exchange = restTemplateMocky.exchange(
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

    ResponseEntity<CommonResponse<ControlLogsDto>> exchange = restTemplateMocky.exchange(
        CONTR0L_LOGS, HttpMethod.GET, request,
        new ParameterizedTypeReference<>() {
        });

    if (exchange.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();


  }

  // 총 모터 가동률
  @Override
  public CommonResponse<MotorsRunningRatesByTimePeriod> getMotorsRunningRatesByTimePeriod(
      String timePeriod) {

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);

    if (timePeriod.equals("day")) {

      ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplateMocky.exchange(
          RUNNING_RATES_BY_TIME1, HttpMethod.GET, request,
          new ParameterizedTypeReference<>() {
          });
      if (exchange.getStatusCode() != HttpStatus.OK) {
        throw new ResponseStatusException(exchange.getStatusCode());
      }
      return exchange.getBody();

    }
    if (timePeriod.equals("week")) {

      ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplateMocky.exchange(
          RUNNING_RATES_BY_TIME2, HttpMethod.GET, request,
          new ParameterizedTypeReference<>() {
          });
      if (exchange.getStatusCode() != HttpStatus.OK) {
        throw new ResponseStatusException(exchange.getStatusCode());
      }
      return exchange.getBody();

    }
    if (timePeriod.equals("month")) {

      ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplateMocky.exchange(
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

  // 개별 모터 가동률
  @Override
  public CommonResponse<MotorsRunningRatesByTimePeriod> getIndividualMotorsRunningRatesByTimePeriod(
      Long motorId, String timePeriod) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);

    if (timePeriod.equals("day")) {

      ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplateMocky.exchange(
          INDIVIDUAL_RUNNING_RATES_BY_TIME1, HttpMethod.GET, request,
          new ParameterizedTypeReference<>() {
          });
      if (exchange.getStatusCode() != HttpStatus.OK) {
        throw new ResponseStatusException(exchange.getStatusCode());
      }
      return exchange.getBody();

    }
    if (timePeriod.equals("week")) {

      ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplateMocky.exchange(
          INDIVIDUAL_RUNNING_RATES_BY_TIME2, HttpMethod.GET, request,
          new ParameterizedTypeReference<>() {
          });
      if (exchange.getStatusCode() != HttpStatus.OK) {
        throw new ResponseStatusException(exchange.getStatusCode());
      }
      return exchange.getBody();

    }
    if (timePeriod.equals("month")) {

      ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplateMocky.exchange(
          INDIVIDUAL_RUNNING_RATES_BY_TIME3, HttpMethod.GET, request,
          new ParameterizedTypeReference<>() {
          });
      if (exchange.getStatusCode() != HttpStatus.OK) {
        throw new ResponseStatusException(exchange.getStatusCode());
      }
      return exchange.getBody();
    }

    return null;
  }

  @Override
  public CommonResponse<SectorManagementDto> registSector(String sectorName) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<Object> request = new HttpEntity<>(sectorName, httpHeaders);

    ResponseEntity<CommonResponse<SectorManagementDto>> exchange = restTemplateMocky.exchange(
        REGIST_SECTOR, HttpMethod.POST, request,
        new ParameterizedTypeReference<>() {
        });

    if (exchange.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();


  }


}
