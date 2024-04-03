package com.nhnacademy.front.server.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.domain.LoginResponseDto;
import com.nhnacademy.front.server.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.HttpClientErrorException;

@SpringBootTest
class AuthServiceImplTest {
  @Autowired
  private AuthService authService;

  @MockBean
  private AuthAdapter authAdapter;
  @Test
  void getLoginTokenSuccess() {
    String email = "test@stest.com";
    String password = "1234";
    given(authAdapter.userLogin(any(String.class),any(String.class))).willReturn(new LoginResponseDto("success",new ResponseData("tempToekn"),"hihi","2024-04-03T15:30:15"));
    assertTrue(authService.getLoginToken(email,password).isPresent());
  }
  @Test
  void getLoginTokenFailed() {
    String email = "test@stest.com";
    String password = "1234";
    given(authAdapter.userLogin(any(String.class),any(String.class))).willReturn(null);
    assertTrue(authService.getLoginToken(email,password).isEmpty());
  }

  @Test
  void tokenLogoutFailed() {
    doThrow(HttpClientErrorException.class).when(authAdapter).logout(any(String.class));
    assertThrows(HttpClientErrorException.class,()->{
      authService.tokenLogout("notToken");
    });
  }
}