package com.ezcsign.service;

import com.ezcsign.entity.Customer;

public interface KeyApplyService {
    void applyKey(String name, String telephone, String address, Customer customer);
}
