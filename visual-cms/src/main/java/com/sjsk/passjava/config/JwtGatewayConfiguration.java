package com.sjsk.passjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Gateway 配置
 *
 * @date 2022-08-09
 */
@Configuration
public class JwtGatewayConfiguration {

    /**
     * 用户注册的密码也是经过 BCryptPasswordEncoder.encode 加密后存放到数据库
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
