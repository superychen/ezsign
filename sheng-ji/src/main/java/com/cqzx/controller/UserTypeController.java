package com.cqzx.controller;

import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.util.JavaBeanUtil;
import com.cqzx.comm.util.QRCodeUtil;
import com.cqzx.domain.Customer;
import com.cqzx.service.UserTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 进入用户类型页面
 * @Author: cqyc
 * @Date: 2019-8-22
 */
@RestController
@Slf4j
@RequestMapping("/type")
public class UserTypeController {

    @Autowired
    private UserTypeService userTypeService;

    /**
     * 进入用户类型的页面时候访问
     * @param session
     * @return 返回类型
     */
    @GetMapping("/is/auth")
    public Map<String,String> isAuth(HttpSession session){
        Map<String,Object> customerMap = (Map<String, Object>) session.getAttribute("customer");
        if (customerMap == null) {
            throw new ZxException(ExceptionEnums.LOGIN_ERROR);
        }
        String jsonObject = new JSONObject(customerMap).toJSONString();
        Customer customer = JSONObject.parseObject(jsonObject, Customer.class);
        return userTypeService.isAuth(customer);
    }

    /**
     * 创建二维码，返回图片的二进制
     * @param content
     * @return
     */
    @GetMapping("/qrCreat")
    public Map<String,String> qrCreate(@RequestParam("content") String content){
        //返回二进制
        HashMap<String, String> map = new HashMap<>();
        String ss = QRCodeUtil.generalQRCode(content);
        map.put("qr",ss); //key的名称为二维码的key
        return map;
    }

    /**
     * 轮询redis中的人脸识别是否成功
     * @param customerId 这个为当前用户的id，可能是注册的customerId，也有可能是登录用户的id
     */
    @GetMapping("/qrFor")
    public  Map<String, String> qrFor(@RequestParam("customerId") String customerId){
        return userTypeService.qrFor(customerId);
    }

    /**
     * 进入首页的时候查看当前的userId是否存在
     */
    @GetMapping("/customer")
    public Map<String,Integer> homeCusOrPerson(HttpSession session){
        Map<String,Object> cusMap = (Map<String, Object>) session.getAttribute("customer");
        if(cusMap == null){
            throw new ZxException(ExceptionEnums.LOGIN_ERROR);
        }
        String jsonObject = new JSONObject(cusMap).toJSONString();
        Customer customer = JSONObject.parseObject(jsonObject, Customer.class);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("userType",customer.getUserType());
        return map;
    }

}
