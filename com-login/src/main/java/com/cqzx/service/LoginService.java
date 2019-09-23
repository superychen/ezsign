package com.cqzx.service;

import com.cqzx.domain.Customer;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface LoginService {

    Customer loginWithPassword(String loginname, String password, String imgcode) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    //redis
    void kapatchaImg(String str,String loginname);

    Customer loginWithCode(String loginname, Integer loginType, String code);

    String sendWithPhone(String loginname);

    String sendWithEamil(String loginname);

    Boolean changeUserType(Customer customer,Integer userType);

    String sendEmailAddress(String email,String rename,String business,String times);
}
