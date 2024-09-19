package com.sjsk.passjava.dto;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class UserLoginDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @NotNull(message = "userId不能为空")
    private String userId;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

}