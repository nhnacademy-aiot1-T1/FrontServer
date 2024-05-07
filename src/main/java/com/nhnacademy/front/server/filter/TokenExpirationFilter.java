//package com.nhnacademy.front.server.filter;
//
//import com.nhnacademy.front.server.adapter.AuthAdapter;
//import com.nhnacademy.front.server.exception.NotFoundTokenException;
//import io.jsonwebtoken.Jwt;
//import io.jsonwebtoken.impl.DefaultJwtParser;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Set;
//
//@Slf4j
////@Component
//@RequiredArgsConstructor
//public class TokenExpirationFilter implements Filter {
//
//    private static final Set<String> EXCLUDE_PATH = Set.of(); //Set.of("/login", "/register", "/plugins", "/dist");
//
//    private final AuthAdapter authAdapter;
//
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        if (EXCLUDE_PATH.contains(request.getRequestURI())) { // 로그인, 회원가입 url은 token 검증을 하지 않습니다, todo, token 검증이 필요하지 않은 url을 정리하면 좋을거 같음.
//            String token = extractToken(request);
//
//            Jwt jwt = new DefaultJwtParser().parse(token);
//
//            // todo, jwt token 유효기간 확인, 만료되었으면 게이트 웨이에 재발급 요청, cookie set.  body에서 받고 cookie set.
//            // todo, 변조된 토큰이 경우 게이트 웨이에서 어떤 값을 주는지? - 401 code 반
//
//            authAdapter.requestTokenRefresh(token, request.getHeader("x-forwarded-for")); // todo, 없으면 다시 login으로
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private String extractToken(HttpServletRequest request) {
//        if (request.getCookies() == null) throw new NotFoundTokenException(request.getRequestURI() + " : 인증 되지 않은 사용자 입니다.");
//
//        // todo, util class로 빼기
//        Cookie token = Arrays.stream(request.getCookies())
//                .filter(cookie -> cookie.getName().equals("Authorization"))
//                .findFirst()
//                .orElseThrow(() -> new NotFoundTokenException(request.getRequestURI() + " : 인증 되지 않은 사용자 입니다."));
//
//        return token.getValue();
//    }
//}
