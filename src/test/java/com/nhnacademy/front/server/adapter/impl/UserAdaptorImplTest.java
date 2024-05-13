package com.nhnacademy.front.server.adapter.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.front.server.adapter.UserAdaptor;
import com.nhnacademy.front.server.dto.UserDetailDto;
import com.nhnacademy.front.server.dto.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
class UserAdaptorImplTest {

  @Autowired
  private UserAdaptor userAdaptor;

  @Test
  void getUserDetail() {
    userAdaptor.getUserDetail(1L);
  }

  @Test
  void updateUserDetail() {
    UserDetailDto userDetailDto = new UserDetailDto(2L, "test", "12314251", "123@123.com", UserRole.NONE);
    userAdaptor.updateUserDetail(1L, userDetailDto);
  }
}