package com.cqzx.service;

import com.cqzx.domain.Customer;

import java.util.Map;

public interface UserTypeService {

    Map<String,String> isAuth(Customer customer);

    Map<String, String> qrFor(String customerId);
}


