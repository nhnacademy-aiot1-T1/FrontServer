package com.nhnacademy.front.server.controller;

import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.dto.motorDetail.MotorDetailDto;
import com.nhnacademy.front.server.dto.motorDetail.SensorDto;
import com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod.MotorsRunningRateData;
import com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod.MotorsRunningRateDataRequest;
import com.nhnacademy.front.server.dto.sector.SectorDto;
import com.nhnacademy.front.server.dto.sensor.SensorDataDto;
import com.nhnacademy.front.server.dto.sensor.SensorScoreDto;
import com.nhnacademy.front.server.service.MotorService;
import com.nhnacademy.front.server.service.SectorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
public class MotorDetailController {

  private final MotorService motorService;
  private final SectorService sectorService;
  private final String SENSOR_SCORE_URL = "/api/monitor/motors/{motorId}/sensors/{sensorId}/scores";
  private final String SENSOR_DATA_URL = "/api/monitor/motors/{motorId}/sensors/{sensorId}/data";
  private final RestTemplate restTemplate;

  @GetMapping("/SectorDetail/MotorDetail")
  public String motorDetail(Model model, @RequestParam("motorId") Long motorId) {
    MotorDetailDto motorDetailDto = motorService.getMotorDetail(motorId);
    List<SensorDto> sensorList = motorService.getMotorDetail(motorId).getSensors();
    List<SectorDto> sectorsInfo = sectorService.getSectorsInfo().getSectors();
    List<MotorsRunningRateData> individualMotorRunningRates_day = motorService.getIndividualMotorsRunningRatesByTimePeriod(
        motorId, new MotorsRunningRateDataRequest("day")).getRates();
    List<MotorsRunningRateData> individualMotorRunningRates_week = motorService.getIndividualMotorsRunningRatesByTimePeriod(
        motorId, new MotorsRunningRateDataRequest("week")).getRates();
    List<MotorsRunningRateData> individualMotorRunningRates_month = motorService.getIndividualMotorsRunningRatesByTimePeriod(
        motorId, new MotorsRunningRateDataRequest("month")).getRates();

    model.addAttribute("sensorList", sensorList);
    model.addAttribute("sectorsInfo", sectorsInfo);
    model.addAttribute("motorDetail", motorDetailDto);
    model.addAttribute("individualMotorRunningRates_day", individualMotorRunningRates_day);
    model.addAttribute("individualMotorRunningRates_week", individualMotorRunningRates_week);
    model.addAttribute("individualMotorRunningRates_month", individualMotorRunningRates_month);

    return "AmotorDetail";
  }

  @GetMapping("/api/monitor/motors/{motorId}/sensors/{sensorId}/scores")
  @ResponseBody
  public SensorScoreDto getSensorScores(Model model,
      @PathVariable Long motorId,
      @PathVariable Long sensorId) {

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

    String url = SENSOR_SCORE_URL.replace("{motorId}", motorId.toString())
        .replace("{sensorId}", sensorId.toString());

    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);
    return restTemplate.exchange(
        "https://run.mocky.io/v3/2556d6eb-cde1-4ca5-aa91-e02d36ff96fb", HttpMethod.GET, request,
        new ParameterizedTypeReference<CommonResponse<SensorScoreDto>>() {
        }).getBody().getData();


  }

  @GetMapping("/api/monitor/motors/{motorId}/sensors/{sensorId}/data")
  public ResponseEntity<CommonResponse<SensorDataDto>> getSensorData(Model model,
      @PathVariable Long motorId,
      @PathVariable Long sensorId) {

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

    String url = SENSOR_DATA_URL.replace("{motorId}", motorId.toString())
        .replace("{sensorId}", sensorId.toString());

    HttpEntity<Object> request = new HttpEntity<>(httpHeaders);

    return restTemplate.exchange(
        "https://run.mocky.io/v3/08b164d4-e6e4-49aa-b0e9-1281404bf52a", HttpMethod.GET, request,
        new ParameterizedTypeReference<>() {
        });
  }

}
