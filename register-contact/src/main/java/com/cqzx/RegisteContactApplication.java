package com.cqzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Description: 注册通讯录启动类
 * @Author: cqyc
 * @Date: 2019-7-25
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.cqzx.feign")
@EnableRedisHttpSession
public class RegisteContactApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegisteContactApplication.class);
    }
}
