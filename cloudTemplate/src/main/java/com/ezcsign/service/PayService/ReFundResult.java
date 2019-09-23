package com.ezcsign.service.PayService;/**
 * Created by Administrator on 2019-09-17.
 */

/**
 * @author mojiayi
 * @date 2019-09-17 17:11
 */
public class ReFundResult {
    public String tradeNo="";       //本次支付的交易代码;
    public String amount="";        //本次支付金额
    public String errorMsg="";      //错误信息
    public int errorcode=0;         //错误代码：0表示成功，其他表示失败

}
