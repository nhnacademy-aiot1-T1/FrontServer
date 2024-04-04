package com.nhnacademy.front.server.service;

import com.nhnacademy.front.server.domain.register.RegisterRequestDto;
import com.nhnacademy.front.server.domain.register.ValidationResult;

public interface RegisterService {
  ValidationResult validationRegisterRequest(RegisterRequestDto registerRequestDto);
}
