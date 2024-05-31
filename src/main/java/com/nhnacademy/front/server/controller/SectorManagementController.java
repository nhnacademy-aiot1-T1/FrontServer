package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.dto.sector.SectorRegisterRequest;
import com.nhnacademy.front.server.dto.sector.SectorRemoveRequest;
import com.nhnacademy.front.server.dto.sector.SectorRenameRequest;
import com.nhnacademy.front.server.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class SectorManagementController {

  private final SectorService sectorService;

  private String REDIRECT = "redirect:";


  @PostMapping("/registSector")
  public String registSector(@RequestParam String sectorNameInput) {

    SectorRegisterRequest sectorRegisterRequest = new SectorRegisterRequest(sectorNameInput);

    sectorService.registSector(sectorRegisterRequest);

    return REDIRECT + "/SectorDetail";
  }

  @PutMapping("/renameSector")
  public String renameSector(Long sectorId,
      @RequestParam("newSectorName") String newSectorName) {

    SectorRenameRequest sectorRenameRequest = new SectorRenameRequest(sectorId, newSectorName);

    sectorService.renameSector(sectorRenameRequest);

    return REDIRECT + "/SectorDetail";
  }

  @DeleteMapping("/removeSector")
  public String removeSector(Long sectorId) {

    sectorService.removeSector(sectorId);

    return REDIRECT + "/SectorDetail";
  }

}
