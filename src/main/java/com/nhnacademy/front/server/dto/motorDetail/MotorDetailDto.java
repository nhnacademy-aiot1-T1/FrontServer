package com.nhnacademy.front.server.dto.motorDetail;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MotorDetailDto {

  private Long motorId;
  private String motorName;
  private Long sectorId;
  private Boolean isOn;
  private String isNormal;
  private List<SensorDto> sensors;
}
