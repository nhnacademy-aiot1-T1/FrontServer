package com.nhnacademy.front.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MotorDetailController {

  @GetMapping("/SectorDetail/MotorDetail")
  public String sectorDetail(Model model) {


    return "AmotorDetail";
  }



}
