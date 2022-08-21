package org.example.IoTStudio.model.bo;

import java.lang.Object;
import java.lang.String;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainCheckTotal1InputBO {
  private String _address;

  private BigInteger _firstMonth;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_address);
    args.add(_firstMonth);
    return args;
  }
}
