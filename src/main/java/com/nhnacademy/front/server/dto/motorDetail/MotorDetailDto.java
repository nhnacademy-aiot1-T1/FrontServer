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
  private String sectorName;
  private Boolean isOn;
  private Boolean isNormal;
  private List<SensorDto> sensors;
}
