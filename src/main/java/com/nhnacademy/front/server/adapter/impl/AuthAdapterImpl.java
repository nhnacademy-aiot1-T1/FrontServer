package com.nhnacademy.front.server.adapter.impl;

import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AuthAdapterImpl implements AuthAdapter {

  private final RestTemplate restTemplate;

  @Override
  public JwtToken userLogin(String email, String password) {
    return null;
  }

  @Override
  public void logout(String accessToken) {

  }
}
