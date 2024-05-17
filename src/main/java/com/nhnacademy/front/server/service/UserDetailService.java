package com.nhnacademy.front.server.service;

import com.nhnacademy.front.server.adapter.UserAdaptor;
import com.nhnacademy.front.server.dto.UserDetailDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService {

  private final UserAdaptor userAdaptor;

  public UserDetailDto getUserDetail(Long userId) {
    return userAdaptor.getUserDetail(userId).getData();
  }

  public List<UserDetailDto> getUsers() {
    return userAdaptor.getUsers().getData();
  }

  public UserDetailDto updateUserDetail(Long id, UserDetailDto userDetailDto) {
    return userAdaptor.updateUserDetail(id, userDetailDto).getData();
  }

  public UserDetailDto deleteUserDetail(Long userId) {
    return userAdaptor.deleteUserDetail(userId).getData();
  }

}
