package com.ezcsign.service;

import java.util.Map;

public interface PayMoneyService {
    Map<String,Object> zjbPayMoney(String outTradeNo);

    Map<String,Object> zjbQrCreate(String paymoney);

    Map<String,Object> wxQrCreate(String paymoney);

    Map<String,Object> isWxPayFor(String outTradeNo);
}
