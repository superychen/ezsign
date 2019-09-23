package com.ezcsign.service.PayService;/**
 * Created by Administrator on 2019-09-17.
 */


import com.ezcsign.service.PayService.alipay.trade.utils.ZxingUtils;
import com.ezcsign.service.PayService.tenpay.ClientRequestHandler;
import com.ezcsign.service.PayService.tenpay.PrepayIdRequestHandler;
import com.ezcsign.service.PayService.tenpay.util.CommonUtil;
import com.ezcsign.service.PayService.tenpay.util.ConstantUtil;
import com.ezcsign.service.PayService.tenpay.util.WXUtil;
import com.ezcsign.service.PayService.tenpay.util.XMLUtil;
import com.ezcsign.service.PayService.tenpay.weixin.InterFaceWeiXinUtil;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author mojiayi
 * @date 2019-09-17 18:08
 */
@Component
public class TencentPayApi {
    private static Logger logger = LoggerFactory.getLogger(InterFaceWeiXinUtil.class);

    @Autowired
    private HttpSession session;

    //创建支付二维码;
    public PayResult CreatePayCode(String amount)
    {
        PayResult pRes=new PayResult();
        pRes.imgBase64=null;
        pRes.errorMsg="未提交支付请求";
        pRes.amount=amount;
        pRes.errorcode=-1;

        String outTradeNo = "esign" + System.currentTimeMillis()+ (long) (Math.random() * 10000000L);
        pRes.tradeNo=outTradeNo;

        String subject = "易通签商务云平台账户充值";
        String body = "易通签账户购买服务需充值"+amount+"元";
        String WeiXinFee="000";
        try {
            float fee = Float.valueOf(amount);
            WeiXinFee = String.valueOf((int) (fee * 100));
        } catch (Exception ex)
        {
            WeiXinFee = "0";
            pRes.amount = "0";
            pRes.errorMsg = "支付金额有误";
            pRes.errorcode = -2;
            return pRes;
        }

        try
        {
            PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler();//生成package的请求类
            prepayReqHandler.setParameter("appid", ConstantUtil.APP_ID);
            prepayReqHandler.setParameter("attach", body);
            prepayReqHandler.setParameter("body", subject);
            prepayReqHandler.setParameter("device_info", "易通签商务云平台");
            prepayReqHandler.setParameter("mch_id", ConstantUtil.PARTNER);
            prepayReqHandler.setParameter("nonce_str", WXUtil.getUUID());
            prepayReqHandler.setParameter("notify_url", ConstantUtil.NOTIFY_URL);
            //prepayReqHandler.setParameter("openid", openid);
            prepayReqHandler.setParameter("out_trade_no", outTradeNo);
            //prepayReqHandler.setParameter("sign_type", ConstantUtil.SIGN_METHOD);
            //prepayReqHandler.setParameter("spbill_create_ip", request.getRemoteAddr());
            prepayReqHandler.setParameter("spbill_create_ip", "127.0.0.1");
            prepayReqHandler.setParameter("total_fee", WeiXinFee);
            prepayReqHandler.setParameter("trade_type", "NATIVE");

            //增加非参与签名的额外参数
            prepayReqHandler.setParameter("sign", prepayReqHandler.createSign());
            //设置请求URL
            prepayReqHandler.setGateUrl(ConstantUtil.GATEURL);
            //获取prepayId
            String result = prepayReqHandler.sendPrepayToCodeUrl();

            session.setAttribute("outTradeNo",outTradeNo);

            System.out.println(result);
            if (result != null && result.equals("该订单已支付"))
            {
                pRes.errorMsg = "该订单已支付";
                pRes.errorcode = -4;
                System.out.print( pRes.errorMsg);
                return pRes;
            }
            else
            {
                //生成二维码
                //String payUrl= InterFaceWeiXinUtil.weChatPay(outTradeNo, "测试支付超时", "测试支付超时", "测试支付超时", new BigInteger("3"), null);
                byte[] imgBytes= ZxingUtils.getQRCodeImgeIO(result,256);
                pRes.imgBase64 =Base64.toBase64String( imgBytes);
                pRes.errorcode=0;
                pRes.errorMsg="提交订单成功";
                logger.info("提交微信订单"+outTradeNo+"订单金额："+amount+"   ");
            }

        }
        catch (Exception e)
        {
            // TODO: handle exception
            pRes.amount = "0";
            pRes.errorMsg = "提交订单出现错误";
            pRes.errorcode = -3;
            return pRes;
        }
        return pRes;
    }


    public ReFundResult ReFundPay(String tradeNo, String amount)
    {
        ReFundResult rRes=new ReFundResult();
        rRes.tradeNo=tradeNo;
        rRes.errorcode=-1;
        rRes.errorMsg="退款失败";
        rRes.amount=amount;
        String outTradeNo =tradeNo;
        String refundAmount = "000";
        try
        {
            float fee = Float.valueOf(amount);
            refundAmount = String.valueOf((int) (fee * 100));
        } catch (Exception ex)
        {
            refundAmount = "0";
            rRes.amount = "0";
            rRes.errorMsg = "支付金额有误";
            rRes.errorcode = -2;
            return rRes;
        }

        String refundReason = "本次充值出现问题，暂停交易，并退还付款";

        ClientRequestHandler cl = new ClientRequestHandler(null, null);
        cl.setParameter("appid", ConstantUtil.APP_ID);
        cl.setParameter("mch_id", ConstantUtil.PARTNER);
        cl.setParameter("out_trade_no", outTradeNo);
        cl.setParameter("nonce_str", WXUtil.getUUID());
        String out_refund_no = WXUtil.getUUID();
        cl.setParameter("out_refund_no", out_refund_no);
        cl.setParameter("total_fee", refundAmount + "");
        cl.setParameter("refund_fee", refundAmount + "");
        cl.setParameter("op_user_id", ConstantUtil.PARTNER + "");
        cl.setParameter("sign", cl.createSign());
        String result = CommonUtil.httpsRequest2(ConstantUtil.REFOUND, XMLUtil.getRequestXml(cl.getAllParameters()));
        System.out.println(result);
        try
        {
            Map<String, String> map = XMLUtil.doXMLParse(result);
            if (!map.get("return_code").equals("SUCCESS"))
            {
                rRes.amount = "0";
                rRes.errorMsg = "退款失败！";
                rRes.errorcode = -3;
                System.out.println("退款失败！");
                return rRes;

            } else {

                if (!map.get("result_code").equals("SUCCESS"))
                {
                    String rs = "公众账号ID:" + map.get("appid") + "\n" + "商户号:" + map.get("mch_id") + "\n" + "业务结果:失败" + map.get("err_code_des") + "\n";
                    System.out.println(rs);
                    // throw new Exception("业务结果:失败！");
                    rRes.amount = "0";
                    rRes.errorMsg = "业务结果:"+map.get("result_code");
                    rRes.errorcode = -4;
                    System.out.println(rRes.errorMsg);
                    return rRes;
                } else
                {
                    rRes.amount = amount;
                    rRes.errorMsg = "退款成功";
                    rRes.errorcode = 0;
                    System.out.println(rRes.errorMsg);
                    return rRes;
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return  rRes;
        }
    }


    /**
     * @param tradeNo
     * @return
     */
    //查询付款状态
    public TradeStatus GetPayStatus(String tradeNo) throws Exception
    {
        TradeStatus tStatus=new TradeStatus();
        tStatus.errorcode=-1;
        tStatus.errorMsg="查询支付状态失败";

        //TradeInfo tradeInfo = new TradeInfo();
        ClientRequestHandler cl = new ClientRequestHandler(null, null);
        cl.setParameter("appid", ConstantUtil.APP_ID);
        cl.setParameter("mch_id", ConstantUtil.PARTNER);
        cl.setParameter("out_trade_no", tradeNo);
        cl.setParameter("nonce_str", WXUtil.getUUID());
        cl.setParameter("sign", cl.createSign());
        String result = CommonUtil.httpsRequest(ConstantUtil.QUERYURL, "POST", XMLUtil.getRequestXml(cl.getAllParameters()));
        Map<String, String> map = XMLUtil.doXMLParse(result);
        if (!map.get("return_code").equals("SUCCESS"))
        {

            //通信标识
            tStatus.errorcode=-2;
            tStatus.errorMsg="微信查询订单通信标识失败，签名或者参数格式校验错误!";
            System.out.println("查询失败！");
        }

        else
        {
            //业务结果判断：SUCCESS/FAIL
            if (!map.get("result_code").equals("SUCCESS"))
            {
                String rs = "公众账号ID:" + map.get("appid") + "\n" + "商户号:" + map.get("mch_id") + "\n"  + "商户订单号:" + tradeNo + "\n" + "业务结果:" + map.get("err_code_des") + "\n";
                //tradeInfo.setTrade_state("FAIL");
                logger.info(rs);
                tStatus.errorcode=-2;
                tStatus.errorMsg="微信支付失败!";
            }
            else
            {
                String rs = "公众账号ID:" + map.get("appid") + "\n" + "商户号:" + map.get("mch_id") + "\n" + "商户订单号:" + map.get("out_trade_no") + "\n" + "用户标示:" + map.get("openid") + "\n"
                        + "交易类型:" + map.get("trade_type") + "\n" + "交易状态:" + map.get("trade_state") + "\n" + "付款银行:" + map.get("bank_type") + "\n"
                        + "标价金额:" + map.get("total_fee") + "\n" + "应结订单金额:" + map.get("settlement_total_fee") + "\n"
                        + "支付完成时间:" + map.get("time_end") + "\n" + "交易状态描述:" + map.get("trade_state_desc") + "\n";
                logger.info(rs);
                if (map.get("trade_state").compareTo("NOTPAY") == 0)
                {
                    tStatus.errorcode = -6;
                    tStatus.errorMsg = "交易未支付!";
                }
                else if(map.get("trade_state").compareTo("SUCCESS") == 0)
                {
                    tStatus.errorcode = 0;
                    tStatus.errorMsg = "订单已支付";
                }
                else if(map.get("trade_state").compareTo("REFUND") == 0)
                {
                    tStatus.errorcode = -7;
                    tStatus.errorMsg = "订单已退款";
                }
                else
                {
                    tStatus.errorcode = -3;
                    tStatus.errorMsg = "订单状态："+map.get("trade_state");
                }

                String WeixinFee=map.get("total_fee");
                try
                {
                    Float ff= Float.valueOf(WeixinFee);
                    ff=ff/100;
                    WeixinFee=String.valueOf(ff);
                }
                catch (Exception ex)
                {

                }
                tStatus.amount= WeixinFee;
                tStatus.payTime=map.get("time_end");
                tStatus.payerCode =map.get("transaction_id");
//                tradeInfo.setAppid(map.get("appid"));
//                tradeInfo.setMch_id(map.get("mch_id"));
//                tradeInfo.setOut_trade_no(map.get("out_trade_no"));
//                tradeInfo.setOpenid(map.get("openid"));
//                tradeInfo.setTrade_type(map.get("trade_type"));
//                tradeInfo.setTrade_state(map.get("trade_state"));
//                tradeInfo.setBank_type(map.get("bank_type"));
//                tradeInfo.setTotal_fee(map.get("total_fee"));
//                tradeInfo.setSettlement_total_fee(map.get("settlement_total_fee"));
//                tradeInfo.setTime_end(map.get("time_end"));
//                tradeInfo.setTrade_state_desc(map.get("trade_state_desc"));
//                tradeInfo.setTransaction_id(map.get("transaction_id"));
            }
        }
        return  tStatus;
    }





    public static void main(String[] args)throws Exception
    {
        TencentPayApi api=new TencentPayApi();
        PayResult res=api.CreatePayCode("0.01");
        byte[] imgBytes =Base64.decode(res.imgBase64);
        // 2、将字节数组转为二进制
        BASE64Encoder encoder = new BASE64Encoder();
        String binary = encoder.encodeBuffer(imgBytes).trim();
        System.out.println(binary);
//
//        PdfControl.saveFile(Base64.decode(res.imgBase64),"C:\\Users\\Administrator\\Desktop\\payimg1.png");
//        TradeStatus tStatus=  api.GetPayStatus(res.tradeNo);

//        String no="esigntrade156877999013565980";
//        ReFundResult rRes= api.ReFundPay(no,"0.01");
//        TradeStatus tStatus1=  api.GetPayStatus(no);


    }


}
