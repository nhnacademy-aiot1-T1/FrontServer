package com.nhnacademy.front.server.interceptor;

import com.nhnacademy.front.server.dto.UserRole;
import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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

    String decodeToken = null;
    if (cookie != null) {
      String token = cookie.getValue();
      String payload = token.split("\\.")[1];
      decodeToken = new String(Base64Utils.decodeFromUrlSafeString(payload));
      log.info(decodeToken);
    }

    if (StringUtils.containsIgnoreCase(decodeToken, "ADMIN")) {
      roleThreadLocal.set(UserRole.ADMIN);
    } else if (StringUtils.containsIgnoreCase(decodeToken, "USER")) {
      roleThreadLocal.set(UserRole.USER);
    } else {
      roleThreadLocal.set(UserRole.NONE);
    }

    return HandlerInterceptor.super.preHandle(request, response, handler);
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    if (modelAndView != null) {
      modelAndView.getModel().put("userRole", roleThreadLocal.get());
    }
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    roleThreadLocal.remove();
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
  }
}
