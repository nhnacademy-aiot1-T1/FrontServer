package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.dto.motor.MotorDto;
import com.nhnacademy.front.server.dto.motorInfoOverview.MotorInfoOverviewDto;
import com.nhnacademy.front.server.dto.sector.SectorDto;
import com.nhnacademy.front.server.service.MotorService;
import com.nhnacademy.front.server.service.SectorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MotorListController {

  private final MotorService motorService;
  private final SectorService sectorservice;

  @GetMapping("/SectorDetail")
  public String sectorDetail(Model model) {

    List<MotorDto> motors = motorService.getMotors().getMotors();
    List<SectorDto> sectorsInfo = sectorservice.getSectorsInfo().getSectors();
    MotorInfoOverviewDto motorInfoOverviewDto = motorService.getMotorOverview();

    model.addAttribute("sectorInfo", sectorsInfo);
    model.addAttribute("motors", motors);
    model.addAttribute("motorInfoOverview", motorInfoOverviewDto);
    return "AmotorList";
  }
}
