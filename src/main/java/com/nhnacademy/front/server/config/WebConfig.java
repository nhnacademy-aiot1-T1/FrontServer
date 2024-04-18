package com.nhnacademy.front.server.config;

import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.adapter.impl.AuthAdapterImpl;
import com.nhnacademy.front.server.filter.TokenExpirationFilter;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * 스프링 부트를 활용하기 위한 wevConfig 파일입니다!
 * @author AoiTuNa
 * @version 1.1
 * @see #restTemplate()
 */
@Configuration
public class WebConfig {

    /**
     * bean으로 등록된 restTemplate 객체입니다!
     * @return 설정된 빌더를 리턴합니다!
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    /**
     * 모든 페이지에 접근하기전에 클라이언트의 쿠키에 인증 토큰의 유무를 확인하는 filter의 규칙을 추가합니다!<br/>
     * 모든 요청에서 토큰의 유무를 검증합니다!
     * @param authService 토큰의 유무를 검증하기 위한 service 객체입니다!
     * @return 필터규칙을 리턴합니다!
     */

}
