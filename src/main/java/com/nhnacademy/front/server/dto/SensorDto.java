package com.nhnacademy.front.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class SensorDto {

  Long sensorId;
  String sensorType;
  double hourMean;

}
