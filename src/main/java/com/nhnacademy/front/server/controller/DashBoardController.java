package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.dto.motorInfoOverview.MotorInfoOverviewDto;
import com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod.MotorsRunningRateDataRequest;
import com.nhnacademy.front.server.dto.sector.SectorDto;
import com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod.MotorsRunningRateData;
import com.nhnacademy.front.server.service.MotorService;
import com.nhnacademy.front.server.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DashBoardController {

  private final MotorService motorService;
  private final SectorService sectorService;

  @GetMapping("/home")
  public String home(Model model) {

    MotorInfoOverviewDto motorOverviewDTO = motorService.getMotorOverview();
    List<SectorDto> sectorsInfo = sectorService.getSectorsInfo().getSectors();

    List<MotorsRunningRateData> dayRunningRates = motorService.getMotorsRunningRatesByTimePeriod(
        new MotorsRunningRateDataRequest("day")).getRates();
    List<MotorsRunningRateData> weekRunningRates = motorService.getMotorsRunningRatesByTimePeriod(
        new MotorsRunningRateDataRequest("week")).getRates();
    List<MotorsRunningRateData> monthRunningRates = motorService.getMotorsRunningRatesByTimePeriod(
        new MotorsRunningRateDataRequest("month")).getRates();

    model.addAttribute("motorOverview", motorOverviewDTO);
    model.addAttribute("sectorsInfo", sectorsInfo);
    model.addAttribute("dayRunningRates", dayRunningRates);
    model.addAttribute("weekRunningRates", weekRunningRates);
    model.addAttribute("monthRunningRates", monthRunningRates);
    // TEST
    return "dashboard";
  }
}
