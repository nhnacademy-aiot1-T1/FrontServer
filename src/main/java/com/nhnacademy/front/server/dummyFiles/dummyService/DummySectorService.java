package com.nhnacademy.front.server.dummyFiles.dummyService;


import com.nhnacademy.front.server.dummyFiles.dummyData.Motor;
import com.nhnacademy.front.server.dummyFiles.dummyData.Sector;
import com.nhnacademy.front.server.dummyFiles.enumData.OperatingStatus;
import com.nhnacademy.front.server.dummyFiles.enumData.RunningStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class DummySectorService {

  private int sectorId= 1;

  Motor motor440 = new Motor(440, RunningStatus.ON, OperatingStatus.WARNING, 170.3, 2939, 221, 70, 33);
  Motor motor441 = new Motor(441, RunningStatus.ON, OperatingStatus.SUCCESS, 110.7, 2345, 191, 72, 29);
  Motor motor442 = new Motor(442, RunningStatus.ON, OperatingStatus.SUCCESS, 139.1, 3130, 198, 77, 31);
  Motor motor443 = new Motor(443, RunningStatus.ON, OperatingStatus.WARNING, 123.3, 3003, 350, 71, 30);
  Motor motor444 = new Motor(444, RunningStatus.ON, OperatingStatus.SUCCESS, 122.0, 3001, 202, 72, 30);
  Motor motor445 = new Motor(445, RunningStatus.ON, OperatingStatus.SUCCESS, 126.9, 2998, 218, 71, 39);
  Motor motor446 = new Motor(446, RunningStatus.ON, OperatingStatus.SUCCESS, 130.0, 2889, 208, 79, 37);
  Motor motor447 = new Motor(447, RunningStatus.ON, OperatingStatus.DANGER, 292.1, 3005, 207, 80, 30);
  Motor motor448 = new Motor(448, RunningStatus.OFF, OperatingStatus.SUCCESS, 0.0, 0, 0, 0, 0);
  Motor motor449 = new Motor(449, RunningStatus.OFF, OperatingStatus.SUCCESS, 0.0, 0, 0, 0, 0);
  Motor motor450 = new Motor(450, RunningStatus.OFF, OperatingStatus.DANGER, 0.0, 0, 0, 0, 0);
  Motor motor501 = new Motor(501, RunningStatus.OFF, OperatingStatus.SUCCESS, 0.0, 0, 0, 0, 0);
  Motor motor502 = new Motor(502, RunningStatus.OFF, OperatingStatus.SUCCESS, 0.0, 0, 0, 0, 0);
  Motor motor503 = new Motor(503, RunningStatus.OFF, OperatingStatus.WARNING, 0.0, 0, 0, 0, 0);

  List<Motor> motorList1 = new ArrayList<>(Arrays.asList(
      motor440, motor441, motor442, motor443,motor444,motor445,
      motor446,motor447,motor448,motor449,motor450,motor501,
      motor502,motor503
  ));

  Sector sector1 = new Sector(sectorId++,"HelloWorld",motorList1);

  Motor motor800 = new Motor(800, RunningStatus.ON, OperatingStatus.SUCCESS, 150.3, 2839, 200, 80, 31);
  Motor motor801 = new Motor(801, RunningStatus.ON, OperatingStatus.SUCCESS, 150.7, 2745, 190, 81, 31);
  Motor motor802 = new Motor(802, RunningStatus.ON, OperatingStatus.SUCCESS, 155.1, 3030, 191, 77, 32);
  Motor motor803 = new Motor(803, RunningStatus.ON, OperatingStatus.SUCCESS, 165.3, 2980, 200, 70, 30);
  Motor motor804 = new Motor(804, RunningStatus.ON, OperatingStatus.SUCCESS, 148.0, 3001, 201, 82, 38);
  Motor motor805 = new Motor(805, RunningStatus.ON, OperatingStatus.WARNING, 202.9, 2911, 211, 72, 33);
  Motor motor806 = new Motor(806, RunningStatus.ON, OperatingStatus.SUCCESS, 159.0, 2888, 201, 71, 37);
  Motor motor807 = new Motor(807, RunningStatus.ON, OperatingStatus.SUCCESS, 144.1, 3001, 201, 81, 30);
  Motor motor808 = new Motor(808, RunningStatus.OFF, OperatingStatus.SUCCESS, 161.0, 0, 0, 0, 0);
  Motor motor809 = new Motor(809, RunningStatus.OFF, OperatingStatus.SUCCESS, 155.0, 0, 0, 0, 0);
  Motor motor810 = new Motor(810, RunningStatus.OFF, OperatingStatus.SUCCESS, 0.0, 0, 0, 0, 0);
  Motor motor811 = new Motor(811, RunningStatus.OFF, OperatingStatus.DANGER, 0.0, 0, 0, 0, 0);
  Motor motor812 = new Motor(812, RunningStatus.OFF, OperatingStatus.SUCCESS, 0.0, 0, 0, 0, 0);
  Motor motor813 = new Motor(813, RunningStatus.OFF, OperatingStatus.SUCCESS, 0.0, 0, 0, 0, 0);
  Motor motor814 = new Motor(814, RunningStatus.OFF, OperatingStatus.DANGER, 0.0, 0, 0, 0, 0);
  Motor motor815 = new Motor(815, RunningStatus.OFF, OperatingStatus.SUCCESS, 0.0, 0, 0, 0, 0);
  Motor motor816 = new Motor(816, RunningStatus.OFF, OperatingStatus.SUCCESS, 0.0, 0, 0, 0, 0);
  List<Motor> motorList2 = new ArrayList<>(Arrays.asList(
      motor800,motor801,motor802,motor803,motor804,motor805,
      motor806,motor807,motor808,motor809,motor810,motor811,
      motor812,motor813,motor814,motor815,motor816
  ));

  Sector sector2 = new Sector(sectorId++,"dd",motorList2);

  Motor motor923 = new Motor(923 , RunningStatus.ON, OperatingStatus.SUCCESS, 150.3, 2839, 200, 80, 31);
  Motor motor924 = new Motor(924, RunningStatus.ON, OperatingStatus.SUCCESS, 150.7, 2745, 190, 81, 31);
  Motor motor925 = new Motor(925, RunningStatus.ON, OperatingStatus.SUCCESS, 155.1, 3030, 191, 77, 32);
  Motor motor926 = new Motor(926, RunningStatus.ON, OperatingStatus.SUCCESS, 165.3, 2980, 200, 70, 30);
  Motor motor927 = new Motor(927, RunningStatus.ON, OperatingStatus.SUCCESS, 148.0, 3001, 201, 82, 38);
  Motor motor928 = new Motor(928, RunningStatus.OFF, OperatingStatus.WARNING, 202.9, 2911, 211, 72, 33);


  List<Motor> motorList3 = new ArrayList<>(Arrays.asList(
      motor923,motor924,motor925,motor926,motor927,motor928
  ));

  Sector sector3 = new Sector(sectorId++,"sectorHI",motorList3);
  List<Sector> sectorList = new ArrayList<>(Arrays.asList(sector1,sector2,sector3));



  public void addSector(String sectorName){
    sectorList.add(new Sector(sectorId++,sectorName,new ArrayList<Motor>()));
//    System.out.println(sectorList.get(sectorId).getName());
  }

  // motorId를 사용하여 모터 정보를 로드하는 메서드
  public Motor loadMotorById(int sectorId, int motorId) {
    // 선택한 sectorId에 따라 해당하는 섹터를 찾습니다.
    Sector selectedSector = loadSectorById(sectorId);

    // 선택한 섹터가 null이 아니라면 해당 섹터의 모터 리스트를 가져옵니다.
    if (selectedSector != null) {
      List<Motor> motorList = selectedSector.getMotorList();

      // 모터 리스트에서 선택한 motorId에 해당하는 모터를 찾습니다.
      for (Motor motor : motorList) {
        if (motor.getId() == motorId) {
          return motor;
        }
      }
    }
    return null; // 모터를 찾을 수 없는 경우
  }

  public Sector loadSectorById(int sectorId){
    for(Sector sector : sectorList){
      if(sector.getId() == sectorId){
        return sector;
      }
    }
    return null;
  }

}
