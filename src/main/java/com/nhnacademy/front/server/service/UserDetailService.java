package com.nhnacademy.front.server.service;

import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.adapter.UserAdaptor;
import com.nhnacademy.front.server.dto.UserDetailDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailService {

  private final UserAdaptor userAdaptor;

  public UserDetailDto getUserDetail(Long userId) {
    CommonResponse<UserDetailDto> dto = userAdaptor.getUserDetail(userId);

    log.info(":{}, :{}, :{}",dto.getData(), dto.getMessage(), dto.getStatus());
    return dto.getData();
  }

  public List<UserDetailDto> getUsers() {
    return userAdaptor.getUsers().getData();
  }

  public UserDetailDto updateUserDetail(Long id, UserDetailDto userDetailDto) {
    return userAdaptor.updateUserDetail(id, userDetailDto).getData();
  }

  public void deleteUserDetail(Long userId) {
    userAdaptor.deleteUserDetail(userId).getData();
  }

}
