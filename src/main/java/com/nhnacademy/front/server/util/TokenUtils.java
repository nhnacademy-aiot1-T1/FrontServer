package com.nhnacademy.front.server.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Slf4j
public class TokenUtils {
    private TokenUtils(){
        throw new IllegalArgumentException("생성자가 필요하지 않습니다!");
    }
    public static boolean isTokenExpired(String jwtToken){
        try{
            String payload = getPayloadFromToken(jwtToken);
            ObjectMapper mapper = new ObjectMapper();
            TokenPayload tokenDetails = mapper.readValue(payload, TokenPayload.class);
            Date now = new Date();
            return  tokenDetails.exp.before(now);
        }catch (JsonProcessingException e){
         log.info("JWT 토큰의 파싱이 잘못되었음 만료로 처리함");
         return true;
        }
    }

    private static String getPayloadFromToken(String token){
        String[] parts = token.split("\\.");
        if(parts.length==3){
            byte[] decodeBytes = Base64.getUrlDecoder().decode(parts[1]);
            return new String(decodeBytes, StandardCharsets.UTF_8);
        }
        throw new IllegalArgumentException("Invalid Token");
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class TokenPayload{
        private Date exp;
    }
}
