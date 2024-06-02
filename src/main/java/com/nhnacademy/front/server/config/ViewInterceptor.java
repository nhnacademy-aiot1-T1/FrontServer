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
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

@Component
@Slf4j
public class ViewInterceptor implements HandlerInterceptor {

  private static final ThreadLocal<UserRole> roleThreadLocal = new ThreadLocal<>();

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    Cookie cookie = Arrays.stream(request.getCookies())
        .filter(c -> c.getName()
            .equals("Authorization"))
        .findFirst().orElse(null);

    String payload = null;
    if (cookie != null) {
      String token = cookie.getValue();
      payload = token.split("\\.")[1];
      String decodeToken = new String(Base64Utils.decodeFromUrlSafeString(payload));
      log.info(decodeToken);
    }

    if (StringUtils.containsIgnoreCase(payload, "ADMIN")) {
      roleThreadLocal.set(UserRole.ADMIN);
    } else {
      roleThreadLocal.set(UserRole.NONE);
    }

    return HandlerInterceptor.super.preHandle(request, response, handler);
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    if (modelAndView != null) {
      modelAndView.addObject("userRole", roleThreadLocal.get());
    }
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    roleThreadLocal.remove();
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
  }


  public static void main(String[] args) {
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjE1LCJuYW1lIjoi67CV66-47KCV7KCVIiwicm9sZSI6Ik5PTkUiLCJpYXQiOjE3MTcyNTE0NzIsImV4cCI6MTcxNzI1MTc3Mn0.TVtdkcKfEyKBmhl9al-5H9rABpjx5alC4THu41cU_C13yL8XDBySjcCLzisaa3d46eCy2wBwXsPEowgxVl795w.YEfEmyEwtRaVC51gT8mJhQAW-TMt0ZJujbfEZvkdeB6h9yLsC87P_8TzDciHZ7dQ6QltHM1JJcRduJes6X3DFA";
    String payload = token.split("\\.")[1];

    System.out.println(new String(Base64Utils.decodeFromUrlSafeString(payload)));

  }
}
