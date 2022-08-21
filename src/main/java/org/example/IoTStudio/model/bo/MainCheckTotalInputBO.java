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
public class MainCheckTotalInputBO {
  private String _address;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_address);
    return args;
  }
}
