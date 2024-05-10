package com.nhnacademy.front.server.dto.motorInfoOverview;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MotorInfoOverviewDto {

// @JsonCreator
// public MotorInfoOverviewDto(int totalSectorCount, int totalMotorCount, int totalMotorNormalCount, int totalMotorAbnomalCount, int totalMotorConnectedCount, int totalMotorDisconnectedCount) {
//  this.totalSectorCount = totalSectorCount;
//  this.totalMotorCount = totalMotorCount;
//  this.totalMotorNormalCount = totalMotorNormalCount;
//  this.totalMotorAbnomalCount = totalMotorAbnomalCount;
//  this.totalMotorConnectedCount = totalMotorConnectedCount;
//  this.totalMotorDisconnectedCount = totalMotorDisconnectedCount;
// }

  int totalSectorCount;
  int totalMotorCount;
  int totalMotorNormalCount;
  int totalMotorAbnomalCount;
  int totalMotorConnectedCount;
  int totalMotorDisConnectedCount;


}
