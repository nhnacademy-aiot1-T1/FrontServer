package com.nhnacademy.front.server.domain;

import java.time.LocalDateTime;
import java.util.function.Supplier;
import lombok.Getter;
import org.springframework.web.client.HttpClientErrorException;

/***
 * 기본 응답 포맷
 * @param <T> - 전달될 응답 DTO 타입
 */
public class CommonResponse<T> {

  @Getter
  private final String status;
  @Getter
  private final T data;
  @Getter
  private final String message;
  @Getter
  private final LocalDateTime timestamp;

  private CommonResponse(String status, T data, String message) {
    this.status = status;
    this.data = data;
    this.message = message;
    this.timestamp = LocalDateTime.now();
  }

  public static <T> CommonResponse<T> success(T data, String message) {
    return new CommonResponse<>("success", data, message);
  }

  public static <T> CommonResponse<T> success(T data) {
    return new CommonResponse<>("success", data, null);
  }

  public static <T> CommonResponse<T> fail(String message) {
    return new CommonResponse<>("fail", null, message);
  }

  public T dataOrElseThrow(Supplier<? extends RuntimeException> exceptionSupplier) {
    if (this.status.equals("fail")) {
      throw exceptionSupplier.get();
    }
    return this.data;
  }
}