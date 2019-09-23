package com.ezcsign.service.PayService.tenpay.util;

import org.apache.commons.configuration.Configuration;
public class ConstantUtil {
	/**
	 * 商家可以考虑读取配置文件
	 */

	// 初始化
	// public static String APP_ID = "wx5dbc3815acaf0ab9";// 微信开发平台应用id
	// public static String APP_SECRET = "4b651704404e2534898c8336889ff58a";//
	// 应用对应的凭证
	// // 应用对应的密钥
	//
	// // 通知的URL
	// public static String NOTIFY_URL =
	// "http://183.230.175.249:8007/easyca/certserver/reNotify.do";
	// public static String APP_KEY = "e71b02f6a86c410b9b5472d0b9d0f4e2";
	// public static String PARTNER = "1440832402";// 东方中讯数字证书认证有限公司商户号
	// public static String PARTNER_KEY = "045985";// 商户号对应的密钥

	// 初始化
	public static String APP_ID;// 微信开发平台应用id
	public static String APP_SECRET;// 应用对应的凭证
	public static String SPBILL_CREATE_IP;// 终端IP
	public static String REDIRECT_URL; //手机端支付回调URL
	
	// 通知的URL
	public static String NOTIFY_URL;
	public static String APP_KEY;
	public static String PARTNER;// 东方中讯数字证书认证有限公司商户号
	public static String PARTNER_KEY;// 商户号对应的密钥
	
	
	public static String TOKENURL = "https://api.weixin.qq.com/cgi-bin/token";// 获取access_token对应的url
	public static String GRANT_TYPE = "client_credential";// 常量固定值
	public static String EXPIRE_ERRCODE = "42001";// access_token失效后请求返回的errcode
	public static String FAIL_ERRCODE = "40001";// 重复获取导致上一次获取的access_token失效,返回错误码
	// public static String GATEURL =
	// "https://api.weixin.qq.com/pay/genprepay?access_token=";//获取预支付id的接口url

	public static String GATEURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";// 获取预支付id的接口url
	public static String QUERYURL = "https://api.mch.weixin.qq.com/pay/orderquery";// 查询
	public static String CLOSEORDERURL = "https://api.mch.weixin.qq.com/pay/closeorder ";// 关闭
	public static String DOWNLOADBILLURL = "https://api.mch.weixin.qq.com/pay/downloadbill";// 下载账单
	public static String REFOUND = "https://api.mch.weixin.qq.com/secapi/pay/refund";// 退款
	public static String REFOUNDQUERY = "https://api.mch.weixin.qq.com/pay/refundquery";// 退款查询
	public static String SHORTURL = "https://api.mch.weixin.qq.com/tools/shorturl";// 转换短链接
	public static String ACCESS_TOKEN = "access_token";// access_token常量值
	public static String ERRORCODE = "errcode";// 用来判断access_token是否失效的值
	public static String SIGN_METHOD = "MD5";// 签名算法常量值
	// package常量值
	public static String packageValue = "bank_type=WX&body=%B2%E2%CA%D4&fee_type=1&input_charset=GBK&notify_url=http%3A%2F%2F127.0.0.1%3A8180%2Ftenpay_api_b2c%2FpayNotifyUrl.jsp&out_trade_no=2051571832&partner=1900000109&sign=10DA99BCB3F63EF23E4981B331B0A3EF&spbill_create_ip=127.0.0.1&time_expire=20131222091010&total_fee=1";
	public static String traceid = "testtraceid001";// 测试用户id

	public static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
	// 加载配置文件
	private static Configuration configs;

	static {
		try {
			configs = LoadConfigFileUtil.loadFile("wxinfo.properties");
			APP_ID = configs.getString("APP_ID");
			APP_SECRET = configs.getString("APP_SECRET");
			NOTIFY_URL = configs.getString("NOTIFY_URL");
			APP_KEY = configs.getString("APP_KEY");
			PARTNER = configs.getString("PARTNER");
			PARTNER_KEY = configs.getString("PARTNER_KEY");
			SPBILL_CREATE_IP = configs.getString("SPBILL_CREATE_IP");
			REDIRECT_URL = configs.getString("REDIRECT_URL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
