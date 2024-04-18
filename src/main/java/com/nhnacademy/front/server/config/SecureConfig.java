package com.nhnacademy.front.server.config;

import com.nhnacademy.front.server.filter.TokenExpirationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecureConfig {
    @Bean
    public FilterRegistrationBean<TokenExpirationFilter> registration(TokenExpirationFilter filter){
        FilterRegistrationBean<TokenExpirationFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }
    private final TokenExpirationFilter tokenExpirationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .cors().disable()
                .csrf().and()
                .httpBasic().disable()
                .formLogin().disable()
                .headers().frameOptions().disable()
                .and()
                .addFilterBefore(tokenExpirationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/login","/register","/js/***","/css/**", "/images/**").permitAll()
                                .anyRequest().authenticated())
                .logout().disable();
        return http.build();
    }
}
