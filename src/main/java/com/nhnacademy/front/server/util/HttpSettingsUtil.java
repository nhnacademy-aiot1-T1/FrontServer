package com.nhnacademy.front.server.util;


import com.nhnacademy.common.dto.CommonResponse;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpSettingsUtil {

  private static final MediaType CONTENT_TYPE = MediaType.APPLICATION_JSON;

  private HttpSettingsUtil() {
    throw new IllegalStateException("Utility class");
  }

  @NotNull
  public static HttpHeaders setupHttpHeaders() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(CONTENT_TYPE);
    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

    return httpHeaders;
  }

  @NotNull
  public static <T> ResponseEntity<CommonResponse<T>> restTemplateExchange(
      RestTemplate restTemplate, String url,
      HttpMethod httpMethod, HttpEntity<String> request) {
    return restTemplate.exchange(
        url,
        httpMethod,
        request,
        new ParameterizedTypeReference<>() {
        });
  }

  public static <T> boolean isStatusNotOk(@NotNull ResponseEntity<CommonResponse<T>> response) {
    return !response.getStatusCode().equals(HttpStatus.OK);
  }

}
