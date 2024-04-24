package com.nhnacademy.front.server.dummyFiles.dummyController;

import com.nhnacademy.front.server.dummyFiles.dummyService.DummySectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DummySectorController {

  private final DummySectorService sectorService;

  @GetMapping("/management")
  public String managementPage(Model model) {
    // 관리 페이지를 보여주기 위한 데이터를 모델에 추가
    return "area-motor-management";
  }

  @PostMapping("/addSector")
  public String addSector(@RequestParam("areaName") String areaName) {
    sectorService.addSector(areaName);
    return "redirect:/management";
  }
}
