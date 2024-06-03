package com.nhnacademy.front.server.dto.sector;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class SectorsDto {

  private List<SectorDto> sectors;

//  @JsonCreator
//  public SectorsDto(List<SectorDto> sectors) {
//    this.sectors = sectors;
//  }
}
