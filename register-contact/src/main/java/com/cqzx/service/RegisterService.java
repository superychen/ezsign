package com.cqzx.service;


import com.cqzx.domain.Customer;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface RegisterService {

    Customer register(Customer customer, Integer registerType, String code) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    String sendCode(String registerName, Integer registerType);

    Boolean adminRegister(String name, String telephone, String idcard) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
