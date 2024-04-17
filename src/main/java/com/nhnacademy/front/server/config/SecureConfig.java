package com.nhnacademy.front.server.config;

import com.nhnacademy.front.server.filter.TokenExpirationFilter;
import lombok.RequiredArgsConstructor;
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
    private final TokenExpirationFilter tokenExpirationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                //.cors()
                //.and()
                //Todo 다하면 csrf disable 해제하기!
                .csrf().disable()
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
