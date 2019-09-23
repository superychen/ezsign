package com.ezcsign.service.PayService.tenpay;

import com.ezcsign.service.PayService.tenpay.util.XMLUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ClientRequestHandler extends PrepayIdRequestHandler {

	public ClientRequestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public String getXmlBody() {
		String params = XMLUtil.getRequestXml(super.getAllParameters());
		System.out.println("预支付参数信息："+params);

		return params;
	}
}
