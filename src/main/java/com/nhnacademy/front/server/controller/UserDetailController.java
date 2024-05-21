package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.dto.UserDetailDto;
import com.nhnacademy.front.server.service.UserDetailService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mypage")
public class UserDetailController {

  private final UserDetailService userService;

  /**
   * @param id AccountId. not user login id
   * @return
   */
  @GetMapping
  public String getUserDetail(Model model, @RequestParam(value = "id", required = false) Long id) {
    UserDetailDto userDetailDto = userService.getUserDetail(id);

    model.addAttribute("userDetail", userDetailDto);

    log.info(" is :{}", userDetailDto);

    return "users-edit";
  }

  @GetMapping("admin/users")
  public String getUsers(Model model) {
    List<UserDetailDto> users = userService.getUsers();

    model.addAttribute("users", users);

    log.info(" is :{}", users);
    return "usersList";
  }


  @PostMapping("/user/update")
  public String updateUserDetail(@ModelAttribute UserDetailDto userDetailDto,
      @RequestParam(value = "id", required = false) Long id) {

    UserDetailDto updateUser = userService.updateUserDetail(id, userDetailDto);

    log.info(" is :{}", updateUser);

    return "users-edit";
  }

  @DeleteMapping("/user/delete")
  public String deleteUserDetail(@RequestParam(value = "id", required = false) Long id) {
    userService.deleteUserDetail(id);

    return "pages/auth/login";
  }
}