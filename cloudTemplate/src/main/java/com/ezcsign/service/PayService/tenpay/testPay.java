package com.ezcsign.service.PayService.tenpay;

import com.ezcsign.service.PayService.tenpay.weixin.InterFaceWeiXinUtil;

import java.math.BigInteger;
import java.util.UUID;



@SuppressWarnings("unused")
public class testPay {

	public static void main(String[] args) {
		try {
//			PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler();//生成package的请求类
//		    prepayReqHandler.setParameter("appid", ConstantUtil.APP_ID);
//		    prepayReqHandler.setParameter("attach", "自定义文本，会返回回来");
//		    prepayReqHandler.setParameter("body", "商品描述-测试退款05");
//		    prepayReqHandler.setParameter("device_info", "测试支付店");
//		    prepayReqHandler.setParameter("mch_id", ConstantUtil.PARTNER);
//		    prepayReqHandler.setParameter("nonce_str", WXUtil.getUUID());
//		    prepayReqHandler.setParameter("notify_url", ConstantUtil.NOTIFY_URL);
//		    //prepayReqHandler.setParameter("openid", openid);
//		    prepayReqHandler.setParameter("out_trade_no","T2017120817034965171874828");
//		    //prepayReqHandler.setParameter("sign_type", ConstantUtil.SIGN_METHOD);
////		    prepayReqHandler.setParameter("spbill_create_ip", request.getRemoteAddr());
//		    prepayReqHandler.setParameter("spbill_create_ip", "127.0.0.1");
//		    prepayReqHandler.setParameter("total_fee", "1");
//		    prepayReqHandler.setParameter("trade_type", "NATIVE");
//
//		    //增加非参与签名的额外参数
//		    prepayReqHandler.setParameter("sign", prepayReqHandler.createSign());
//		    //设置请求URL
//		    prepayReqHandler.setGateUrl(ConstantUtil.GATEURL);
//		    //获取prepayId
//		    String result = prepayReqHandler.sendPrepayToCodeUrl();
//		    System.out.println(result);
//		    if(result!=null&&result.equals("该订单已支付"))
//		    {
//		        System.out.print("该订单已支付!");
//		        //request.setAttribute();
//		    }
//		    else {
//		        String code_url = result;
//
//		    }
//		    //生成二维码
			InterFaceWeiXinUtil.weChatPay("test2018022415251111", "测试支付超时", "测试支付超时","测试支付超时" ,new BigInteger("1"),null);
//			InterFaceWeiXinUtil.getTradeInfo("T20180208170948");//查询订单状态
//			InterFaceWeiXinUtil.reFound("T2017062212111521671176722", 1, 1);//退款
//			InterFaceWeiXinUtil.reFundQuery("T2017062211111521671176712");
//			InterFaceWeiXinUtil.closeOrder("T2017062115350100005");
//			InterFaceWeiXinUtil.reFundQuery("T2017062211111521671176712");
//			InterFaceWeiXinUtil.downloadBills("20170621","ALL");

		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();

		}
		System.exit(0);
	}
	
	
	
}
