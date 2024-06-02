package com.nhnacademy.front.server.adapter;


import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.dto.controlLog.ControlLogsDto;
import com.nhnacademy.front.server.dto.motor.MotorsDto;
import com.nhnacademy.front.server.dto.motorDetail.MotorDetailDto;
import com.nhnacademy.front.server.dto.motorInfoOverview.MotorInfoOverviewDto;
import com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod.MotorsRunningRateDataRequest;
import com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod.MotorsRunningRatesByTimePeriod;
import com.nhnacademy.front.server.dto.sector.SectorManagementDto;
import com.nhnacademy.front.server.dto.sector.SectorRegisterRequest;
import com.nhnacademy.front.server.dto.sector.SectorRenameRequest;
import com.nhnacademy.front.server.dto.sector.SectorsDto;

public interface MonitoringAdaptor {

  CommonResponse<MotorInfoOverviewDto> getMotorInfoOverview();

  CommonResponse<MotorsDto> getMotors();

  CommonResponse<SectorsDto> getSectorsInfo();

  CommonResponse<MotorDetailDto> getMotorDetail(Long motorId);

//  CommonResponse<MotorScoresDto> getMotorScores(Long motorId);

  CommonResponse<ControlLogsDto> getControlLogs();

  CommonResponse<MotorsRunningRatesByTimePeriod> getMotorsRunningRatesByTimePeriod(
      MotorsRunningRateDataRequest motorsRunningRateDataRequest);

  CommonResponse<MotorsRunningRatesByTimePeriod> getIndividualMotorsRunningRatesByTimePeriod(
      Long motorId, MotorsRunningRateDataRequest motorsRunningRateDataRequest);

  CommonResponse<SectorManagementDto> registSector(SectorRegisterRequest sectorRegisterRequest);

  CommonResponse<SectorManagementDto> renameSector(Long sectorId,
      SectorRenameRequest sectorRenameRequest);

  CommonResponse<SectorManagementDto> removeSector(Long sectorId);
}