package com.nhnacademy.front.server.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nhnacademy.front.server.domain.register.CreateRegisterRequestDto;
import com.nhnacademy.front.server.domain.register.RegisterRequestDto;
import com.nhnacademy.front.server.domain.register.ValidationResult;
import com.nhnacademy.front.server.exception.RegisterFailException;
import com.nhnacademy.front.server.service.RegisterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = RegisterController.class)
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
    given(registerService.validationRegisterRequest(any(RegisterRequestDto.class))).willReturn(new ValidationResult(false,"Id는 5글자 이상이어야 합니다"));
    mockMvc.perform(post("/register")
            .param("id","ppap")
            .param("password","12345678")
            .param("passwordRetype","12345678"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("failedResult"))
            .andExpect(view().name("pages/auth/register"));
  }
  @Test
  void doRegisterFailedFromApi() throws Exception{
    given(registerService.validationRegisterRequest(any(RegisterRequestDto.class))).willReturn(new ValidationResult(true,"유효한 입력입니다!"));
    doThrow(new RegisterFailException("로그인 터짐 ㅇㅇ")).when(registerService).isCreated(any(CreateRegisterRequestDto.class));
    mockMvc.perform(post("/register")
            .param("id","ppapppa")
            .param("password","12345678")
            .param("passwordRetype","12345678"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("failedResult"))
            .andExpect(view().name("pages/auth/register"));
  }
  @Test
  void doResisterSuccessTest() throws Exception{
    given(registerService.validationRegisterRequest(any(RegisterRequestDto.class))).willReturn(new ValidationResult(true,"유효한 입력입니다!"));
    mockMvc.perform(post("/register")
                    .param("id","ppapppa")
                    .param("password","12345678")
                    .param("passwordRetype","12345678"))
            .andExpect(status().is3xxRedirection())
            .andExpect(flash().attributeExists("successMessage"))
            .andExpect(redirectedUrl("pages/auth/registerSuccess"));


  }
}