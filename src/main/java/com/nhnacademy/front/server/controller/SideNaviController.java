package com.nhnacademy.front.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SideNaviController {

  @GetMapping("/controllog")
  public String controlLog() {
    return "controlLog";
  }


}
