package com.nhnacademy.front.server.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.config.SecureConfig;
import com.nhnacademy.front.server.exception.LoginFailedException;
import com.nhnacademy.front.server.service.AuthService;
import java.util.Optional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockCookie;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = AuthController.class)
@Import(SecureConfig.class)
@DisplayName("로그인 테스트")
class AuthControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AuthService authService;

  @MockBean
  AuthAdapter authAdapter;

  @Test
  void showLoginForm() throws Exception {
    mockMvc.perform(get("/login"))
        .andExpect(status().isOk());
  }

  @Test
  void doLoginSuccess() throws Exception {
    given(authService.getLoginToken(any(String.class), any(String.class),any(String.class))).willReturn(
        Optional.of("token").orElse(null));


    mockMvc.perform(post("/login")
            .param("id", "test")
            .param("password", "1234")
            .header("x-forwarded-for","192.168.0.1"))
        .andExpect(status().isOk())
        .andExpect(cookie().exists("authorization"))
        .andExpect(cookie().httpOnly("authorization", true))
        .andExpect(cookie().secure("authorization", true));
    verify(authService).getLoginToken(anyString(), anyString(), anyString());
  }

  @Test
  void doLoginFailed() throws Exception {
    given(authService.getLoginToken(any(String.class), any(String.class),any(String.class))).willReturn(null);
    mockMvc.perform(post("/login")
            .param("id", "test")
            .param("password", "1234")
            .param("userAddress", "192.168.0.1"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("pages/auth/login"))
        .andExpect(flash().attributeExists("error"));
  }

  @Test
  @WithMockUser(username = "user")
  void TokenSuccess() throws Exception {
    MockCookie cookie = new MockCookie("authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJleHAiOjIwMTYyMzkwMjJ9.BWcm3vSRd3fcwFC5ZShh4jRhlRJkU_8sYTXlG3IkyXY");

    mockMvc.perform(MockMvcRequestBuilders.post("/logout")
            .cookie(cookie))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("pages/auth/login"))
        .andExpect(flash().attributeExists("state"));
  }

  @Test
  @WithMockUser(username = "user")
  void TokenExpired() throws Exception {
    MockCookie cookie = new MockCookie("authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJleHAiOjEwMDAyMzkwMjJ9.dK5BoJoe46wiASMjEVZgSwCVhZOXceYBXbT--uX0SLI");

    mockMvc.perform(MockMvcRequestBuilders.post("/logout")
                    .cookie(cookie))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("pages/auth/login"));
  }

}