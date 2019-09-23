package com.ezcsign.service.PayService.tenpay.weixin;


import java.util.Map;

/**
 * Created by Administrator on 2017/3/3 0003.
 */
public interface Products {

    public Map<String,Object> getProductInfoByid(String product_id)throws Exception;
}
