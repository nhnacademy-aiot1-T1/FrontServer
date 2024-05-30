package com.nhnacademy.front.server.service;

import com.nhnacademy.front.server.adapter.MonitoringAdaptor;
import com.nhnacademy.front.server.dto.sector.SectorManagementDto;
import com.nhnacademy.front.server.dto.sector.SectorRegisterRequest;
import com.nhnacademy.front.server.dto.sector.SectorRemoveRequest;
import com.nhnacademy.front.server.dto.sector.SectorRenameRequest;
import com.nhnacademy.front.server.dto.sector.SectorsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SectorService {

  private final MonitoringAdaptor monitoringAdaptor;

  public SectorsDto getSectorsInfo() {
    return monitoringAdaptor.getSectorsInfo().getData();
  }

  public SectorManagementDto registSector(SectorRegisterRequest sectorRegisterRequest) {
    return monitoringAdaptor.registSector(sectorRegisterRequest).getData();
  }

  public SectorManagementDto renameSector(SectorRenameRequest sectorRenameRequest) {
    return monitoringAdaptor.renameSector(sectorRenameRequest).getData();
  }

  public SectorManagementDto removeSector(SectorRemoveRequest sectorRemoveRequest) {
    return monitoringAdaptor.removeSector(sectorRemoveRequest).getData();
  }
}
