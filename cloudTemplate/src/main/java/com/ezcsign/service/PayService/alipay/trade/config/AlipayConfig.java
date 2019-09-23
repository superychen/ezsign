package com.ezcsign.service.PayService.alipay.trade.config;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;

public class AlipayConfig {
	// 商户appid
	public static String APPID = "";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "";
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//public static String notify_url = "http://商户网关地址/alipay.trade.wap.pay-JAVA-UTF-8/notify_url.jsp";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	public static String return_url = "";
	// 请求网关地址
	public static String URL = "";
	// 编码
	public static String CHARSET = "";
	// 返回格式
	public static String FORMAT = "";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "";
	// 日志记录目录
	public static String log_path = "";
	// RSA2
	public static String SIGNTYPE = "";
	
	
	static {
        CompositeConfiguration config = new CompositeConfiguration();
        try {
            config.addConfiguration(new PropertiesConfiguration("zfbinfo.properties"));
            APPID = config.getString("appid");
            RSA_PRIVATE_KEY = config.getString("RSA_PRIVATE_KEY");
            return_url= config.getString("return_url");
            URL= config.getString("URL");
            CHARSET=config.getString("CHARSET");
            FORMAT=config.getString("FORMAT");
            ALIPAY_PUBLIC_KEY=config.getString("alipay_public_key");
            log_path=config.getString("log_path");
            SIGNTYPE=config.getString("SIGNTYPE");
        	}catch (Exception e) {
        		 e.printStackTrace();
			}
		}
	}
