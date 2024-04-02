package com.nhnacademy.front.server.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.domain.JwtToken;
import com.nhnacademy.front.server.domain.UserLoginRequestDto;
import com.nhnacademy.front.server.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
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
    given(authAdapter.userLogin(any(String.class),any(String.class))).willReturn(new JwtToken("testToken"));
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
    })
    ;
  }
}