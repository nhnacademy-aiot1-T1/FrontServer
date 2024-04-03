package com.nhnacademy.front.server.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nhnacademy.front.server.service.AuthService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockCookie;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = AuthController.class)
class AuthControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AuthService authService;

  @Test
  void showLoginForm() throws Exception {
    mockMvc.perform(get("/login"))
        .andExpect(status().isOk());
  }

  @Test
  void doLoginSuccess() throws Exception {
    given(authService.getLoginToken(any(String.class), any(String.class))).willReturn(
        Optional.of("token").orElse(null));

    mockMvc.perform(post("/login")
            .param("id", "test")
            .param("password", "1234"))
        .andExpect(status().isOk())
        .andExpect(cookie().exists("authorization"))
        .andExpect(cookie().httpOnly("authorization", true))
        .andExpect(cookie().secure("authorization", true));
  }

  @Test
  void doLoginFailed() throws Exception {
    given(authService.getLoginToken(any(String.class), any(String.class))).willReturn(null);
    mockMvc.perform(post("/login")
            .param("id", "test")
            .param("password", "1234"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/pages/auth/login"))
        .andExpect(flash().attributeExists("error"));
  }

  @Test
  void doLogoutSuccess() throws Exception {
    MockCookie cookie = new MockCookie("authorization", "testToken");

    mockMvc.perform(MockMvcRequestBuilders.post("/logout")
            .cookie(cookie))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/pages/auth/login"))
        .andExpect(flash().attributeExists("state"));
  }

}