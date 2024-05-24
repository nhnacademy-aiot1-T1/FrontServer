package com.nhnacademy.front.server.service;

import com.nhnacademy.front.server.adapter.MonitoringAdaptor;
import com.nhnacademy.front.server.dto.SectorsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SectorService {

  private final MonitoringAdaptor monitoringAdaptor;

  public SectorsDto getSectorsInfo(){
   return monitoringAdaptor.getSectorsInfo().getData();
  }




}
