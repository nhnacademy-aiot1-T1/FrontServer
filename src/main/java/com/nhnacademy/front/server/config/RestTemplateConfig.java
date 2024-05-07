package com.nhnacademy.front.server.config;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

  private final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
  @Bean
  RestTemplate restTemplate(RestTemplateBuilder builder) {
    RestTemplate restTemplate = builder
        .setReadTimeout(Duration.ofSeconds(5L))
        .setConnectTimeout(Duration.ofSeconds(5L))
//        .messageConverters(mappingJackson2HttpMessageConverter)
        .build();

    // Spring boot 에서 쓰는 messgeConverter와 restTemplate 에서 쓰는 messageConverter가 달라서 생긴일
    // 따라서 컨버터 설정을 꼭 해줘야함

    return restTemplate;
  }
}
