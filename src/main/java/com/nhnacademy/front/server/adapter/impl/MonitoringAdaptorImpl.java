package com.nhnacademy.front.server.adapter.impl;

import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.adapter.MonitoringAdaptor;
import com.nhnacademy.front.server.config.ApiPathProperties;
import com.nhnacademy.front.server.dto.controlLog.ControlLogsDto;
import com.nhnacademy.front.server.dto.motorDetail.MotorDetailDto;
import com.nhnacademy.front.server.dto.motorInfoOverview.MotorInfoOverviewDto;
import com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod.MotorsRunningRateDataRequest;
import com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod.MotorsRunningRatesByTimePeriod;
import com.nhnacademy.front.server.dto.motorScore.MotorScoresDto;
import com.nhnacademy.front.server.dto.motor.MotorsDto;
import com.nhnacademy.front.server.dto.sector.SectorManagementDto;
import com.nhnacademy.front.server.dto.sector.SectorRegisterRequest;
import com.nhnacademy.front.server.dto.sector.SectorRemoveRequest;
import com.nhnacademy.front.server.dto.sector.SectorRenameRequest;
import com.nhnacademy.front.server.dto.sector.SectorsDto;
import java.util.Map;
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

  private final ApiPathProperties pathProperties;

  private final RestTemplate restTemplateMocky;

  // 종합 정보
  @Override
  public CommonResponse<MotorInfoOverviewDto> getMotorInfoOverview() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);

    ResponseEntity<CommonResponse<MotorInfoOverviewDto>> exchange = restTemplateMocky.exchange(
        pathProperties.getMotorOverview(), HttpMethod.GET, request,
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
        pathProperties.getSectorInfo(), HttpMethod.GET, request,
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
        pathProperties.getMotors(), HttpMethod.GET, request,
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
        pathProperties.getMotorDetail(), HttpMethod.GET, request,
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
        pathProperties.getControlLogs(), HttpMethod.GET, request,
        new ParameterizedTypeReference<>() {
        });

    if (exchange.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();


  }

  // 총 모터 가동률
//  @Override
//  public CommonResponse<MotorsRunningRatesByTimePeriod> getMotorsRunningRatesByTimePeriod(
//      String timePeriod) {
//
//    HttpHeaders httpHeaders = new HttpHeaders();
//    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
//    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);
//
//    if (timePeriod.equals("day")) {
//
//      ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplateMocky.exchange(
//          RUNNING_RATES_BY_TIME1, HttpMethod.GET, request,
//          new ParameterizedTypeReference<>() {
//          });
//      if (exchange.getStatusCode() != HttpStatus.OK) {
//        throw new ResponseStatusException(exchange.getStatusCode());
//      }
//      return exchange.getBody();
//
//    }
//    if (timePeriod.equals("week")) {
//
//      ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplateMocky.exchange(
//          RUNNING_RATES_BY_TIME2, HttpMethod.GET, request,
//          new ParameterizedTypeReference<>() {
//          });
//      if (exchange.getStatusCode() != HttpStatus.OK) {
//        throw new ResponseStatusException(exchange.getStatusCode());
//      }
//      return exchange.getBody();
//
//    }
//    if (timePeriod.equals("month")) {
//
//      ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplateMocky.exchange(
//          RUNNING_RATES_BY_TIME3, HttpMethod.GET, request,
//          new ParameterizedTypeReference<>() {
//          });
//      if (exchange.getStatusCode() != HttpStatus.OK) {
//        throw new ResponseStatusException(exchange.getStatusCode());
//      }
//      return exchange.getBody();
//    }
//
//    return null;
//  }

  // 총 모터 가동률
  @Override
  public CommonResponse<MotorsRunningRatesByTimePeriod> getMotorsRunningRatesByTimePeriod(
      MotorsRunningRateDataRequest motorsRunningRateDataRequest) {

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    HttpEntity<Object> request = new HttpEntity<>(motorsRunningRateDataRequest, httpHeaders);

    ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplateMocky.exchange(
        pathProperties.getRunningRateByTime(), HttpMethod.GET, request,
        new ParameterizedTypeReference<>() {
        });
    if (exchange.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }
    return exchange.getBody();

  }

//  // 개별 모터 가동률
//  @Override
//  public CommonResponse<MotorsRunningRatesByTimePeriod> getIndividualMotorsRunningRatesByTimePeriod(
//      Long motorId, String timePeriod) {
//    HttpHeaders httpHeaders = new HttpHeaders();
//    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
//    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);
//
//    if (timePeriod.equals("day")) {
//
//      ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplateMocky.exchange(
//          INDIVIDUAL_RUNNING_RATES_BY_TIME1, HttpMethod.GET, request,
//          new ParameterizedTypeReference<>() {
//          });
//      if (exchange.getStatusCode() != HttpStatus.OK) {
//        throw new ResponseStatusException(exchange.getStatusCode());
//      }
//      return exchange.getBody();
//
//    }
//    if (timePeriod.equals("week")) {
//
//      ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplateMocky.exchange(
//          INDIVIDUAL_RUNNING_RATES_BY_TIME2, HttpMethod.GET, request,
//          new ParameterizedTypeReference<>() {
//          });
//      if (exchange.getStatusCode() != HttpStatus.OK) {
//        throw new ResponseStatusException(exchange.getStatusCode());
//      }
//      return exchange.getBody();
//
//    }
//    if (timePeriod.equals("month")) {
//
//      ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplateMocky.exchange(
//          INDIVIDUAL_RUNNING_RATES_BY_TIME3, HttpMethod.GET, request,
//          new ParameterizedTypeReference<>() {
//          });
//      if (exchange.getStatusCode() != HttpStatus.OK) {
//        throw new ResponseStatusException(exchange.getStatusCode());
//      }
//      return exchange.getBody();
//    }
//
//    return null;
//  }

  // 개별 모터 가동률
  @Override
  public CommonResponse<MotorsRunningRatesByTimePeriod> getIndividualMotorsRunningRatesByTimePeriod(
      Long motorId, MotorsRunningRateDataRequest motorsRunningRateDataRequest) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    HttpEntity<Object> request = new HttpEntity<>(motorsRunningRateDataRequest, httpHeaders);

    ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplateMocky.exchange(
        pathProperties.getIndividualRunningRateByTime(), HttpMethod.GET, request,
        new ParameterizedTypeReference<>() {
        }, motorId);
    if (exchange.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }
    return exchange.getBody();

  }

  @Override
  public CommonResponse<SectorManagementDto> registSector(
      SectorRegisterRequest sectorRegisterRequest) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<Object> request = new HttpEntity<>(sectorRegisterRequest, httpHeaders);

    ResponseEntity<CommonResponse<SectorManagementDto>> exchange = restTemplateMocky.exchange(
        pathProperties.getSectorRegister(), HttpMethod.POST, request,
        new ParameterizedTypeReference<>() {
        });

    if (exchange.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();


  }

  @Override
  public CommonResponse<SectorManagementDto> renameSector(SectorRenameRequest sectorRenameRequest) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<SectorRenameRequest> request = new HttpEntity<>(sectorRenameRequest,
        httpHeaders);

    ResponseEntity<CommonResponse<SectorManagementDto>> exchange = restTemplateMocky.exchange(
        pathProperties.getSectorRename(), HttpMethod.POST, request,
        new ParameterizedTypeReference<>() {
        });

    if (exchange.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();
  }

  @Override
  public CommonResponse<SectorManagementDto> removeSector(Long sectorId) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<SectorRemoveRequest> HttpRequest = new HttpEntity<>(
        httpHeaders);

    ResponseEntity<CommonResponse<SectorManagementDto>> exchange = restTemplateMocky.exchange(
        pathProperties.getSectorRemove() + sectorId, HttpMethod.DELETE, HttpRequest,
        new ParameterizedTypeReference<>() {
        });

    if (exchange.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();
  }
}
