package com.nhnacademy.front.server.filter;

import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.exception.NotFoundTokenException;
import com.nhnacademy.front.server.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenExpirationFilter implements Filter {

    private static final String LOGIN_PATH = "/login";
    private static final String REGISTER_PATH = "/register";

    private final AuthAdapter authAdapter;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI();
        if (!(path.startsWith(LOGIN_PATH) || path.startsWith(REGISTER_PATH) )) {
            String token = extractToken(request);
            if (token != null && TokenUtils.isTokenExpired(token)) {
                authAdapter.checkAccessToken(token, request.getHeader("x-forwarded-for"));
            }
        }
        filterChain.doFilter(request,response);
    }

    private String extractToken(HttpServletRequest request){
        Cookie[] cookies =request.getCookies();
        if(cookies != null){
            Cookie authCookie = Arrays.stream(cookies)
                    .filter(cookie -> "authorization".equals(cookie.getName()))
                    .findFirst()
                    .orElse(null);
            if(authCookie != null){
                return authCookie.getValue();
            }
            log.info("인증 토큰이 없음");
            throw new NotFoundTokenException("인증 되지 않은 사용자 입니다!! 로그인 해주세요!!");
        }
        log.info("아예 쿠키 자체가 없음");
        throw new NotFoundTokenException("인증 되지 않은 사용자 입니다!! 로그인 해주세요!!");
    }

}
