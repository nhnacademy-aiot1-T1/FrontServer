package com.nhnacademy.front.server.adapter.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.config.SecureConfig;
import com.nhnacademy.front.server.domain.CommonResponse;
import com.nhnacademy.front.server.domain.LoginResponseDto;
import com.nhnacademy.front.server.domain.register.RegisterRequestDto;
import com.nhnacademy.front.server.exception.LoginFailedException;
import com.nhnacademy.front.server.exception.RegisterFailException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@DisplayName("로그인 요청 검증 테스트")
@ExtendWith(SpringExtension.class)
class AuthAdapterImplTest {

  @Autowired
  private AuthAdapterImpl authAdapter;

  @MockBean
  private RestTemplate restTemplate;

  @Test
  void userLoginSuccess() {
    CommonResponse<LoginResponseDto> commonResponse = CommonResponse.success(new LoginResponseDto("userId","fakeToken"),"success");
    ResponseEntity<CommonResponse<LoginResponseDto>> responseEntity = new ResponseEntity<>(commonResponse, HttpStatus.OK);
    when(restTemplate.exchange(anyString(), any(), any(HttpEntity.class), any(ParameterizedTypeReference.class))).thenReturn(responseEntity);
    LoginResponseDto result = authAdapter.userLogin("user", "1234","192.168.0.1");
    assertNotNull(result);
    assertEquals("fakeToken", result.getAccessToken());
  }
  @Test
  void userLoginFail() {
    CommonResponse<LoginResponseDto> commonResponse = CommonResponse.fail("id pw 일치하지 않음");
    ResponseEntity<CommonResponse<LoginResponseDto>> responseEntity = new ResponseEntity<>(commonResponse, HttpStatus.UNAUTHORIZED);
    when(restTemplate.exchange(anyString(), any(), any(HttpEntity.class), any(ParameterizedTypeReference.class))).thenReturn(responseEntity);
    assertThrows(LoginFailedException.class,()->authAdapter.userLogin("user", "1234","192.168.0.1"));
  }

  @Test
  void logout() {
    doThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED)).when(restTemplate.exchange(eq("GATEWAY-SERVICE/api/auth/logout"),eq(HttpMethod.POST),any(HttpEntity.class),any(Class.class)));
    assertThrows(HttpClientErrorException.class, () -> authAdapter.logout("notToken"));
  }

  @Test
  void registerUser() {
    doThrow(new HttpClientErrorException(HttpStatus.CONFLICT)).when(restTemplate.exchange(anyString(),any(HttpMethod.class),any(RequestEntity.class),any(Class.class)));
//    mockServer.expect(MockRestRequestMatchers.requestTo("http://GATEWAY-SERVICE/register"))
//        .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
//        .andRespond(MockRestResponseCreators.withStatus(HttpStatus.CONFLICT).body(
//            "{ \"status\": \"fail\","
//                + " \"data\": null, "
//                + "\"message\": \"is already use id!!!\","
//                + "\"timestamp\" : \"" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")) + "\" }"
//        ));
    RegisterRequestDto registerRequestDto = new RegisterRequestDto("user","1234","바보","test@test.com");
    assertThrows(
        RegisterFailException.class, () -> authAdapter.registerUser(registerRequestDto));
//    mockServer.verify();
    }
}