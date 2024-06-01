package com.nhnacademy.front.server.service;

import com.nhnacademy.front.server.adapter.MonitoringAdaptor;
import com.nhnacademy.front.server.dto.motorDetail.MotorDetailDto;
import com.nhnacademy.front.server.dto.motorInfoOverview.MotorInfoOverviewDto;
import com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod.MotorsRunningRateDataRequest;
import com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod.MotorsRunningRatesByTimePeriod;
import com.nhnacademy.front.server.dto.motorScore.MotorScoresDto;
import com.nhnacademy.front.server.dto.motor.MotorsDto;
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
      MotorsRunningRateDataRequest motorsRunningRateDataRequest) {
    return monitoringAdaptor.getMotorsRunningRatesByTimePeriod(motorsRunningRateDataRequest)
        .getData();
  }

  public MotorsRunningRatesByTimePeriod getIndividualMotorsRunningRatesByTimePeriod(
      Long motorId, MotorsRunningRateDataRequest motorsRunningRateDataRequest) {
    return monitoringAdaptor.getIndividualMotorsRunningRatesByTimePeriod(motorId,
        motorsRunningRateDataRequest).getData();
  }
}
