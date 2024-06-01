package com.nhnacademy.front.server.dto.controlLog;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class ControlLogsDto {


  private final int total;

  private final int page;

  private final int size;

  private List<ControlLogDto> logs;

}
