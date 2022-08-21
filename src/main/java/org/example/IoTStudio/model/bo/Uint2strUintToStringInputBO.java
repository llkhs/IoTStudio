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
public class Uint2strUintToStringInputBO {
  private BigInteger _uint;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_uint);
    return args;
  }
}
