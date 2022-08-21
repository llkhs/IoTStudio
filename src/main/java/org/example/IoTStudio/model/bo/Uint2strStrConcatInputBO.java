package org.example.IoTStudio.model.bo;

import java.lang.Object;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Uint2strStrConcatInputBO {
  private String _a;

  private String _b;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_a);
    args.add(_b);
    return args;
  }
}
