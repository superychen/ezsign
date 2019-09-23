package com.ezcsign.service.PayService.tenpay;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezcsign.service.PayService.tenpay.client.TenpayHttpClient;
import com.ezcsign.service.PayService.tenpay.util.CommonUtil;
import com.ezcsign.service.PayService.tenpay.util.ConstantUtil;
import com.ezcsign.service.PayService.tenpay.util.XMLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class PrepayIdRequestHandler extends RequestHandler {
	private static Logger logger = LoggerFactory.getLogger(PrepayIdRequestHandler.class);
	
    public PrepayIdRequestHandler() {
        super();
    }
    public PrepayIdRequestHandler(HttpServletRequest request,
                                  HttpServletResponse response) {
        super(request, response);
    }

    // 提交预支付获取预支付ID
    public String sendPrepayToId() {
        String prepayId = "";
        String mweb_url = "";
        String sign = "";
        try {
            String params = XMLUtil.getRequestXml(super.getAllParameters());
            logger.info("获取预支付ID参数："+ params);
            System.out.println("获取预支付ID参数："+ params);

            String result = CommonUtil.httpsRequest(super.getGateUrl(), "POST", params);
            logger.info("获取预支付ID返回信息："+ result);
            System.out.println("获取预支付ID返回信息："+ result);

            Map<String, String> map = XMLUtil.doXMLParse(result);
            //通信标识
            if(map.get("return_code").equals("FAIL")){ //失败
            	System.out.println("预支付通信标识失败,签名或者参数格式校验错误");
            	return "RETURN_FAIL";
            }else{
            	//成功，判断业务结果：result_code
            	mweb_url = map.get("mweb_url");
            	sign = map.get("sign");
            	if(map.get("result_code").equals("FAIL")){
            		logger.info(map.get("err_code_des"));
            		return "RESULT_FAIL";
            	}
            	prepayId = map.get("prepay_id");
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }

        return prepayId +";"+ mweb_url + ";" + sign;
    }

    // 提交预支付获取CodeUrl
	public String sendPrepayToCodeUrl() {
        String code_url = "";
        try {
            String params = XMLUtil.getRequestXml(super.getAllParameters());
            logger.info("获取预支付ID参数："+ params);
            System.out.println("获取预支付ID参数："+ params+"-----1-----");

            String result = CommonUtil.httpsRequest(super.getGateUrl(), "POST", params);
            logger.info("获取预支付ID返回信息："+ result);
            System.out.println("获取预支付ID返回信息："+ result+"---2---");

            Map<String, String> map = XMLUtil.doXMLParse(result);
            //通信标识
            if(!map.get("return_code").equals("SUCCESS")){ //失败
            	code_url = "FAIL";
            	System.out.println("预支付通信标识失败,签名或者参数格式校验错误");
            }else{
            	//成功，判断业务结果：result_code
            	if (map.get("result_code").equals("SUCCESS") && (map.get("code_url") != null && !"undefined".equals(map.get("code_url")))){
                	code_url = map.get("code_url");
                } else{
                	logger.info(map.get("err_code_des"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return code_url;
    }

    /*// 提交预支付
    public String sendPrepay() {
        String code_url = "";
        try {
            String params = XMLUtil.getRequestXml(super.getAllParameters());
            System.out.println("获取预支付ID参数："+ params);

            String result = CommonUtil.httpsRequest(super.getGateUrl(), "POST", params);
            System.out.println("获取预支付ID返回信息："+ result);

            Map<String, String> map = XMLUtil.doXMLParse(result);
            code_url = map.get("code_url");
        }catch (Exception e){
            e.printStackTrace();
        }

        return code_url;
    }*/

    // 判断access_token是否失效
    public String sendAccessToken() {
        String accesstoken = "";
        StringBuffer sb = new StringBuffer("{");
        Set es = super.getAllParameters().entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"appkey".equals(k)) {
                sb.append("\"" + k + "\":\"" + v + "\",");
            }
        }
        String params = sb.substring(0, sb.lastIndexOf(","));
        params += "}";

        String requestUrl = super.getGateUrl();
//		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "requestUrl:" + requestUrl);
        TenpayHttpClient httpClient = new TenpayHttpClient();
        httpClient.setReqContent(requestUrl);
        String resContent = "";
//		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "post data:" + params);
        if (httpClient.callHttpPost(requestUrl, params)) {
            resContent = httpClient.getResContent();
            if (2 == resContent.indexOf(ConstantUtil.ERRORCODE)) {
                accesstoken = resContent.substring(11, 16);//获取对应的errcode的值
            }
        }
        return accesstoken;
    }
}
