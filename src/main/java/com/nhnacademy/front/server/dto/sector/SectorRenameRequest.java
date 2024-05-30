package com.nhnacademy.front.server.dto.sector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SectorRenameRequest {

  private Long sectorId;
  private String sectorName;

}

