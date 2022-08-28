package org.example.IoTStudio.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    @NotNull(message = "账号不允许为空")
    private String username;
    @NotNull(message = "密码不允许为空")
    private String passwd;
}
