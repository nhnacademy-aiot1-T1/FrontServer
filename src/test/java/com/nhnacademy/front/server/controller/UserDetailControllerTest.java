package com.nhnacademy.front.server.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelExtensionsKt;

@SpringBootTest
@Disabled
class UserDetailControllerTest {

  @Autowired
  private UserDetailController userDetailController;

  @Test
  void userDetail() {
    Model model = new ExtendedModelMap();

//    userDetailController.getUserDetail(model, 1L);
  }
}