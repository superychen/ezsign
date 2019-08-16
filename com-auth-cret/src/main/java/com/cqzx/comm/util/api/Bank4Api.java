package com.cqzx.comm.util.api;

import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.properties.AuthProperties;
import com.cqzx.comm.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 银行卡4要素验证
 * @Author: cqyc
 * @Date: 2019-8-01
 */
@Component
@Slf4j
@EnableConfigurationProperties(AuthProperties.class)
public class Bank4Api {

    private final static String BANK_AUTHORIZATION="authorization";
    private final static String BANK_APPCODE="APPCODE ";
    private final static String BANK_BANKCARD="bankcard";
    private final static String BANK_IDCARD="idcard";
    private final static String BANK_NAME="name";
    private final static String BANK_MOBILE="mobile";

    @Autowired
    private AuthProperties authProperties;

    public String bank4Auth(Map<String,String> map){
        String  host  =  authProperties.getHost(); //测试环境
        String  bank4path = authProperties.getBankPath();
        String  method = "post";
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        Map<String,String> bodys = new HashMap<>();
        String appcode = authProperties.getAppcode();
        String appkey = authProperties.getAppkey();
        String appsecret = authProperties.getAppsecret();

        String authorization = appcode+"  "+appkey+"  "+appsecret+"  ";//两个空格,后面以空格分隔
        headers.put(BANK_AUTHORIZATION, BANK_APPCODE + authorization);

        //银行卡号,只要是输入的string就行
        querys.put(BANK_BANKCARD, map.get("bankid"));
        //身份证号
        querys.put(BANK_IDCARD, map.get("idnumber"));
        //姓名
        querys.put(BANK_NAME, map.get("fullname"));
        //电话
        querys.put(BANK_MOBILE, map.get("phonenum"));
        bodys = null;

        HttpResponse response = null;
        try {
            response = HttpUtils.doPost(host, bank4path, method, headers, querys, bodys);
            String str = EntityUtils.toString(response.getEntity(), "utf-8");
            JSONObject jsonObject = JSONObject.fromObject(str);
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ZxException(ExceptionEnums.BANK4_AUTH_ERROR);
        }

    }
}
