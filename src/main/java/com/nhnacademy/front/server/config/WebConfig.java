package com.nhnacademy.front.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.common.aop.CommonLogger;
import com.nhnacademy.front.server.interceptor.AuthorizationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;
import java.util.List;


@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    RestTemplate restTemplate = builder
        .setConnectTimeout(Duration.ofSeconds(5))
        .setReadTimeout(Duration.ofSeconds(5))
        .build();

    restTemplate.setInterceptors(List.of(new AuthorizationInterceptor()));

    return restTemplate;
  }

  @Bean
  @ConditionalOnProperty(value = "spring.profiles.active", havingValue = "dev")
  public RestTemplate restTemplateMocky(RestTemplateBuilder builder) {
    return builder
        .setConnectTimeout(Duration.ofSeconds(5))
        .setReadTimeout(Duration.ofSeconds(5))
        .build();
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper().registerModule(new JavaTimeModule());
  }

  @Bean
  public DeviceResolverHandlerInterceptor deviceResolverHandlerInterceptor() {
    return new DeviceResolverHandlerInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(deviceResolverHandlerInterceptor());
  }

  @Bean
  public CommonLogger commonLogger() {
    return new CommonLogger();
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("http://localhost:8082")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("*")
        .allowCredentials(true);
  }
}
