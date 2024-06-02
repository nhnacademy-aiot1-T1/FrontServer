package com.nhnacademy.front.server.config;

import com.nhnacademy.front.server.dto.UserRole;
import com.nhnacademy.front.server.exception.AuthenticationException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

@Component
@Slf4j
public class ViewInterceptor implements HandlerInterceptor {

  private static ThreadLocal<UserRole> roleThreadLocal = new ThreadLocal<>();

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    Cookie cookie = Arrays.stream(request.getCookies())
        .filter(c -> c.getName()
            .equals("Authorization"))
        .findFirst().orElse(new Cookie("Authorization", ""));
    String token = cookie.getValue();
    Decoder decoder = Base64.getDecoder();
    String decodeToken = new String(decoder.decode(token));
    log.info(decodeToken);
    if (StringUtils.containsIgnoreCase(decodeToken, "ADMIN")) {
      roleThreadLocal.set(UserRole.ADMIN);
    }else {
      roleThreadLocal.set(UserRole.NONE);
    }

    return HandlerInterceptor.super.preHandle(request, response, handler);
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    modelAndView.addObject("userRole", roleThreadLocal.get());
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    roleThreadLocal.remove();
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
  }
}
