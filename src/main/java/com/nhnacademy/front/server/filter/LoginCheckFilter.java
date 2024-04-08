package com.nhnacademy.front.server.filter;

import com.nhnacademy.front.server.service.AuthService;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class LoginCheckFilter implements Filter {

  private final AuthService authService;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    boolean isHaveAuthToken = false;

    Cookie[] cookies = request.getCookies();
    for(Cookie cookie : cookies){
      if("authorization".equals(cookie.getName())){
        //Todo authService 단에서 처리하는 토큰 검증 요청 로직!!
        try{
          //authService.validateToken(검증 값)
        }catch (Exception e){
          response.sendRedirect("pages/auth/login");
        }
      }
    }

  }
}
