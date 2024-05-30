package com.nhnacademy.front.server.dto.controlLog;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ControlLogDto {

  private Long logId;
  private String sectorName;
  private String motorName;
  private String sensorType;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
  private String time;
  private String status;

}
