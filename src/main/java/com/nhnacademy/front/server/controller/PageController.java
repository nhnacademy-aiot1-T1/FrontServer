package com.nhnacademy.front.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 기본 페이지를 로드하는 메서드들의 컨트롤러 입니다!
 * @author AoiTuNa
 * @version 1.0
 */
@Controller
@Slf4j
public class PageController {

    /**
     * 메인페이지를 로드합니다
     * @return 메인페이지의 주소입니다!
     */
    @GetMapping
    public String loadMainPage(){
        return "/pages/main/index";
    }
}
