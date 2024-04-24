package com.nhnacademy.front.server.dummyFiles.dummyData;


import static com.nhnacademy.front.server.dummyFiles.enumData.OperatingStatus.DANGER;
import static com.nhnacademy.front.server.dummyFiles.enumData.OperatingStatus.SUCCESS;
import static com.nhnacademy.front.server.dummyFiles.enumData.OperatingStatus.WARNING;

import com.nhnacademy.front.server.dummyFiles.enumData.RunningStatus;
import java.text.DecimalFormat;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@RequiredArgsConstructor
@Slf4j
public class Sector {

  private final int id;
  private final String name;
  private final List<Motor> motorList;

  public int getNormalMotorsNum(){
    int normalMotorsNum=0;
    for(Motor motor : motorList){
      if(motor.getOperatingStatus().equals(SUCCESS)){
        normalMotorsNum++;
      }
    }
    return normalMotorsNum;
  }

  public int getWarningMotorsNum(){
    int warningMotorsNum=0;
    for(Motor motor : motorList){
      if(motor.getOperatingStatus().equals(WARNING)){
        warningMotorsNum++;
      }
    }
    return warningMotorsNum;
  }

  public int getDangerMotorsNum(){
    int dangerMotorsNum=0;
    for(Motor motor : motorList){
      if(motor.getOperatingStatus().equals(DANGER)){
        dangerMotorsNum++;
      }
    }
    return dangerMotorsNum;
  }

  public int getRunningMotorsNum() {

    int runningMotorsNum=0;
    for (Motor motor : motorList) {
      if (motor.getRunningStatus().equals(RunningStatus.ON)) {
        runningMotorsNum++;
        System.out.println(runningMotorsNum);
        System.out.println("으아아아아");
      }
    }
    return runningMotorsNum;
  }


  public double getRunningPercentage() {
    double ratio = (double) getRunningMotorsNum() / (double) getMotorList().size();
    double result = ratio * 100;

    DecimalFormat df = new DecimalFormat("#.##"); // 소수점 둘째 자리까지 포맷 지정
    return Double.parseDouble(df.format(result));
  }

  public double getNormalPercentage() {
    double ratio = (double) getNormalMotorsNum() / (double) getMotorList().size();
    double result = ratio * 100;

    DecimalFormat df = new DecimalFormat("#.##"); // 소수점 둘째 자리까지 포맷 지정
    return Double.parseDouble(df.format(result));
  }

}
