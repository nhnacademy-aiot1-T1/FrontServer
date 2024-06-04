package com.nhnacademy.front.server.filter;

import com.nhnacademy.front.server.service.AuthService;
import com.nhnacademy.front.server.util.WebUtils;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class TokenExpiredFilter extends OncePerRequestFilter {

  private final AuthService authService;

  private static final String[] EXCLUDE_PATH_PREFIX = {"/login", "/logout", "/register"};

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    boolean isExcludePath = Arrays.stream(EXCLUDE_PATH_PREFIX)
        .anyMatch(prefix -> request.getRequestURI().startsWith(prefix));

    return (isExcludePath && (request.getCookies() == null || WebUtils.findAuthorizationCookie(
        request.getCookies()).isEmpty()));
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    Cookie authorizationCookie = WebUtils.findAuthorizationCookie(request.getCookies())
        .orElse(null);

    if (authorizationCookie == null) {
      response.sendRedirect("/login");
    }

    final String accessToken = authorizationCookie.getValue();

    if (!WebUtils.isTokenExpired(accessToken)) {
      String refreshAccessToken = authService.requestTokenRefresh(accessToken);

      log.info("access token이 재발급 되었습니다.");

      Cookie cookie = new Cookie("Authorization", refreshAccessToken);
      response.addCookie(cookie);
    }

    filterChain.doFilter(request, response);
  }
}
