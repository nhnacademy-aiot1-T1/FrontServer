package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.dto.UserDetailDto;
import com.nhnacademy.front.server.dto.UserRole;
import com.nhnacademy.front.server.service.UserService;
import javax.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mypage")
public class UserDetailController {

  private final UserService userService;

  /**
   *
   * @param id AccountId. not user login id
   * @return
   */
  @GetMapping
  public String getUserDetail(Model model, @RequestParam(value = "id", required = false) Long id) {
    UserDetailDto userDetailDto = userService.getUserDetail(id);

    model.addAttribute("userDetail", userDetailDto);

    log.warn(" is :{}", userDetailDto);

    return "users-edit";
  }

  @PostMapping("/user/update")
  public String updateUserDetail(@ModelAttribute UserDetailDto userDetailDto, @RequestParam(value = "id", required = false) Long id) {

    UserDetailDto updateUser = userService.updateUserDetail(id, userDetailDto);

    log.info(" is :{}", updateUser);

    return "dashboard";
  }
}