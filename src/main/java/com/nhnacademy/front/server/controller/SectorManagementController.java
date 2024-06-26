package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.dto.sector.SectorRegisterRequest;
import com.nhnacademy.front.server.dto.sector.SectorRenameRequest;
import com.nhnacademy.front.server.service.SectorService;
import com.nhnacademy.front.server.util.WebUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SectorManagementController {

  private final SectorService sectorService;

  private static final String SECTOR_DETAIL = "/SectorDetail";

  @PostMapping("/registSector")
  public String registSector(@RequestParam String sectorNameInput) {

    SectorRegisterRequest sectorRegisterRequest = new SectorRegisterRequest(sectorNameInput);

    sectorService.registSector(sectorRegisterRequest);

    return WebUtils.REDIRECT_PREFIX + SECTOR_DETAIL;
  }

  @PutMapping("/renameSector")
  public String renameSector(Long sectorId,
      @RequestParam("newSectorName") String newSectorName) {

    SectorRenameRequest sectorRenameRequest = new SectorRenameRequest(sectorId, newSectorName);

    sectorService.renameSector(sectorId, sectorRenameRequest);

    return WebUtils.REDIRECT_PREFIX + SECTOR_DETAIL;
  }


  @DeleteMapping("/removeSector")
  public String removeSectorTemp(@ModelAttribute("sectorId") Long sectorId) {

    log.info("Removing sector Temp inside {}", sectorId);
    sectorService.removeSector(sectorId);

    return WebUtils.REDIRECT_PREFIX + SECTOR_DETAIL;
  }
}
