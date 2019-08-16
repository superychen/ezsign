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
 * @Description: 企查查接口
 * @Author: cqyc
 * @Date: 2019-8-01
 */
@Component
@Slf4j
@EnableConfigurationProperties(AuthProperties.class)
public class CompanyApi {

    private final static String COMPANY_METHOD="get";
    private final static String COMPANY_AUTHORIZATION="authorization";
    private final static String COMPANY_APPCODE="APPCODE ";


    @Autowired
    private AuthProperties authProperties;

    public String companyAuth(String companyName){
        String  host  =  authProperties.getHost(); //测试环境
        String  companypath = authProperties.getCompanyPath();
        String  method = COMPANY_METHOD;
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        String appcode = authProperties.getAppcode();
        String appkey = authProperties.getAppkey();
        String appsecret = authProperties.getAppsecret();

        String authorization = appcode+"  "+appkey+"  "+appsecret+"  ";//两个空格,后面以空格分隔
        headers.put(COMPANY_AUTHORIZATION, COMPANY_APPCODE + authorization);

        //公司名称
        querys.put("name", companyName);
        HttpResponse response = null;
        try {
            response = HttpUtils.doGet(host, companypath , method, 	headers, querys);
            String str = EntityUtils.toString(response.getEntity(), "utf-8");
            JSONObject jsonObject = JSONObject.fromObject(str);
            return jsonObject.get("result").toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ZxException(ExceptionEnums.HAS_COMPANY_ERROR);
        }

    }
}
