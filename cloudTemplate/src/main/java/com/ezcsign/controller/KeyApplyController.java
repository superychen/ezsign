package com.ezcsign.controller;

import com.alibaba.fastjson.JSONObject;
import com.ezcsign.comm.exception.ExceptionEnums;
import com.ezcsign.comm.exception.ZxException;
import com.ezcsign.entity.Customer;
import com.ezcsign.service.KeyApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description: 钥匙盘控制层
 * @Author: cqyc
 * @Date: 2019-9-10
 */
@RestController
@RequestMapping("/key")
public class KeyApplyController {
    @Autowired
    private KeyApplyService keyApplyService;

    @GetMapping("/customer")
    public Map<String,Object> nowCustomer(HttpSession session){
        Map<String,Object> customerMap = (Map<String, Object>) session.getAttribute("customer");
        return customerMap;
    }

    /**
     * 申请钥匙盘
     * @param name 姓名
     * @param telephone 电话
     * @param address 邮寄地址
     * @return
     */
    @PostMapping("/applyKey")
    public Boolean applyKey(@RequestParam("name") String name,
                            @RequestParam("telephone") String telephone,
                            @RequestParam("address") String address,
                            HttpSession session){
        Map<String,Object> customerMap = (Map<String, Object>) session.getAttribute("customer");
        if(customerMap.size() <= 0){
            throw new ZxException(ExceptionEnums.SESSION_HAS_ERROR);
        }
        String jsonObject = new JSONObject(customerMap).toJSONString();
        Customer customer = JSONObject.parseObject(jsonObject, Customer.class);
        keyApplyService.applyKey(name,telephone,address,customer);
        return true;
    }

}
