package com.cqzx.service;

import com.cqzx.domain.Companyinfo;
import com.cqzx.domain.Customer;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * api注册业务接口
 */
public interface ApiRegisterService {

    //api注册企业用户
    Boolean apiRegister(Map<String,String> map) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
