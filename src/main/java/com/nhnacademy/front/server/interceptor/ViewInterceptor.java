package com.nhnacademy.front.server.interceptor;

import com.nhnacademy.front.server.dto.UserRole;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component
@Slf4j
public class ViewInterceptor implements HandlerInterceptor {

  private static final ThreadLocal<UserRole> roleThreadLocal = new ThreadLocal<>();

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    Cookie[] cookies =request.getCookies();

    if (cookies == null) {
      cookies = new Cookie[0];
    }
    Cookie cookie = Arrays.stream(cookies)
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
                         ModelAndView modelAndView) {
    if (modelAndView == null){
      return;
    }
    if (modelAndView.getView() instanceof RedirectView || StringUtils.containsIgnoreCase(modelAndView.getViewName(), "redirect:")) {
      FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
      flashMap.put("userRole", roleThreadLocal.get());
      FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
      Objects.requireNonNull(flashMapManager).saveOutputFlashMap(flashMap, request, response);
    } else {
      modelAndView.addObject("userRole", roleThreadLocal.get());
    }
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    roleThreadLocal.remove();
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
  }
}
