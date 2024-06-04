package com.nhnacademy.front.server.dto.controlLog;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ControlLogDto {

  private Long motorId;

  private String motorName;

  private String sensorType;

  private double score;

  private String time;
}
