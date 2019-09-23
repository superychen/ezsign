package com.ezcsign.service.impl;

import com.ezcsign.service.PayMoneyService;
import com.ezcsign.service.PayService.AliPayApi;
import com.ezcsign.service.PayService.PayResult;
import com.ezcsign.service.PayService.TencentPayApi;
import com.ezcsign.service.PayService.TradeStatus;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Service
public class PayMoneyServiceImpl implements PayMoneyService {

    @Autowired
    private AliPayApi aliPayApi;

    @Autowired
    private TencentPayApi tencentPayApi;

    @Override
    public Map<String, Object> zjbPayMoney(String outTradeNo) {
        Map<String, Object> map = new HashMap<>();
        //查询支付状态;
        String tradeCode=outTradeNo;
        TradeStatus ss= aliPayApi.GetPayStatus(tradeCode);
        map.put("payRes",ss);
        return map;
    }

    /**
     * 支付宝创建支付二维码
     * @param paymoney
     * @return
     */
    @Override
    public Map<String,Object> zjbQrCreate(String paymoney) {
        Map<String, Object> map = new HashMap<>();
        //生成支付二维码;
        PayResult res= aliPayApi.CreatePayCode(paymoney);

        byte[] imgBytes =Base64.decode(res.imgBase64);
        // 2、将字节数组转为二进制
        BASE64Encoder encoder = new BASE64Encoder();
        String binary = encoder.encodeBuffer(imgBytes).trim();
        map.put("binary",binary);
        return map;
    }

    /**
     * 微信支付创建二维码
     * @return
     */
    @Override
    public Map<String, Object> wxQrCreate(String paymoney) {
        Map<String, Object> map = new HashMap<>();
        PayResult res=tencentPayApi.CreatePayCode(paymoney);
        byte[] imgBytes =Base64.decode(res.imgBase64);
        // 2、将字节数组转为二进制
        BASE64Encoder encoder = new BASE64Encoder();
        String binary = encoder.encodeBuffer(imgBytes).trim();
        map.put("binary",binary);
        return map;
    }

    /**
     * 判断是否微信是否充值
     * @param outTradeNo
     */
    @Override
    public Map<String, Object> isWxPayFor(String outTradeNo) {
        Map<String, Object> map = new HashMap<>();
        //查询支付状态;
        String tradeCode = outTradeNo;
        TradeStatus ss;
        try {
            ss = tencentPayApi.GetPayStatus(tradeCode);
            map.put("payRes",ss);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("微信支付出现异常,请稍后尝试");
        }

    }

}
