package com.nhnacademy.front.server.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.nhnacademy.front.server.enums.DeviceType;
import com.nhnacademy.front.server.enums.UserAgent;
import com.nhnacademy.front.server.exception.InvalidTokenException;
import com.nhnacademy.front.server.exception.NotFoundTokenException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Slf4j
public class WebUtils {
    public static final String REDIRECT_PREFIX = "redirect:";
    private static final UserAgent[] USER_AGENT_LIST = UserAgent.values();

    private WebUtils() {
        throw new IllegalStateException("util class입니다.");
    }

    public static UserAgent findUserAgent(HttpServletRequest request) {
        String agent = request.getHeader("user-agent");

        if (agent != null) {
            Optional<UserAgent> res = Arrays.stream(USER_AGENT_LIST)
                    .filter(value -> agent.toUpperCase().contains(value.name()))
                    .findFirst();

            if (res.isPresent()) {
                return res.get();
            }
        }

        log.info("user agent를 찾을 수 없습니다. user-agent : {}", agent);
        return UserAgent.UNKNOWN;
    }

    /**
     * @return 만약 lite device type이 NORMAL일 경우 true를 반환하고 그 외의 경우 false를 반환 합니다.
     */
    public static DeviceType getDeviceType(HttpServletRequest request) {
        Object currentDevice = request.getAttribute("currentDevice");

        if (currentDevice == null) {
            return DeviceType.UNKNOWN;
        }

        return currentDevice.toString().contains("NORMAL") ? DeviceType.WEB : DeviceType.MOBILE;
    }

    public static boolean isTokenExpired(String jwt) {
        try {
            Date now = new Date();

            return JWT.decode(jwt).getExpiresAt().after(now);
        } catch (JWTDecodeException e) {
            log.error(e.getMessage());

            throw new InvalidTokenException();
        }
    }

    public static Optional<Cookie> findAuthorizationCookie(Cookie[] cookies) {
        if (cookies == null) throw new NotFoundTokenException();

        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("Authorization"))
                .findFirst();
    }
}
