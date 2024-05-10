package com.nhnacademy.front.server.adapter;


import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.dto.controlLog.ControlLogsDto;
import com.nhnacademy.front.server.dto.motorDetail.MotorDetailDto;
import com.nhnacademy.front.server.dto.motorInfoOverview.MotorInfoOverviewDto;
import com.nhnacademy.front.server.dto.motorScore.MotorScoresDto;
import com.nhnacademy.front.server.dto.motor.MotorsDto;
import com.nhnacademy.front.server.dto.SectorsDto;

public interface MonitoringAdaptor {

  CommonResponse<MotorInfoOverviewDto> getMotorInfoOverview();

  CommonResponse<MotorsDto> getMotors();

  CommonResponse<SectorsDto> getSectorsInfo();

  CommonResponse<MotorDetailDto> getMotorDetail(Long motorId);

  CommonResponse<MotorScoresDto> getMotorScores(Long motorId);

  CommonResponse<ControlLogsDto> getControlLogs();
}