package com.nhnacademy.front.server.adapter.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.nhnacademy.common.dto.CommonResponse;
import com.nhnacademy.front.server.dto.UserDetailDto;
import com.nhnacademy.front.server.dto.UserRole;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@SpringBootTest
class UserAdaptorImplTest {

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private UserAdaptorImpl userAdaptor;

  @Test
  void getUserDetail() {
    CommonResponse<UserDetailDto> user = userAdaptor.getUserDetail(1L);
    log.info(user.toString());
    assertNotNull(user.getData());
  }

  @Test
  void getUsers() {
    List<UserDetailDto> userDetailDto = userAdaptor.getUsers().getData();
    assertNotNull(userDetailDto.get(0));
  }

  @Test
  void updateUserDetail() {
    UserDetailDto userDetailDto = new UserDetailDto(2L, "test", "12314251", "123@123.com",
        UserRole.NONE);
    userAdaptor.updateUserDetail(1L, userDetailDto);
  }

//  @Test
//  void updateUSerDetailThrowException() {
//    UserDetailDto userDetailDto = new UserDetailDto(2L, "test", "12314251", "123@123.com", UserRole.NONE);
//
//    assertThrows(ResponseStatusException.class, () ->
//        userAdaptor.updateUserDetail(-1L, userDetailDto));
//  }

  @Test
  void deleteUserDetail() {
    userAdaptor.deleteUserDetail(1L);
  }

  @Test
  void testUpdateUserDetailThrowsResponseStatusException() {
    UserDetailDto userDetailDto = new UserDetailDto(
        2L, "test", "12314251", "123@123.com", UserRole.NONE);
    String requestBody = "{\\\"id\\\":2,\\\"name\\\":\\\"test\\\",\\\"password\\\":\\\"12314251\\\",\\\"email\\\":\\\"123@123.com\\\",\\\"userRole\\\":\\\"NONE\\\"}";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

    when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), any(
        ParameterizedTypeReference.class)))
        .thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
            "Error occurred while posting user detail"));

    assertThrows(ResponseStatusException.class, () -> {
      userAdaptor.updateUserDetail(1L, userDetailDto);
    });
  }
}