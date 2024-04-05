package com.nhnacademy.front.server.service.impl;

import com.nhnacademy.front.server.adapter.AuthAdapter;
import com.nhnacademy.front.server.domain.register.RegisterCheckDto;
import com.nhnacademy.front.server.domain.register.RegisterRequestDto;
import com.nhnacademy.front.server.domain.register.ValidationResult;
import com.nhnacademy.front.server.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
//Todo 스프링을 사용하는 검정으로 변경...
  private final AuthAdapter authAdapter;
  @Override
  public ValidationResult validationRegisterRequest(RegisterCheckDto registerCheckDto) {
    if(registerCheckDto.getId().toCharArray().length<=4){
      return new ValidationResult(false,"아이디가 형식에 맞지 않습니다! 4글자 이상이어야 합니다!");
    }
    if(!registerCheckDto.getPassword().equals(registerCheckDto.getPasswordRetype())){
      return new ValidationResult(false,"비밀번호와 비밀번호 확인이 일치하지 않습니다!");
    }
    if(registerCheckDto.getPassword().toCharArray().length<=9){
      return new ValidationResult(false,"비밀번호는 9글자 이상이어야 합니다!");
    }
    return new ValidationResult(true,"회원가입 가능");
  }

  @Override
  public void registerCreated(RegisterRequestDto registerRequestDto) {
    authAdapter.registerUser(registerRequestDto);
  }
}
