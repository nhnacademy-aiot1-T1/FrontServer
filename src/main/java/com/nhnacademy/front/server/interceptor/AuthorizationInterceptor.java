package com.nhnacademy.front.server.interceptor;

import com.nhnacademy.front.server.util.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * rest template 요청을 보내기 전 interceptor해서, 필요한 header들을 넣어줍니다.
 */
@RequiredArgsConstructor
public class AuthorizationInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpServletRequest prevRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); // todo, 경고 문구 제거.
        HttpHeaders headers = request.getHeaders();

        if (prevRequest.getMethod().equalsIgnoreCase("post")) {
            headers.setContentType(MediaType.APPLICATION_JSON);
        }

        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.add("X-USER-IP", prevRequest.getHeader("X-FORWARDED-FOR") == null ? "127.0.0.1" : prevRequest.getHeader("X-FORWARDED-FOR")); // todo, test code.
        headers.add("X-USER-DEVICE", WebUtils.getDeviceType(prevRequest).name());
        headers.add("X-USER-BROWSER", WebUtils.findUserAgent(prevRequest).name());

        return execution.execute(request, body);
    }
}
