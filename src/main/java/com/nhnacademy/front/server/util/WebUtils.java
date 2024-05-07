package com.nhnacademy.front.server.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.impl.DefaultJwtParser;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
public class WebUtils {
    private static final UserAgent[] USER_AGENT_LIST = UserAgent.values();
    private static final UserAgent[] USER_AGENT_LIS1 = UserAgent.values();

    private WebUtils() {
        throw new IllegalStateException("util class입니다.");
    }

    public static UserAgent findUserAgent(HttpServletRequest request) {
        String agent = request.getHeader("user-agent");

        if (agent != null) {
            Optional<UserAgent> res = Arrays.stream(USER_AGENT_LIST)
                    .filter(value -> agent.contains(value.name()))
                    .findFirst();

            if (res.isPresent()) {
                return res.get();
            }
        }

        log.info("user agent를 찾을 수 없습니다. user-agent : {}", agent);
        return UserAgent.Unknown;
    }

    /**
     * @return 만약 lite device type이 NORMAL일 경우 true를 반환하고 그 외의 경우 false를 반환 합니다.
     */
    public static String getDeviceType(HttpServletRequest request) {
        Object currentDevice = request.getAttribute("currentDevice");

        if (currentDevice == null) { // todo, to enum type.
            return "unknown";
        }

        return currentDevice.toString().contains("NORMAL") ? "web" : "mobile";
    }

//    public static String temp() { // todo,
//
//    }

    public static boolean isTokenExpired(String jwtToken) {
        JwtParser parser = new DefaultJwtParser();
        try {
            String payload = getPayloadFromToken(jwtToken);
            ObjectMapper mapper = new ObjectMapper();
            TokenPayload tokenDetails = mapper.readValue(payload, TokenPayload.class);
            Date now = new Date();
            return tokenDetails.exp.before(now);
        } catch (JsonProcessingException e) {
            log.info("JWT 토큰의 파싱이 잘못되었음 만료로 처리함");
            return true;
        }
    }

    private static String getPayloadFromToken(String token) {
        String[] parts = token.split("\\.");
        if (parts.length == 3) {
            byte[] decodeBytes = Base64.getUrlDecoder().decode(parts[1]);
            return new String(decodeBytes, StandardCharsets.UTF_8);
        }
        throw new IllegalArgumentException("Invalid Token");
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class TokenPayload {
        private Date exp;
    }
}
