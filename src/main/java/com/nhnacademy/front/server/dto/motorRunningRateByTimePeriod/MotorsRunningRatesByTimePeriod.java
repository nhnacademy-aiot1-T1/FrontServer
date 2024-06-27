package com.nhnacademy.front.server.dto.motorRunningRateByTimePeriod;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MotorsRunningRatesByTimePeriod {

  protected List<MotorsRunningRateData> rates;

}
