package com.sjsk.passjava.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 电话
     */
    private String mobile;

}