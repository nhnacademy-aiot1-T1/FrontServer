package com.nhnacademy.front.server.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomDeserializer {

  @JsonComponent
  public static class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime>{
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss][yyyy-MM-dd'T'HH:mm:ss.SSSSSS]");

      return LocalDateTime.parse(jsonParser.getValueAsString(), formatter);
    }
  }
}
