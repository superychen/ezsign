package com.cqzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Description: 认证以及数字证书
 * @Author: cqyc
 * @Date: 2019-7-29
        */
@SpringBootApplication
@EnableDiscoveryClient
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 360)//共享配置超时时间为360s
@EnableFeignClients(basePackages = "com.cqzx.feign")
public class AuthCretApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthCretApplication.class);
    }
}
