package com.nhnacademy.front.server.interceptor;

import com.nhnacademy.front.server.threadlocal.RegenerateTokenHolder;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RegenerateTokenCheckInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = execution.execute(request, body);
        //Todo 재발급 토큰 이름 정하기!
        if(response.getHeaders().containsKey("regeneratedToken")){
            RegenerateTokenHolder.setData(response.getHeaders().getFirst("regeneratedToken"));
        }
        return response;
    }
}
