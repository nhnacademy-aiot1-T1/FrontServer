package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.dto.controlLog.ControlLogDto;
import com.nhnacademy.front.server.service.ControlLogService;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ControlLogController {

  private final ControlLogService controlLogService;

  @GetMapping("/home/controlLog")
  public String getControlLogs(Model model) {
    List<ControlLogDto> controlLogsDto = controlLogService.getControlLogs().getLogs();
    Set<String> dateSet = new TreeSet<>(Comparator.reverseOrder());

    for (ControlLogDto controlLog : controlLogsDto) {
      String date = controlLog.getTime().substring(0, 10);
      dateSet.add(date);
    }

    model.addAttribute("controlLogs", controlLogsDto);
    model.addAttribute("dateSet", dateSet);

    return "controlLog";
  }

}
