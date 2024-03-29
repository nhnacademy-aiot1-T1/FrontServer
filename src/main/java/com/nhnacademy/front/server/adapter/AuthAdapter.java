package com.nhnacademy.front.server.adapter;

import com.nhnacademy.front.server.domain.JwtToken;

public interface AuthAdapter {

  JwtToken userLogin(String email, String password);
  void logout(String accessToken);

}
