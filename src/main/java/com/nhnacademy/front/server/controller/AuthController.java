package com.nhnacademy.front.server.controller;

import com.nhnacademy.front.server.dto.user.UserLoginRequestDto;
import com.nhnacademy.front.server.properties.PaycoProperties;
import com.nhnacademy.front.server.exception.NotFoundTokenException;
import com.nhnacademy.front.server.service.AuthService;
import com.nhnacademy.front.server.util.WebUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AuthController {

  private static final String AUTHORIZATION_KEY = "Authorization";
  private static final String LOGIN_PAGE = "login";
  private static final String HOME_PAGE = "home";


  private final PaycoProperties paycoProperties;

  private final AuthService authService;

  /**
   * 로그인 페이지로 이동합니다, 이미 로그인이 되어있으시 home으로 이동합니다.
   */
  @GetMapping("/login")
  public String showLoginForm(
      @CookieValue(value = AUTHORIZATION_KEY, required = false) String token) {
    return (!StringUtils.hasText(token)) ? LOGIN_PAGE : WebUtils.REDIRECT_PREFIX + HOME_PAGE;
  }

  /**
   * oauth login 입니다.
   */
  @GetMapping("/oauth/payco-authorize")
  public String paycoLogin(@CookieValue(value = AUTHORIZATION_KEY, required = false) String token) {
    if (StringUtils.hasText(token)) {
      return WebUtils.REDIRECT_PREFIX + HOME_PAGE;
    }

    final String url = "https://id.payco.com/oauth2.0/authorize?response_type=code"
        + "&client_id=" + paycoProperties.getClientId()
        + "&serviceProviderCode=FRIENDS"
        + "&redirect_uri=" + paycoProperties.getRedirectUri()
        + "&userLocale=ko_KR";

    return WebUtils.REDIRECT_PREFIX + url;
  }

  @GetMapping("/oauth/payco-login")
  public String doPaycoLogin(@RequestParam(name = "code") String authCode,
      HttpServletResponse res) {
    String accessToken = authService.paycoLogin(authCode).getAccessToken();

    if (accessToken != null) {
      Cookie cookie = new Cookie(AUTHORIZATION_KEY, authCode);
      cookie.setPath("/");
      res.addCookie(cookie);
    }

    return WebUtils.REDIRECT_PREFIX + "/home";
  }


  /**
   * 로그인 성공시 Authorization 쿠키를 만들고 response에 추가 후 homepage로 이동합니다. 로그인 실패시 바로 filter에 걸려 다시 로그인 페이지로
   * 이동.
   */
  @PostMapping("/login")
  public String doLogin(@ModelAttribute UserLoginRequestDto userLoginRequestDto,
      HttpServletResponse res) {
    String accessToken = authService.login(userLoginRequestDto).getAccessToken();

    if (accessToken != null) {
      Cookie cookie = new Cookie(AUTHORIZATION_KEY, accessToken);
      cookie.setPath("/");
      res.addCookie(cookie);
    }

    return WebUtils.REDIRECT_PREFIX + HOME_PAGE;
  }

  @PostMapping("/logout")
  public String doLogout(
      @CookieValue(value = AUTHORIZATION_KEY, required = false) Cookie authorizationCookie,
      HttpServletResponse res) {
    if (!StringUtils.hasText(authorizationCookie.getValue())) {
      throw new NotFoundTokenException();
    }

    authService.logout(authorizationCookie.getValue());
    authorizationCookie.setMaxAge(0);
    res.addCookie(authorizationCookie);

    return WebUtils.REDIRECT_PREFIX + LOGIN_PAGE;
  }
}
