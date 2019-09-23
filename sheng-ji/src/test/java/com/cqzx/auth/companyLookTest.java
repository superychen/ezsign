package com.cqzx.auth;

import com.cqzx.comm.util.HttpUtils;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class companyLookTest {
    @Test
    public void companyLookTest() throws Exception {
        String  host  =  "http://192.168.228.200:9051"; //测试环境
        String  companypath = "/service/ecloud/ecloudComp/authComp.action";
        String  method = "get";
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        String appcode = "d64ee7a82fb04b82a989d3440b2cc48c";
        String appkey = "00186db542004dd9b57b67cc530da317";
        String appsecret = "2ae2294f4d7a4176fa0885ca4482a244";

        String authorization = appcode+"  "+appkey+"  "+appsecret+"  ";//两个空格,后面以空格分隔
        headers.put("authorization", "APPCODE " + authorization);

        //公司名称
        querys.put("name", "东方中讯数字证书认证有限公司");
        HttpResponse response = HttpUtils.doGet(host, companypath , method, 	headers, querys);
        String str = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject jsonObject = JSONObject.fromObject(str);
        System.out.println(jsonObject.toString());
    }
}
