package com.nhnacademy.front.server.service;

import com.nhnacademy.front.server.adapter.MonitoringAdaptor;
import com.nhnacademy.front.server.dto.motorDetail.MotorDetailDto;
import com.nhnacademy.front.server.dto.motorInfoOverview.MotorInfoOverviewDto;
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

  public MotorScoresDto getMotorScores(Long motorId) {
    return monitoringAdaptor.getMotorScores(motorId).getData();
  }

  public MotorsRunningRatesByTimePeriod getMotorsRunningRatesByTimePeriod(String timePeriod) {
    return monitoringAdaptor.getMotorsRunningRatesByTimePeriod(timePeriod).getData();
  }

  public MotorsRunningRatesByTimePeriod getIndividualMotorsRunningRatesByTimePeriod(
      Long motorId, String timePeriod) {
    return monitoringAdaptor.getIndividualMotorsRunningRatesByTimePeriod(motorId, timePeriod)
        .getData();
  }
}
