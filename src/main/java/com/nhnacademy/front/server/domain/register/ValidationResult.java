package com.nhnacademy.front.server.domain.register;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 회원의 회원가입 조건의 통과 유무를 검증하는 메서드 입니다!
 * @author AoiTuNs
 * @version 1.0
 */
@AllArgsConstructor
@Getter
public class ValidationResult {
  private boolean isValid;
  private String message;
}
