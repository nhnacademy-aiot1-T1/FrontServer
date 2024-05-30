package com.nhnacademy.front.server.adapter;


import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.dto.controlLog.ControlLogsDto;
import com.nhnacademy.front.server.dto.motorDetail.MotorDetailDto;
import com.nhnacademy.front.server.dto.motorInfoOverview.MotorInfoOverviewDto;
import com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod.MotorsRunningRatesByTimePeriod;
import com.nhnacademy.front.server.dto.motorScore.MotorScoresDto;
import com.nhnacademy.front.server.dto.motor.MotorsDto;
import com.nhnacademy.front.server.dto.sector.SectorManagementDto;
import com.nhnacademy.front.server.dto.sector.SectorRegisterRequest;
import com.nhnacademy.front.server.dto.sector.SectorRemoveRequest;
import com.nhnacademy.front.server.dto.sector.SectorRenameRequest;
import com.nhnacademy.front.server.dto.sector.SectorsDto;
import javax.xml.stream.events.Characters;
import org.springframework.web.bind.annotation.RequestParam;

public interface MonitoringAdaptor {

  CommonResponse<MotorInfoOverviewDto> getMotorInfoOverview();

  CommonResponse<MotorsDto> getMotors();

  CommonResponse<SectorsDto> getSectorsInfo();

  CommonResponse<MotorDetailDto> getMotorDetail(Long motorId);

  CommonResponse<MotorScoresDto> getMotorScores(Long motorId);

  CommonResponse<ControlLogsDto> getControlLogs();

  CommonResponse<MotorsRunningRatesByTimePeriod> getMotorsRunningRatesByTimePeriod(
      String timePeriod);

  CommonResponse<MotorsRunningRatesByTimePeriod> getIndividualMotorsRunningRatesByTimePeriod(
      @RequestParam Long motorId, String timePeriod);

  CommonResponse<SectorManagementDto> registSector(SectorRegisterRequest sectorRegisterRequest);

  CommonResponse<SectorManagementDto> renameSector(SectorRenameRequest sectorRenameRequest);

  CommonResponse<SectorManagementDto> removeSector(SectorRemoveRequest sectorRemoveRequest);
}