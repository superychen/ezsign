package com.cqzx.comm.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@ConfigurationProperties(prefix = "ecloud")
@Data
public class SmsPropertis {
    private String appcode;
    private String appkey;
    private String appsecret;
    private String ecloudUrl;
    private String path;
    private String emailpath;
    private String emailcode;
    private String emailtime;
}
