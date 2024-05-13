package com.nhnacademy.front.server.controller;

import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SideNaviController {

//  @GetMapping("/management")
//  public String management() {
//    return "area-motor-management";
//  }

  @GetMapping("/controllog")
  public String controlLog() {
    return "controlLog";
  }


}
