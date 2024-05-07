package com.nhnacademy.front.server.service;

import com.nhnacademy.front.server.adapter.MonitoringAdaptor;
import com.nhnacademy.front.server.dto.MotorInfoOverviewDto;
import com.nhnacademy.front.server.dto.MotorsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MotorService {

  private final MonitoringAdaptor monitoringAdaptor;

  public MotorInfoOverviewDto getMotorOverview(){
    return monitoringAdaptor.getMotorInfoOverview().getData();
  }

  public MotorsDto getMotors(){
    return monitoringAdaptor.getMotors().getData();
  }


}
