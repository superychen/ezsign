package com.cqzx.comm.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@ConfigurationProperties(prefix = "cqzx.sms")
@Data
public class SmsPropertis {
    private String appcode;
    private String appkey;
    private String appsecret;
    private String host;
    private String path;
    private String emailpath;
    private String emailcode;

}
