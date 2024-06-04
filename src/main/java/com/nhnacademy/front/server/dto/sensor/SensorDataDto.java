package com.nhnacademy.front.server.dto.sensor;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorDataDto {

  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Long sensorId;
  private List<ValueDto> sensorData;

}
