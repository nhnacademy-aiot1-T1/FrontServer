package com.nhnacademy.front.server.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.config.SecureConfig;
import com.nhnacademy.front.server.domain.register.RegisterCheckDto;
import com.nhnacademy.front.server.domain.register.RegisterRequestDto;
import com.nhnacademy.front.server.exception.RegisterFailException;
import com.nhnacademy.front.server.service.RegisterService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = RegisterController.class)
@DisplayName("회원가입 로직 테스트")
@Import(SecureConfig.class)
class RegisterControllerTest {
    @Autowired
    RegisterController registerController;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RegisterService registerService;

    @MockBean
    AuthAdapter authAdapter;

    @Test
    void showRegisterForm() {
        String actual = "pages/auth/register";
        assertEquals(registerController.showRegisterForm(), actual);
    }

    @Test
    void doRegisterFailedFromValidation() throws Exception {
        RegisterCheckDto registerCheckDto = new RegisterCheckDto("rude", "123456789", "123456789", "김춘삼", "KimCH@ppap.com");
        mockMvc.perform(post("/register").with(csrf())
                        .param("id", registerCheckDto.getId())
                        .param("password", registerCheckDto.getPassword())
                        .param("passwordRetype", registerCheckDto.getPasswordRetype())
                        .param("name", registerCheckDto.getName())
                        .param("email", registerCheckDto.getEmail()))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/auth/register"))
                .andExpect(model().attributeExists("message"));

    }

    @Test
    void doRegisterFailedFromNotMatchPassword() throws Exception {
        RegisterCheckDto registerCheckDto = new RegisterCheckDto("AoiTuNa", "123456789", "PuppyTabeTai", "김춘삼", "KimCH@ppap.com");
        mockMvc.perform(post("/register").with(csrf())
                        .param("id", registerCheckDto.getId())
                        .param("password", registerCheckDto.getPassword())
                        .param("passwordRetype", registerCheckDto.getPasswordRetype())
                        .param("name", registerCheckDto.getName())
                        .param("email", registerCheckDto.getEmail()))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/auth/register"))
                .andExpect(model().attributeExists("message"))
                .andExpect(model().attribute("message", "pw와 pw확인이 일치하지 않습니다!"));
    }

    @Test
    void doRegisterFailedFormExistId() throws Exception {
//        RegisterRequestDto registerRequestDto = new RegisterRequestDto("AoiTuNa", "123456789", "김춘삼", "KimCH@ppap.com");
        doThrow(new RegisterFailException("이미 존재하는 ID", "AuthAdapterImpl")).when(registerService).registerCreated(any(RegisterRequestDto.class));
        RegisterCheckDto registerCheckDto = new RegisterCheckDto("AoiTuNa", "123456789", "123456789", "김춘삼", "KimCH@ppap.com");
        mockMvc.perform(post("/register").with(csrf())
                        .param("id", registerCheckDto.getId())
                        .param("password", registerCheckDto.getPassword())
                        .param("passwordRetype", registerCheckDto.getPasswordRetype())
                        .param("name", registerCheckDto.getName())
                        .param("email", registerCheckDto.getEmail()))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/auth/register"))
                .andExpect(model().attributeExists("message"));
    }

    @Test
    void doRegisterSuccess() throws Exception {
      RegisterCheckDto registerCheckDto = new RegisterCheckDto("AoiTuNa", "123456789", "123456789", "김춘삼", "KimCH@ppap.com");
      mockMvc.perform(post("/register").with(csrf())
                      .param("id", registerCheckDto.getId())
                      .param("password", registerCheckDto.getPassword())
                      .param("passwordRetype", registerCheckDto.getPasswordRetype())
                      .param("name", registerCheckDto.getName())
                      .param("email", registerCheckDto.getEmail()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("pages/auth/registerSuccess"));

    }

}