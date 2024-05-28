package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class SectorManagementController {

  private final SectorService sectorService;

  private String REDIRECT = "redirect:";


  @PostMapping("/registSector")
  public String registSector(@RequestParam String sectorNameInput) {
    sectorService.registSector(sectorNameInput);

    return "AmotorList";
  }

}
