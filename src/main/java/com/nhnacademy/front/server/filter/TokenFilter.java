package com.nhnacademy.front.server.filter;

import com.nhnacademy.front.server.exception.NotFoundTokenException;
import com.nhnacademy.front.server.exception.UnauthorizedException;
import com.nhnacademy.front.server.util.WebUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {

    private static final String[] EXCLUDE_PATH_PREFIX = {"/auth", "/register"};

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return Arrays.stream(EXCLUDE_PATH_PREFIX)
                .anyMatch(prefix -> request.getRequestURI().startsWith(prefix));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie authorizationCookie = WebUtils.findAuthorizationCookie(request.getCookies()).orElseThrow(NotFoundTokenException::new);

        if (!WebUtils.isTokenExpired(authorizationCookie.getValue())) {
            throw new UnauthorizedException("올바른 형식의 토큰이 아니거나, 기한이 만료된 토큰입니다.");
        }

        filterChain.doFilter(request, response);
    }
}
