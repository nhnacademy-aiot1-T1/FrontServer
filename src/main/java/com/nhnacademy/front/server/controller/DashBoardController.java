package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.dto.MotorInfoOverviewDto;
import com.nhnacademy.front.server.dto.SectorDto;
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
  public String home(Model model){
    MotorInfoOverviewDto motorOverviewDTO = motorService.getMotorOverview();
    List<SectorDto> sectorsInfo = sectorService.getSectorsInfo().getSectors();

    model.addAttribute("motorOverview", motorOverviewDTO);
    model.addAttribute("sectorsInfo", sectorsInfo);
    // TEST
    return "dashboard";
  }
}
