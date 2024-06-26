package com.nhnacademy.front.server.service;

import com.nhnacademy.front.server.adapter.MonitoringAdaptor;
import com.nhnacademy.front.server.dto.motor.MotorsDto;
import com.nhnacademy.front.server.dto.motorDetail.MotorDetailDto;
import com.nhnacademy.front.server.dto.motorInfoOverview.MotorInfoOverviewDto;
import com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod.MotorsRunningRateDataRequest;
import com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod.MotorsRunningRatesByTimePeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MotorService {

  private final MonitoringAdaptor monitoringAdaptor;

  public MotorInfoOverviewDto getMotorOverview() {
    return monitoringAdaptor.getMotorInfoOverview().getData();
  }

  public MotorsDto getMotors() {
    return monitoringAdaptor.getMotors().getData();
  }

  public MotorDetailDto getMotorDetail(Long motorId) {
    return monitoringAdaptor.getMotorDetail(motorId).getData();
  }

  public MotorsRunningRatesByTimePeriod getMotorsRunningRatesByTimePeriod(
      String duration) {
    return monitoringAdaptor.getMotorsRunningRatesByTimePeriod(duration)
        .getData();
  }

  public MotorsRunningRatesByTimePeriod getIndividualMotorsRunningRatesByTimePeriod(
      Long motorId, String duration) {
    return monitoringAdaptor.getIndividualMotorsRunningRatesByTimePeriod(motorId,
        duration).getData();
  }
}
