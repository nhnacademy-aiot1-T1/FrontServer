package com.nhnacademy.front.server.dto.sensor;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValueDto {

  private LocalDateTime time;
  private double value;

}
