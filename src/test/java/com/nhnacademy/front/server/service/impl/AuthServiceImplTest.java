package com.nhnacademy.front.server.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.domain.LoginResponseDto;
import com.nhnacademy.front.server.service.AuthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.HttpClientErrorException;

@SpringBootTest
@DisplayName("회원가입 로그인 서비스 로직 테스트")
class AuthServiceImplTest {
  @Autowired
  private AuthService authService;

  @MockBean
  private AuthAdapter authAdapter;
  @Test
  void getLoginTokenSuccess() {
    String id = "test";
    String password = "1234";
    String userAddress ="192.168.0.1";
    given(authAdapter.userLogin(any(String.class),any(String.class),any(String.class))).willReturn(new LoginResponseDto("admin","testToken"));
    assertTrue(authService.getLoginToken(id,password,userAddress).matches("testToken"));
  }
  @Test
  void getLoginTokenFailed() {
    String id = "test";
    String password = "1234";
    String userAddress ="192.168.0.1";
    given(authAdapter.userLogin(any(String.class),any(String.class),any(String.class))).willReturn(new LoginResponseDto("admin",null));
    assertNull(authService.getLoginToken(id,password,userAddress));
  }

  @Test
  void tokenLogoutFailed() {
    doThrow(HttpClientErrorException.class).when(authAdapter).logout(any(String.class));
    assertThrows(HttpClientErrorException.class,()->{
      authService.tokenLogout("notToken");
    });
  }
}