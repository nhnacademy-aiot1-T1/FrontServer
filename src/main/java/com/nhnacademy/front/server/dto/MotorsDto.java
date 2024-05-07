package com.nhnacademy.front.server.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
@Getter
@NoArgsConstructor
public class MotorsDto {

  private List<MotorDto> motors;

  @JsonCreator
  private MotorsDto(List<MotorDto> motors) {
    this.motors = motors;
  }
}
