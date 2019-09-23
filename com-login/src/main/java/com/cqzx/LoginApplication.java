package com.cqzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 * @Description:
 * @Author:
 * @Date:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableFeignClients(basePackages = "com.cqzx.feign")
public class LoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class);
    }
}
