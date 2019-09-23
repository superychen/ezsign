package com.cqzx.comm.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

/**
 * @Description: 生成uuid和将密码加密
 * @Author: cqyc
 * @Date: 2019-7-29
 */
@Slf4j
public class MD5Hash {

    /**
     * 创建一个UUID
     * @return UUID
     */
    public static String UUIDCreate(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }


    public static String encodeByMd5(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        Base64.Encoder base64Encoder = Base64.getEncoder();
        // 加密字符串
        String res = base64Encoder.encodeToString(md5.digest(password.getBytes("utf-8")));
        log.debug("用户加密后的结果-->{}",res);
        return res.replaceAll("=","");
    }
}
