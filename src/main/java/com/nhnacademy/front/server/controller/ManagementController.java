package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.dto.SectorDto;
import com.nhnacademy.front.server.dto.SectorsDto;
import com.nhnacademy.front.server.service.SectorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ManagementController {

  private final SectorService sectorService;

  @GetMapping("/management")
  public String getRegisterdSector(Model model) {

    SectorsDto sectorsDto = sectorService.getSectorsInfo();
    List<SectorDto> sectors = sectorsDto.getSectors();

    model.addAttribute("sectors", sectors);

    return "area-motor-management";

  }

}
