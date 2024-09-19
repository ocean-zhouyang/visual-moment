package com.sjsk.passjava.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT 属性配置
 *
 * @date 2022-08-09
 */
@Data
@ConfigurationProperties(prefix = "ocean.jwt")
@Component
public class JwtProperties {

    /**
     * 是否开启JWT，即注入相关的类对象
     */
    private Boolean enabled;
    /**
     * JWT 密钥
     */
    private String secret;
    /**
     * accessToken 有效时间
     */
    private Long expiration;
    /**
     * 前端向后端传递JWT时使用HTTP的header名称，前后端要统一
     */
    private String header;
    /**
     * 用户登录-用户名参数名称
     */
    private String userParamName = "userId";
    /**
     * 用户登录-密码参数名称
     */
    private String pwdParamName = "password";
    /**
     * 是否使用默认的JWTAuthController
     */
    private Boolean useDefaultController = false;

    private String skipValidUrl;

}