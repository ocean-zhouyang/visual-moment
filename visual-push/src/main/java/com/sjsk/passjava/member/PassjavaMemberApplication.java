package com.sjsk.passjava.member;

//import com.sjsk.passjava.common.config.WebMvcConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * @author wukong
 */
@EnableFeignClients(basePackages = "com.sjsk.passjava.member.feign")
@MapperScan("com.sjsk.passjava.member.dao")
@SpringBootApplication(scanBasePackages = "com.sjsk.passjava")
//@Import({WebMvcConfig.class})
public class PassjavaMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassjavaMemberApplication.class, args);
    }

}
