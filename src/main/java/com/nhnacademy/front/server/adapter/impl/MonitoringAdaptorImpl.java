package com.nhnacademy.front.server.adapter.impl;

import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.adapter.MonitoringAdaptor;
import com.nhnacademy.front.server.config.ApiPathProperties;
import com.nhnacademy.front.server.dto.controlLog.ControlLogsDto;
import com.nhnacademy.front.server.dto.motor.MotorsDto;
import com.nhnacademy.front.server.dto.motorDetail.MotorDetailDto;
import com.nhnacademy.front.server.dto.motorInfoOverview.MotorInfoOverviewDto;
import com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod.MotorsRunningRateDataRequest;
import com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod.MotorsRunningRatesByTimePeriod;
import com.nhnacademy.front.server.dto.sector.SectorManagementDto;
import com.nhnacademy.front.server.dto.sector.SectorRegisterRequest;
import com.nhnacademy.front.server.dto.sector.SectorRemoveRequest;
import com.nhnacademy.front.server.dto.sector.SectorRenameRequest;
import com.nhnacademy.front.server.dto.sector.SectorsDto;
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
public class MonitoringAdaptorImpl implements MonitoringAdaptor {

  private final ApiPathProperties pathProperties;

  private final RestTemplate restTemplate;

  // 종합 정보
  @Override
  public CommonResponse<MotorInfoOverviewDto> getMotorInfoOverview() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);

    ResponseEntity<CommonResponse<MotorInfoOverviewDto>> exchange = restTemplate.exchange(
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

    ResponseEntity<CommonResponse<SectorsDto>> exchange = restTemplate.exchange(
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

    ResponseEntity<CommonResponse<MotorsDto>> exchange = restTemplate.exchange(
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

    String url = pathProperties.getMotorDetail()
        .replace("{motorId}", motorId.toString());

    ResponseEntity<CommonResponse<MotorDetailDto>> exchange = restTemplate.exchange(
        url, HttpMethod.GET, request,
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
        pathProperties.getControlLogs(), HttpMethod.GET, request,
        new ParameterizedTypeReference<>() {
        });

    if (exchange.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();


  }

  // 총 모터 가동률

  // TODO  쿼리쓰트링으로 쑤쩡

  @Override
  public CommonResponse<MotorsRunningRatesByTimePeriod> getMotorsRunningRatesByTimePeriod(
      MotorsRunningRateDataRequest motorsRunningRateDataRequest) {

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    HttpEntity<Object> request = new HttpEntity<>(motorsRunningRateDataRequest, httpHeaders);

    ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplate.exchange(
        pathProperties.getRunningRateByTime(), HttpMethod.GET, request,
        new ParameterizedTypeReference<>() {
        });
    if (exchange.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }
    return exchange.getBody();

  }

  // 개별 모터 가동률

  // TODO  쿼리쓰트링으로 쑤쩡
  @Override
  public CommonResponse<MotorsRunningRatesByTimePeriod> getIndividualMotorsRunningRatesByTimePeriod(
      Long motorId, MotorsRunningRateDataRequest motorsRunningRateDataRequest) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    HttpEntity<Object> request = new HttpEntity<>(motorsRunningRateDataRequest, httpHeaders);

    String url = pathProperties.getIndividualRunningRateByTime()
        .replace("{motorId}", motorId.toString());

    ResponseEntity<CommonResponse<MotorsRunningRatesByTimePeriod>> exchange = restTemplate.exchange(
        url + "?duration=", HttpMethod.GET, request,
        new ParameterizedTypeReference<>() {
        });
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

    ResponseEntity<CommonResponse<SectorManagementDto>> exchange = restTemplate.exchange(
        pathProperties.getSectorRegister(), HttpMethod.POST, request,
        new ParameterizedTypeReference<>() {
        });

    if (exchange.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();


  }

  @Override
  public CommonResponse<SectorManagementDto> renameSector(Long sectorId,
      SectorRenameRequest sectorRenameRequest) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<SectorRenameRequest> request = new HttpEntity<>(sectorRenameRequest,
        httpHeaders);

    String url = pathProperties.getSectorRename()
        .replace("{sectorId}", sectorId.toString());

    ResponseEntity<CommonResponse<SectorManagementDto>> exchange = restTemplate.exchange(
        url, HttpMethod.PUT, request,
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

    String url = pathProperties.getSectorRemove()
        .replace("{sectorId}", sectorId.toString());

    ResponseEntity<CommonResponse<SectorManagementDto>> exchange = restTemplate.exchange(
        url, HttpMethod.DELETE, HttpRequest,
        new ParameterizedTypeReference<>() {
        });

    if (exchange.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(exchange.getStatusCode());
    }

    return exchange.getBody();
  }

}
