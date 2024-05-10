package com.nhnacademy.front.server.dto.controlLog;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ControlLogsDto {


  private List<ControlLogDto> sensorStatusList;

  @JsonCreator
  public ControlLogsDto(List<ControlLogDto> controlLogs) {
    this.sensorStatusList = controlLogs;
  }
}
