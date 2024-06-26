package com.nhnacademy.front.server.filter;

import com.nhnacademy.front.server.exception.AuthenticationException;
import com.nhnacademy.front.server.exception.AuthorizationException;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

public class RoleCheckFilter extends OncePerRequestFilter {

  private static final String[] EXCLUDE_PATH_PREFIX = {"/login", "/none", "/"};

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    boolean isExcludePath = Arrays.stream(EXCLUDE_PATH_PREFIX)
        .anyMatch(prefix -> request.getRequestURI().startsWith(prefix));

    if (isExcludePath) {
      filterChain.doFilter(request, response);
    }

    Cookie cookie = Arrays.stream(request.getCookies())
        .filter(c -> c.getName()
            .equals("Authorization"))
        .findFirst().orElseThrow(AuthenticationException::new);

    String token = cookie.getValue();
    String payload = token.split("\\.")[1];
    String decodeToken = new String(Base64Utils.decodeFromUrlSafeString(payload));

    if (StringUtils.containsIgnoreCase(decodeToken, "none")) {
      throw new AuthorizationException();
    }

    filterChain.doFilter(request, response);
  }
}
