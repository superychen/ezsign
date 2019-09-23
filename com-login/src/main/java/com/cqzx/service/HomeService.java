package com.cqzx.service;

import com.cqzx.domain.Customer;

import java.util.Map;

public interface HomeService {
    Map<String,Object> homePersonOrCompany(Customer customer);
}
