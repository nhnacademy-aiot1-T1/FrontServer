package com.nhnacademy.front.server.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nhnacademy.front.server.adapter.impl.AuthAdapterImpl;
import com.nhnacademy.front.server.config.SecureConfig;
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
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

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

    @Test
    void showRegisterForm() {
        String actual = "pages/auth/register";
        assertEquals(registerController.showRegisterForm(), actual);
    }

    @Test
    void doRegisterFailedFromValidation() throws Exception {
        RegisterCheckDto registerCheckDto = new RegisterCheckDto("rude", "123456789", "123456789", "김춘삼", "KimCH@ppap.com");
        mockMvc.perform(post("/register")
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
        mockMvc.perform(post("/register")
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
        mockMvc.perform(post("/register")
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
      mockMvc.perform(post("/register")
                      .param("id", registerCheckDto.getId())
                      .param("password", registerCheckDto.getPassword())
                      .param("passwordRetype", registerCheckDto.getPasswordRetype())
                      .param("name", registerCheckDto.getName())
                      .param("email", registerCheckDto.getEmail()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("pages/auth/registerSuccess"));

    }

}