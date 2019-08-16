package com.cqzx.service;

import java.util.Map;

/**
 * 找回密码业务 处理层
 */
public interface FindPasswordService {

    Map<String,String> findLoginname(String name, String idCart, String telephone);

}
