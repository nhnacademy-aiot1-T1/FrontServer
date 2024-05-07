package com.nhnacademy.front.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MotorDto {

  private Long motorId;
  private String motorName;
  private Long sectorId;
  private String status;
  private String connect;
}
