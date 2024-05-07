package com.nhnacademy.front.server.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class SectorsDto {

  private List<SectorDto> sectors;

//  @JsonCreator
//  public SectorsDto(List<SectorDto> sectors) {
//    this.sectors = sectors;
//  }
}
