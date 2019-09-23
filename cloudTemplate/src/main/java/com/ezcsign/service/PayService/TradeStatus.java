package com.ezcsign.service.PayService;/**
 * Created by Administrator on 2019-09-17.
 */

/**
 * @author mojiayi
 * @date 2019-09-17 16:38
 */
public class TradeStatus {
    public String errorMsg="";      //错误信息
    public int errorcode=0;         //交易状态代码：0表示成功，其他表示失败    -6表示订单未支付！！！！！！
    public String payerId="";       //支付账号
    public String payerCode="";     //支付账号ID；
    public String amount="";        //支付金额;
    public String payTime="";       //支付时间;
}
