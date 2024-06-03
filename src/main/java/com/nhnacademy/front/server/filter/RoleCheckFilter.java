package com.nhnacademy.front.server.filter;

import com.nhnacademy.front.server.exception.AuthorizationException;
import com.nhnacademy.front.server.exception.NotFoundTokenException;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RoleCheckFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    Cookie cookie = Arrays.stream(request.getCookies())
        .filter(c -> c.getName()
            .equals("Authorization"))
        .findFirst().orElseThrow(NotFoundTokenException::new);

    String token = cookie.getValue();
    String payload = token.split("\\.")[1];
    String decodeToken = new String(Base64Utils.decodeFromUrlSafeString(payload));

    if (StringUtils.containsIgnoreCase(decodeToken, "none")) {
      throw new AuthorizationException();
    }

    filterChain.doFilter(request, response);
  }
}
