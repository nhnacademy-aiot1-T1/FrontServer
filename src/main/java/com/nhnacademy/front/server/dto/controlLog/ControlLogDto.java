package com.nhnacademy.front.server.dto.controlLog;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
public class ControlLogDto {

  private Long motorId;

  private String motorName;

  private String sensorType;

  private double score;

  private String time;
}
