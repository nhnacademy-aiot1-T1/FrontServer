package com.nhnacademy.front.server.properties;

import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ConfigurationProperties(prefix = "oauth.payco")
public class PaycoProperties {

  @NotNull
  private String clientId;

  @NotNull
  private String redirectUri;

  @NotNull
  private String secret;
}
