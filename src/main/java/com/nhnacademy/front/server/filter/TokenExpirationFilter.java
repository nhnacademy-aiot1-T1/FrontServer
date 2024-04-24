package com.nhnacademy.front.server.filter;

import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.exception.NotFoundTokenException;
import com.nhnacademy.front.server.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

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
        if (!(path.startsWith(LOGIN_PATH) || path.startsWith(REGISTER_PATH) || path.startsWith("/plugins") || path.startsWith("/dist"))) { // 로그인, 회원가입 url은 token 검증을 하지 않습니다, todo, token 검증이 필요하지 않은 url을 정리하면 좋을거 같음.
            String token = extractToken(request);

            authAdapter.checkAccessToken(token, request.getHeader("x-forwarded-for"));
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        Cookie token = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("authorization"))
                .findFirst()
                .orElseThrow(() -> new NotFoundTokenException(request.getRequestURI() + "인증 되지 않은 사용자 입니다."));

        return token.getValue();
    }
}
