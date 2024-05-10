package com.nhnacademy.front.server.dto.motor;

import lombok.AllArgsConstructor;
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
