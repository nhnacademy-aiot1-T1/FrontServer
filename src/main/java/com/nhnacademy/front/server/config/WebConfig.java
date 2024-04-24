package com.nhnacademy.front.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;


/**
 * 스프링 부트를 활용하기 위한 webConfig 파일입니다!
 * @author AoiTuNa
 * @version 1.1
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * bean으로 등록된 restTemplate 객체입니다!
     * @return 설정된 빌더를 리턴합니다!
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5))
            .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
