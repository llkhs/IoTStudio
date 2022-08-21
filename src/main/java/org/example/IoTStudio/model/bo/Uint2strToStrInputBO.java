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
public class Uint2strToStrInputBO {
  private BigInteger value;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(value);
    return args;
  }
}
