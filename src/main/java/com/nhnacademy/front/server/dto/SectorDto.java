package com.nhnacademy.front.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectorDto {

  private Long sectorId;
  private String sectorName;
  private int normalCount;
  private int anomalyCount;

}
