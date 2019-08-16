package com.cqzx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.util.ToJson;
import com.cqzx.domain.Customer;
import com.cqzx.domain.Perosoninfo;
import com.cqzx.feign.DbService;
import com.cqzx.service.FindPasswordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 找回密码业务处理层
 * @Author: cqyc
 * @Date: 2019-8-15
 */
@Service
@Slf4j
public class FindPasswordServiceImpl implements FindPasswordService {

    @Autowired
    private DbService dbService;

    /**
     * 找回用户名
     * @param name 姓名
     * @param idCart 身份证号
     * @param telephone 电话号码
     */
    @Override
    public Map<String, String> findLoginname(String name, String idCart, String telephone) {
        Map<String, Object> map = dbService.get("personinfo", "personinfo_id", idCart);
        log.debug("--->{}",map);
        //将从数据库服务的查询出来的数据转换为json字符串

        if(StringUtils.equals(String.valueOf(map.get("data")),"{}")){
            throw new ZxException(ExceptionEnums.FIND_USERNAME_ERROR);
        }
        String yc = ToJson.stringToJson(String.valueOf(map.get("data")));
        Perosoninfo personinfo = JSONObject.parseObject(yc, Perosoninfo.class);
        //判断姓名以及对应的电话是否相同
        if (!StringUtils.equals(personinfo.getPersoninfoName(),name) || !StringUtils.equals(personinfo.getPersoninfoTelephone(),telephone)) {
            throw new ZxException(ExceptionEnums.FIND_USERNAME_ERROR);
        }

        Map<String, Object> cusMap = dbService.get("customer", "customer_personid", personinfo.getPersoninfoId());
        log.debug("--->{}",cusMap);
        if(StringUtils.equals(String.valueOf(cusMap.get("data")),"{}")){
            throw new ZxException(ExceptionEnums.FIND_USERNAME_ERROR);
        }
        String cusStr = ToJson.stringToJson(String.valueOf(map.get("data")));
        Customer customer = JSONObject.parseObject(cusStr, Customer.class);

        Map<String, String> res = new HashMap<>();
        res.put("loginname",customer.getCustomerLoginname());
        res.put("status","200");
        res.put("msg","ok");
        return res;
    }
}
