package com.nhnacademy.front.server.dto.controlLog;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ControlLogsDto {


  private final int total;

  private final int page;

  private final int size;

  private List<ControlLogDto> logs;

}
