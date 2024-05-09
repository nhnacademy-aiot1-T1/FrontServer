package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.dto.MotorDetailDto;
import com.nhnacademy.front.server.dto.MotorDto;
import com.nhnacademy.front.server.dto.MotorScoreDto;
import com.nhnacademy.front.server.dto.MotorScoresDto;
import com.nhnacademy.front.server.dto.MotorsDto;
import com.nhnacademy.front.server.dto.SectorDto;
import com.nhnacademy.front.server.dto.SensorDto;
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
    List<SensorDto> sensorList = motorService.getMotorDetail(motorId).getSensors();
    List<MotorDto> motors = motorService.getMotors().getMotors();
    List<SectorDto> sectorInfo = sectorService.getSectorsInfo().getSectors();

    MotorScoresDto motorScoresDto = motorService.getMotorScores(motorId);
    List<MotorScoreDto> scores = motorScoresDto.getScores();

    model.addAttribute("sensorList", sensorList);
    model.addAttribute("motors", motors);
    model.addAttribute("sectorInfo", sectorInfo);
    model.addAttribute("motorScores", scores);
    model.addAttribute("motorScoresDto", motorScoresDto);

    return "AmotorDetail";
  }


}
