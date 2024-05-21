package com.nhnacademy.front.server.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.front.server.dto.UserDetailDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelExtensionsKt;

@SpringBootTest
class UserDetailControllerTest {

  @Autowired
  private UserDetailController userDetailController;

  @Test
  void getUserDetail() {
    Model model = new ExtendedModelMap();

    userDetailController.getUserDetail(model, 1L);
  }

  @Test
  void getUsers() {
    Model model = new ExtendedModelMap();
    userDetailController.getUsers(model);
  }

  @Test
  void updateUserDetail() {
    Model model = new ExtendedModelMap();

    UserDetailDto userDetailDto = new UserDetailDto();

    userDetailController.updateUserDetail(userDetailDto,1L);
  }

  @Test
  void deleteUserDetail() {
    Model model = new ExtendedModelMap();
    userDetailController.deleteUserDetail(1L);
  }
}