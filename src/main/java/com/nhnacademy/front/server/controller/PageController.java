package com.nhnacademy.front.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class PageController {

    @GetMapping
    public String loadMainPage(){
        return "/pages/main/index";
    }
}
