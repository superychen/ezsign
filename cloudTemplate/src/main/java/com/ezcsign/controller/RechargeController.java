package com.ezcsign.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ezcsign.comm.exception.ExceptionEnums;
import com.ezcsign.comm.exception.ZxException;
import com.ezcsign.entity.Customer;
import com.ezcsign.service.RechargeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @Description: 充值接口
 * @Author: cqyc
 * @Date: 2019-9-5
 */
@RestController
@RequestMapping("/recharge")
@Slf4j
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    /**
     * 查询对应的套餐
     */
    @GetMapping("/packageCus")
    public Map<String, Object> packageUserOrCompany(HttpSession session){
        Customer customer = objectCustomer(session);
        return rechargeService.packageUserOrCompany(customer);
    }

    /**
     * 根据用户查询对应的服务剩余数量
     * @param isShowType 1是代表查询全部，2是代表只查询对应的服务配置
     */
    @GetMapping("/restSer")
    public Map<String,Object> restServiceUserOrMember(@RequestParam("isShowType") Integer isShowType,
                                                      HttpSession session){
        Customer customer = objectCustomer(session);
        return rechargeService.restServiceUserOrMember(customer,isShowType);
    }


    /**
     * 查询所有的发票信息
     */
    @GetMapping("/allRecharge")
    public List<Map<String,Object>> allRecharges(@RequestParam(value = "fromTime",required = false) String fromTime,
                                                 @RequestParam(value = "toTime",required = false) String toTime,
                                                 @RequestParam(value = "isInvoice",required = false) Integer isInvoice,
                                                 HttpSession session) throws ParseException {
        Customer customer = objectCustomer(session);
        return rechargeService.allRecharges(fromTime,toTime,isInvoice,customer);
    }

    /**
     * 充值成功后调用套餐接口
     * @param packInfo 套餐信息
     * @return true/false
     */
    @PostMapping("/payMoney")
    public Boolean isPayMoneyRight(@RequestBody JSONObject packInfo,
                                   HttpSession session){
        log.info("套餐信息接收--->{}",packInfo);
        Customer customer = objectCustomer(session);
        return rechargeService.isPayMoneyRight(packInfo,customer);
    }

    /**
     * 充值成功后调用服务配置接口
     * @param serverInfo 服务配置信息
     * @return true/false
     */
    @PostMapping("/payMoney2")
    public Boolean isPayMoneyLeft(@RequestBody JSONObject serverInfo,
                                  HttpSession session){
        Customer customer = objectCustomer(session);
        return rechargeService.isPayMoneyLeft(serverInfo,customer);
    }

    protected Customer objectCustomer(HttpSession session){
        Map<String,Object> customerMap = (Map<String, Object>) session.getAttribute("customer");
        if(customerMap.size() <= 0){
            throw new ZxException(ExceptionEnums.SESSION_HAS_ERROR);
        }
        String jsonObject = new JSONObject(customerMap).toJSONString();
        Customer customer = JSONObject.parseObject(jsonObject, Customer.class);
        return customer;
    }



}
