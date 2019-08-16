package com.cqzx.test;

import com.cqzx.comm.util.MD5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Description: md5加密
 * @Author: cqyc
 * @Date: 2019-7-29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MD5Test {

    @Test
    public void md5test() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String password = "825467364@qq.com"+"123456";
        String s = MD5Hash.encodeByMd5(password);
        System.out.println(s);
    }


}
