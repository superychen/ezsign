package com.cqzx.comm.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: 读取配置文件中认证的配置信息
 * @Author: cqyc
 * @Date: 2019-7-31
 */
@ConfigurationProperties(prefix = "ecloud")
@Data
public class AuthProperties {
    private String appcode;
    private String appkey;
    private String appsecret;
    private String ecloudUrl;
    private String requestPath;
    private String authPath;
    private String bankPath;
    private String companyPath;
}
