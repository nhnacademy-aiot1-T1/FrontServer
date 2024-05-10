package com.nhnacademy.front.server.adapter;

import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.dto.UserDetailDto;

public interface UserAdaptor {

  CommonResponse<UserDetailDto> getUserDetail(Long userId);

  CommonResponse<UserDetailDto> updateUserDetail(UserDetailDto userDetailDto);
}
