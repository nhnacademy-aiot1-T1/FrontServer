package com.nhnacademy.front.server.adapter.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.domain.JwtToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class AuthAdapterImplTest {

  private AuthAdapter authAdapter;

  @MockBean
  private RestTemplate restTemplate;

  private MockRestServiceServer mockServer;

  @BeforeEach
  public void setUp() {
    mockServer = MockRestServiceServer.createServer(restTemplate);
    authAdapter = new AuthAdapterImpl(restTemplate);
  }

  @Test
  void userLogin() {
    mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8081/api/account/login"))
        .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
        .andRespond(MockRestResponseCreators.withSuccess("{ \"access_token\": \"fakeToken\"}", MediaType.APPLICATION_JSON));

    JwtToken result = authAdapter.userLogin("user","1234");
    assertNotNull(result);
    assertEquals("fakeToken",result.getAccessToken());

    mockServer.verify();
  }

  @Test
  void logout() {
  }
}