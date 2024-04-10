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

/**
 * 모든요청에 클라이언트의 헤더의 토큰의 유무를 확인하는 필터를 적용하는 코드입니다!
 * @author AoiTuNA
 * @version 1.0
 * @see #doFilter(ServletRequest, ServletResponse, FilterChain)
 */
@Slf4j
@RequiredArgsConstructor
public class LoginCheckFilter implements Filter {

  private final AuthService authService;

  /**
   * 클라이언트의 헤더의 토큰을 검증하고 해당하는 페이지를 연결해 주는 필터입니다!
   * @param servletRequest 쿠키의 값과 요청자의 ip정보를 받아오기 위해 사용합니다!
   * @param servletResponse 원하는 페이지로 redirect해주기 위해 사용합니다!
   * @param filterChain 필터 처리를 위한 필터체인 입니다
   * @throws IOException 예기치 못한 예외가 발생 했을 시 IOException을 던집니다!
   */
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    String path = request.getRequestURI();
    if (path.startsWith("/register")) {
      filterChain.doFilter(servletRequest,servletResponse);
      return;
    }

    Cookie[] cookies = request.getCookies();
    String clientIp = request.getHeader("x-forwarded-for");
    for(Cookie cookie : cookies){
      if("authorization".equals(cookie.getName())){
        try{
          authService.checkAccessToken(cookie.getValue(),clientIp);
          response.sendRedirect("pages/main/index");
        }catch (Exception e){
          response.sendRedirect("pages/auth/login");
        }
      }else {
        response.sendRedirect("pages/auth/login");
      }
    }

  }
}
