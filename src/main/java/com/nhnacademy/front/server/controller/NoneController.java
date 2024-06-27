package com.nhnacademy.front.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoneController {

  @GetMapping("/none")
  public String getNonePage() {
    return "none";
  }

}
