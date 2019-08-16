package com.cqzx.comm.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@ConfigurationProperties(prefix = "cqzx.cert")
@Data
public class CertProperties {
    private String pwfpwd;
    private Integer rsa1;
    private Integer rsaKeylen1;
    private Integer ras2;
    private Integer rasKeylen2;
    private Integer sm2;
    private Integer sm2Keylen;
    private String hashalg1;
    private String hashalg2;
    private String hashalg3;
    private String host;
    private String certpath;
}
