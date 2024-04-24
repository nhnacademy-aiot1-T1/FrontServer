package com.nhnacademy.front.server.dummyFiles.dummyData;


import com.nhnacademy.front.server.dummyFiles.enumData.OperatingStatus;
import com.nhnacademy.front.server.dummyFiles.enumData.RunningStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Motor {

  private final Integer id;
  private final RunningStatus runningStatus;
  private final OperatingStatus operatingStatus;
  private final Double temperature;
  private final Integer vibrationIntensity;
  private final Integer electricCurrent;
  private final Integer noise;
  private final Integer rpm;

}
