package com.nhnacademy.front.server.dummyFiles.dummyController;

import com.nhnacademy.front.server.dummyFiles.dummyData.Motor;
import com.nhnacademy.front.server.dummyFiles.dummyData.Sector;
import com.nhnacademy.front.server.dummyFiles.dummyService.DummySectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class DummyController {

  private final DummySectorService dummySectorService;

  // 구역별 정보
  @GetMapping("sectorDetail/{sectorId}")
  public String test(@PathVariable int sectorId ,Model model) {
    Sector selectedSector = dummySectorService.loadSectorById(sectorId);
    model.addAttribute("selectedSector",selectedSector);

    return "AmotorList";
  }

  // 모터별 정보
  @GetMapping("motorDetail/{sectorId}/{motorId}")
  public String motorDetail(@PathVariable int sectorId, @PathVariable int motorId, Model model) {
    // 선택한 섹터에 해당하는 모터 정보 로드
    Motor selectedMotor = dummySectorService.loadMotorById(sectorId, motorId);

    // 선택한 모터 정보를 모델에 추가하여 AmotorDetail 페이지로 전달
    model.addAttribute("selectedMotor", selectedMotor);

    return "AmotorDetail";
  }

  @GetMapping("/controllog")
  public String controllog(Model model) {
    return "controlLog";
  }

  @GetMapping("/home")
  public String dashboard(Model model) {
    model.addAttribute("sectorList",dummySectorService.getSectorList());
    return "dashboard";
  }

  @GetMapping("/manage")
  public String manage(Model model) {
    return "area-motor-management";
  }

  @GetMapping("/useredit")
  public String userEdit(Model model) {
    return "users-edit";
  }

  // 페이지 샘플
  @GetMapping("/test")
  public String sample(Model model) {
    return "pageFrame/PageFrame";
  }

}
