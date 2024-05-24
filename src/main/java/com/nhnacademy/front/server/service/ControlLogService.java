package com.nhnacademy.front.server.service;

import com.nhnacademy.front.server.adapter.MonitoringAdaptor;
import com.nhnacademy.front.server.dto.controlLog.ControlLogsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ControlLogService {

  private final MonitoringAdaptor monitoringAdaptor;

  public ControlLogsDto getControlLogs() {
    return monitoringAdaptor.getControlLogs().getData();
  }
}
