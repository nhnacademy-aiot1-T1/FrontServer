package com.nhnacademy.front.server.adapter;


import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.dto.MotorDetailDto;
import com.nhnacademy.front.server.dto.MotorInfoOverviewDto;
import com.nhnacademy.front.server.dto.MotorScoresDto;
import com.nhnacademy.front.server.dto.MotorsDto;
import com.nhnacademy.front.server.dto.SectorsDto;

public interface MonitoringAdaptor {


  CommonResponse<MotorInfoOverviewDto> getMotorInfoOverview();

  //  CommonResponse<SectorsDto> getSectors();
  CommonResponse<MotorsDto> getMotors();

  CommonResponse<SectorsDto> getSectorsInfo();

  CommonResponse<MotorDetailDto> getMotorDetail(Long motorId);

  CommonResponse<MotorScoresDto> getMotorScores(Long motorId);

}