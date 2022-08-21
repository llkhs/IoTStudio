package org.example.IoTStudio.model.bo;

import java.lang.Object;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateTimeGetDaysInMonthInputBO {
  private BigInteger month;

  private BigInteger year;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(month);
    args.add(year);
    return args;
  }
}
