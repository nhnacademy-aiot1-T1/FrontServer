package com.nhnacademy.front.server.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("페이지 로드 테스트")
class PageControllerTest {
  @Autowired
  private PageController pageController;

  @Test
  void loadMainPage() {
      String mainPage = pageController.loadMainPage();
      assertEquals("pages/main/index",mainPage);
    }
}