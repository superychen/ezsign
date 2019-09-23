package com.cqzx.controller;

import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.domain.Customer;
import com.cqzx.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description: 进入首页控制层
 * @Author: cqyc
 * @Date: 2019-9-20
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/info")
    public Map<String,Object> homePersonOrCompany(HttpSession session){
        Map<String,Object> cusMap = (Map<String, Object>) session.getAttribute("customer");
        if(cusMap.size() <= 0){
            throw new ZxException(ExceptionEnums.SESSION_USER_ERROR);
        }
        String jsonObject = new JSONObject(cusMap).toJSONString();
        Customer customer = JSONObject.parseObject(jsonObject, Customer.class);
        return homeService.homePersonOrCompany(customer);
    }
}
