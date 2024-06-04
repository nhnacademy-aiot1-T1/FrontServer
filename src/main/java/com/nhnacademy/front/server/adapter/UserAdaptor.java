package com.nhnacademy.front.server.adapter;

import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.dto.UserDetailDto;
import java.util.List;

public interface UserAdaptor {

  CommonResponse<UserDetailDto> getUserDetail(Long id);

  CommonResponse<List<UserDetailDto>> getUsers();

  CommonResponse<UserDetailDto> updateUserDetail(Long id, UserDetailDto userDetailDto);

  CommonResponse<UserDetailDto> deleteUserDetail(Long id);
}
