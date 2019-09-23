package com.cqzx;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description: 认证以及数字证书
 * @Author: cqyc
 * @Date: 2019-7-29
        */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.cqzx.feign")
public class AuthCretApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthCretApplication.class);
    }
}
