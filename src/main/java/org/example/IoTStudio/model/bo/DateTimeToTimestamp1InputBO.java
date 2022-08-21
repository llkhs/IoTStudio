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
public class DateTimeToTimestamp1InputBO {
  private BigInteger year;

  private BigInteger month;

  private BigInteger day;

  private BigInteger hour;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(year);
    args.add(month);
    args.add(day);
    args.add(hour);
    return args;
  }
}
