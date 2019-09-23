package com.ezcsign.service.PayService.tenpay;


import com.ezcsign.service.PayService.tenpay.util.ConstantUtil;
import com.ezcsign.service.PayService.tenpay.util.MD5Util;
import com.ezcsign.service.PayService.tenpay.util.TenpayUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 请求处理类
 * 请求处理类继承此类，重写createSign方法即可。
 * @author miklchen
 *
 */
@SuppressWarnings({ "rawtypes", "unused" })
public class RequestHandler {
	
	/** 网关url地址 */
	private String gateUrl;



	/** 查询订单url地址 */
	private String queryUrl;
	
	/** 密钥 */
	private String key;
	
	/** 请求的参数 */
	private SortedMap parameters;
	
	/** debug信息 */
	private String debugInfo;
	
	protected HttpServletRequest request;
	
	protected HttpServletResponse response;
	
	public RequestHandler() {
		this.parameters = new TreeMap();
		this.debugInfo = "";
	}
	public RequestHandler(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		
		this.gateUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		this.queryUrl = "https://api.mch.weixin.qq.com/pay/orderquery";
		this.key = "";
		this.parameters = new TreeMap();
		this.debugInfo = "";
	}
	
	/**
	*初始化函数。
	*/
	public void init() {
		//nothing to do
	}

	/**
	*获取入口地址,不包含参数值
	*/
	public String getGateUrl() {
		return gateUrl;
	}

	/**
	*设置入口地址,不包含参数值
	*/
	public void setGateUrl(String gateUrl) {
		this.gateUrl = gateUrl;
	}
	public String getQueryUrl() {
		return queryUrl;
	}

	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}

	/**
	*获取密钥
	*/
	public String getKey() {
		return key;
	}

	/**
	*设置密钥
	*/
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * 获取参数值
	 * @param parameter 参数名称
	 * @return String 
	 */
	public String getParameter(String parameter) {
		String s = (String)this.parameters.get(parameter);
		return (null == s) ? "" : s;
	}
	
	/**
	 * 设置参数值
	 * @param parameter 参数名称
	 * @param parameterValue 参数值
	 */
	@SuppressWarnings("unchecked")
	public void setParameter(String parameter, String parameterValue) {
		String v = "";
		if(null != parameterValue) {
			v = parameterValue.trim();
		}
		this.parameters.put(parameter, v);
	}
	
	/**
	 * 返回所有的参数
	 * @return SortedMap
	 */
	public SortedMap getAllParameters() {
		return this.parameters;
	}

	/**
	*获取debug信息
	*/
	public String getDebugInfo() {
		return debugInfo;
	}
	
	/**
	 * 获取带参数的请求URL
	 * @return String
	 * @throws UnsupportedEncodingException
	 */
	public String getRequestURL() throws UnsupportedEncodingException {
		
		this.createSign();
		
		StringBuffer sb = new StringBuffer();
		String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			
			if(!"spbill_create_ip".equals(k)) {
				sb.append(k + "=" + URLEncoder.encode(v, enc) + "&");
			} else {
				sb.append(k + "=" + v.replace("\\.", "%2E") + "&");
			}
		}
		
		//去掉最后一个&
		String reqPars = sb.substring(0, sb.lastIndexOf("&"));
		
		return this.getGateUrl() + "?" + reqPars;
		
	}
	
	public void doSend() throws UnsupportedEncodingException, IOException {
		this.response.sendRedirect(this.getRequestURL());
	}
	
	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	public String createSign() {
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			if(null != v && !"".equals(v)) {
				if(!flag){
					flag = true;
					sb.append(k + "=" + v);
				}else {
					sb.append("&" + k + "=" + v);
				}
			}
		}
		sb.append("&key=" + ConstantUtil.APP_KEY);

		//String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
		//System.out.println("参数编码方式："+ enc);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		
		return sign;
		
	}
	
	/**
	*设置debug信息
	*/
	protected void setDebugInfo(String debugInfo) {
		this.debugInfo = debugInfo;
	}
	
	protected HttpServletRequest getHttpServletRequest() {
		return this.request;
	}
	
	protected HttpServletResponse getHttpServletResponse() {
		return this.response;
	}
	 
}
