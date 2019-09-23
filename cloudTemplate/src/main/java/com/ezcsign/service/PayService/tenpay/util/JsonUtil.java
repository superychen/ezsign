package com.ezcsign.service.PayService.tenpay.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

	public static String getJsonValue(String rescontent, String key) {
		String var= null;
		try {
			JSONObject object = JSON.parseObject(rescontent);
//			String json = JSON.toJSONStringWithDateFormat(object, "yyyyMMddHHmmss");
			var = object.getString(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return var;
//		JSONObject jsonObject;
//		String v = null;
//		try {
//			jsonObject = new JSONObject(rescontent);
//			v = jsonObject.getString(key);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return v;
	}
}
