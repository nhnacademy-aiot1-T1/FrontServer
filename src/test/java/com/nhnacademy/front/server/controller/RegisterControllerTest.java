package com.nhnacademy.front.server.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nhnacademy.front.server.adapter.impl.AuthAdapterImpl;
import com.nhnacademy.front.server.domain.register.RegisterCheckDto;
import com.nhnacademy.front.server.domain.register.RegisterRequestDto;
import com.nhnacademy.front.server.domain.register.ValidationResult;
import com.nhnacademy.front.server.exception.JsonParseFailException;
import com.nhnacademy.front.server.exception.RegisterFailException;
import com.nhnacademy.front.server.service.RegisterService;
import java.io.IOException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = RegisterController.class)
@DisplayName("회원가입 로직 테스트")
@Disabled
class RegisterControllerTest {
  @Autowired
  RegisterController registerController;

  @Autowired
  MockMvc mockMvc;

  @MockBean
  private RegisterService registerService;


  @Test
  void showRegisterForm() {
    String actual = "/pages/auth/register";
    assertEquals(registerController.showRegisterForm(),actual);
  }

  @Test
  void doRegisterFailedFromFront() throws Exception {
    given(registerService.validationRegisterRequest(any(RegisterCheckDto.class))).willReturn(new ValidationResult(false,"Id는 5글자 이상이어야 합니다"));
    mockMvc.perform(post("/register")
            .param("id","ppap")
            .param("password","12345678")
            .param("passwordRetype","12345678"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("message"))
            .andExpect(view().name("/pages/auth/register"));
  }
  @Test
  void doRegisterFailedFromApi() throws Exception{
    given(registerService.validationRegisterRequest(any(RegisterCheckDto.class))).willReturn(new ValidationResult(true,"유효한 입력입니다!"));
    doThrow(new RegisterFailException("로그인 터짐 ㅇㅇ",AuthAdapterImpl.class.getSimpleName())).when(registerService).registerCreated(any(
        RegisterRequestDto.class));
    mockMvc.perform(post("/register")
            .param("id","ppapppa")
            .param("password","12345678")
            .param("passwordRetype","12345678"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("message"))
        .andExpect(view().name("/pages/auth/register"));
  }

  @Test
  void doRegisterFailedFromJsonParsing() throws Exception{
    given(registerService.validationRegisterRequest(any(RegisterCheckDto.class))).willReturn(new ValidationResult(true,"유효한 입력입니다!"));
    doThrow(new JsonParseFailException("json 파싱 오류",new IOException(), AuthAdapterImpl.class.getSimpleName())).when(registerService).registerCreated(any(RegisterRequestDto.class));
    mockMvc.perform(post("/register")
            .param("id","ppapppa")
            .param("password","12345678")
            .param("passwordRetype","12345678"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("message"))
        .andExpect(view().name("/pages/auth/register"));
  }
  @Test
  void doResisterSuccessTest() throws Exception{
    given(registerService.validationRegisterRequest(any(RegisterCheckDto.class))).willReturn(new ValidationResult(true,"유효한 입력입니다!"));
    mockMvc.perform(post("/register")
                    .param("id","ppapppa")
                    .param("password","12345678")
                    .param("passwordRetype","12345678"))
            .andExpect(status().is3xxRedirection())
            .andExpect(flash().attributeExists("message"))
            .andExpect(redirectedUrl("/pages/auth/registerSuccess"));


  }
}