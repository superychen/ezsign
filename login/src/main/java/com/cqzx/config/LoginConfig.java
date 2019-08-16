package com.cqzx.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Description: 登录配置类
 * @Author: cqyc
 * @Date: 2019-7-26
 */
@Configuration
public class LoginConfig {
    //配置验证码
    @Bean
    public DefaultKaptcha defaultKaptcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.put("kaptcha.image.width", 60);
        properties.put("kaptcha.image.height", 30);
        properties.put("kaptcha.textproducer.char.length", "4");
        defaultKaptcha.setConfig(new Config(properties));
        return defaultKaptcha;
    }
}
