package com.nhnacademy.front.server.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "api.path")
@RequiredArgsConstructor
@Getter
@ConstructorBinding
public class ApiPathProperties {

  private final String motorOverview;
  private final String sectorInfo;
  private final String motors;
  private final String motorDetail;
  private final String controlLogs;
  private final String runningRateByTime;
  private final String individualRunningRateByTime;
  private final String sectorRegister;
  private final String sectorRename;
  private final String sectorRemove;

}
