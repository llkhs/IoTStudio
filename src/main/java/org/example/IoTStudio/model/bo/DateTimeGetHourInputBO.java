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
public class DateTimeGetHourInputBO {
  private BigInteger timestamp;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(timestamp);
    return args;
  }
}
