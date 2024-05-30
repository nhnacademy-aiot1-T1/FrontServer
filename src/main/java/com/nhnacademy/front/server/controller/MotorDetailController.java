package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.dto.motorDetail.MotorDetailDto;
import com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod.MotorsRunningRateData;
import com.nhnacademy.front.server.dto.sector.SectorDto;
import com.nhnacademy.front.server.dto.motorDetail.SensorDto;
import com.nhnacademy.front.server.service.MotorService;
import com.nhnacademy.front.server.service.SectorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MotorDetailController {

  private final MotorService motorService;
  private final SectorService sectorService;

  @GetMapping("/SectorDetail/MotorDetail")
  public String motorDetail(Model model, @RequestParam("motorId") Long motorId) {
    MotorDetailDto motorDetailDto = motorService.getMotorDetail(motorId);
    List<SensorDto> sensorList = motorService.getMotorDetail(motorId).getSensors();
    List<SectorDto> sectorsInfo = sectorService.getSectorsInfo().getSectors();
    List<MotorsRunningRateData> individualMotorRunningRates_day = motorService.getIndividualMotorsRunningRatesByTimePeriod(
        motorId, "day").getRates();
    List<MotorsRunningRateData> individualMotorRunningRates_week = motorService.getIndividualMotorsRunningRatesByTimePeriod(
        motorId, "week").getRates();
    List<MotorsRunningRateData> individualMotorRunningRates_month = motorService.getIndividualMotorsRunningRatesByTimePeriod(
        motorId, "month").getRates();

    model.addAttribute("sensorList", sensorList);
    model.addAttribute("sectorsInfo", sectorsInfo);
    model.addAttribute("motorDetail", motorDetailDto);
    model.addAttribute("individualMotorRunningRates_day", individualMotorRunningRates_day);
    model.addAttribute("individualMotorRunningRates_week", individualMotorRunningRates_week);
    model.addAttribute("individualMotorRunningRates_month", individualMotorRunningRates_month);

    return "AmotorDetail";
  }


}
