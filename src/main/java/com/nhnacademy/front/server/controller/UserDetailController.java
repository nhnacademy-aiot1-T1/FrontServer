package com.nhnacademy.front.server.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.front.server.dto.UserDetailDto;
import com.nhnacademy.front.server.dto.UserRole;
import com.nhnacademy.front.server.service.UserDetailService;
import com.nhnacademy.front.server.util.WebUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mypage")
public class UserDetailController {

  private final UserDetailService userService;

  /**
   * @return
   */
  @GetMapping
  public String getUserDetail(Model model, HttpServletRequest request) {

    UserDetailDto userDetailDto = userService.getUserDetail(getUserId(request));

    log.info("user detail {}", userDetailDto.getId());

    model.addAttribute("userDetail", userDetailDto);

    log.info("userDetail dto is :{}", userDetailDto.getId());

    return "users-edit";
  }

  @GetMapping("/admin/users")
  public String getUsers(Model model) {
    List<UserDetailDto> users = userService.getUsers();

    model.addAttribute("users", users);

    log.warn(" is :{}", users);
    return "usersList";
  }

  @PostMapping("/user/update")
  public String updateUserDetail(@ModelAttribute UserDetailDto userDetailDto,
      @RequestParam(value = "id", required = false) Long id) {

    UserDetailDto updateUser = userService.updateUserDetail(id, userDetailDto);

    log.info(" is :{}", updateUser);

    return "users-edit";
  }

  @PostMapping("/user/update/role")
  public String updateUserRole(@RequestParam("role") String role,
                               @RequestParam("userId") Long userId) {

    UserDetailDto userDetailDto = userService.getUserDetail(userId);
    userDetailDto.setRole(UserRole.valueOf(role));

    log.info("role : {} / userId : {}", role, userId);
    userService.updateUserDetail(userId, userDetailDto);
    log.info("Updated user: {}", userDetailDto);

    return WebUtils.REDIRECT_PREFIX + "/mypage/admin/users";
  }


  @DeleteMapping("/user/delete")
  public String deleteUserDetail(@RequestParam(value = "id", required = false) Long id) {
    userService.deleteUserDetail(id);

    return "pages/auth/login";
  }

  private static Long getUserId(HttpServletRequest request) {
    Cookie cookie = Arrays.stream(request.getCookies())
        .filter(c -> c.getName()
            .equals("Authorization"))
        .findFirst().orElse(null);

    String decodeToken = null;

    if (cookie != null) {
      String token = cookie.getValue();
      String payload = token.split("\\.")[1];
      decodeToken = new String(Base64Utils.decodeFromUrlSafeString(payload));
    }

    ObjectMapper objectMapper = new ObjectMapper();

    try {
      JsonNode jsonNode = objectMapper.readTree(decodeToken);
      return jsonNode.get("userId").asLong();
    }catch (IOException e) {
      log.error(e.getMessage());
      return null;
    }
  }
}