package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.dto.UserDetailDto;
import com.nhnacademy.front.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserDetailController {

  private final UserService userService;

  /**
   *
   * @param id AccountId. not user login id
   * @return
   */
  @GetMapping("/api/account/users/{id}")
  public String userDetails(Model model, @PathVariable Long id) {
    UserDetailDto userDetailDto = userService.getUserDetail(id);

    model.addAttribute("name", userDetailDto.getUserName());
    model.addAttribute("email", userDetailDto.getUserEmail());
    model.addAttribute("phone", userDetailDto.getUserPhone());
    model.addAttribute("role",userDetailDto.getUserRole());

    return "userDetail";
  }
}
