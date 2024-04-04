package com.nhnacademy.front.server.domain.register;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidationResult {
  private boolean isValid;
  private String message;
}
