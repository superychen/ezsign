package com.ezcsign.service;

import com.alibaba.fastjson.JSONObject;
import com.ezcsign.entity.Customer;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface RechargeService {

    Map<String,Object> packageUserOrCompany(Customer customer);

    Map<String,Object> restServiceUserOrMember(Customer customer,Integer isShowType);

    List<Map<String, Object>> allRecharges(String fromTime, String toTime, Integer isInvoice, Customer customer) throws ParseException;

    Boolean isPayMoneyRight(JSONObject packInfo,  Customer customer);

    Boolean isPayMoneyLeft(JSONObject serverInfo, Customer customer);
}
