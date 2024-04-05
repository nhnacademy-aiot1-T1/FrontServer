package com.nhnacademy.front.server.adapter.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.domain.LoginResponseDto;
import com.nhnacademy.front.server.domain.register.CreateRegisterRequestDto;
import com.nhnacademy.front.server.exception.RegisterFailException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@DisplayName("로그인 요청 검증 테스트")
class AuthAdapterImplTest {

  @Autowired
  private AuthAdapter authAdapter;

  @Autowired
  private RestTemplate restTemplate;

  private MockRestServiceServer mockServer;

  @BeforeEach
  public void setUp() {
    mockServer = MockRestServiceServer.createServer(restTemplate);
  }

  @Test
  void userLogin() {
    mockServer.expect(MockRestRequestMatchers.requestTo("http://192.168.71.99:8080/api/auth/login"))
        .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
        .andRespond(MockRestResponseCreators.withSuccess(
            "{ \"status\": \"success\","
                + " \"data\": { \"userId\": \"userId\", \"userRole\": \"ADMIN\", \"accessToken\": \"fakeToken\"}, "
                + "\"message\": null, "
                + "\"timestamp\" : \"" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")) + "\" }",
            MediaType.APPLICATION_JSON));

    LoginResponseDto result = authAdapter.userLogin("user", "1234","192.168.0.1");
    assertNotNull(result);
    assertEquals("fakeToken", result.getAccessToken());

    mockServer.verify();
  }

  @Test
  void logout() {
    mockServer.expect(MockRestRequestMatchers.requestTo("http://192.168.0.27:8080/logout"))
        .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
        .andRespond(MockRestResponseCreators.withUnauthorizedRequest());

    assertThrows(HttpClientErrorException.class, () -> authAdapter.logout("notToken"));

    mockServer.verify();


  }

  @Test
  void registerUser() {
    mockServer.expect(MockRestRequestMatchers.requestTo("http://192.168.0.27:8080/register"))
        .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
        .andRespond(MockRestResponseCreators.withStatus(HttpStatus.CONFLICT).body(
            "{ \"status\": \"fail\","
                + " \"data\": null, "
                + "\"message\": \"is already use id!!!\","
                + "\"timestamp\" : \"" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")) + "\" }"
        ));
    CreateRegisterRequestDto registerRequestDto = new CreateRegisterRequestDto("user","1234");
    assertThrows(
        RegisterFailException.class, () -> authAdapter.registerUser(registerRequestDto));
    mockServer.verify();
    }
}