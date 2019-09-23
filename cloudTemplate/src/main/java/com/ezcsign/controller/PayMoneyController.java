package com.ezcsign.controller;

import com.ezcsign.comm.exception.ExceptionEnums;
import com.ezcsign.comm.exception.ZxException;
import com.ezcsign.service.PayMoneyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@RestController
@RequestMapping("/pay")
@Slf4j
public class PayMoneyController {

    @Autowired
    private PayMoneyService payMoneyService;

    /**
     * 支付宝充值，是否已经充值
     * @return
     */
    @PostMapping("/zfb/isPay")
    public Map<String,Object> isZjbPayMoney(HttpSession session){
        String outTradeNo = (String) session.getAttribute("outTradeNo");
        //如果从session里面获取的
        if(StringUtils.isBlank(outTradeNo)){
            throw new ZxException(ExceptionEnums.OUT_TRADE_NO_SELECT_ERROR);
        }
        return payMoneyService.zjbPayMoney(outTradeNo);
    }

    /**
     * 支付宝创建支付二维码接口
     * @param paymoney 充值的金额
     */
    @GetMapping("/zfb/qrCreate")
    public Map<String,Object> zjbQrCreate(@RequestParam("paymoney") String paymoney){
        log.info("支付宝创建二维码接口需要充值的钱---->{}",paymoney);
        return payMoneyService.zjbQrCreate(paymoney);
    }

    /**
     * 微信支付创建二维码
     * @param paymoney 充值的金额
     */
    @GetMapping("/weixin/qrCreate")
    public Map<String,Object> wxQrCreate(@RequestParam("paymoney") String paymoney){
        log.info("微信创建二维码的接口需要重置的钱---->{}",paymoney);
        return payMoneyService.wxQrCreate(paymoney);
    }


    @GetMapping("/weixin/isPayFor")
    public Map<String,Object> isWxPayFor(HttpSession session){
        String outTradeNo = (String) session.getAttribute("outTradeNo");
        //如果从session里面获取的
        if(StringUtils.isBlank(outTradeNo)){
            throw new ZxException(ExceptionEnums.OUT_TRADE_NO_SELECT_ERROR);
        }
        return payMoneyService.isWxPayFor(outTradeNo);
    }

}
