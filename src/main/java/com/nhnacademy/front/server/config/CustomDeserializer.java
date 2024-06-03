package com.nhnacademy.front.server.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDeserializer {

  private CustomDeserializer() {

  }

  @JsonComponent
  public static class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser,
        DeserializationContext deserializationContext) throws IOException {
      return LocalDateTime.parse(jsonParser.getValueAsString(),
          DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
  }
}
