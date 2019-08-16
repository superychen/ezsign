package com.cqzx.auth;

import com.cqzx.comm.util.HttpUtils;
import com.cqzx.comm.util.ToJson;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description: 银行卡4要素测试类
 * @Author: cqyc
 * @Date: 2019-8-01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Bank4Test {

    /**
     * 返回值Json格式如下：
     * {"result":{"message":"成功","res":"1","description":"认证信息匹配"},"code":"200","msg":"成功"}
     * code代表返回值200代表成功 否则为对应的错误码，msg中返回对应的错误信息；如果方法调用成功则在result中返回对应方法结果；
     */
    @Test
    public void testBank4() throws Exception {
        String  host  =  "http://192.168.228.200:9051"; //测试环境
        String  bank4path = "/service/ecloud/ecloudBank/Bankcard4.action";
        String  method = "post";
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        Map<String,String> bodys = new HashMap<>();
        String appcode = "d64ee7a82fb04b82a989d3440b2cc48c";
        String appkey = "00186db542004dd9b57b67cc530da317";
        String appsecret = "2ae2294f4d7a4176fa0885ca4482a244";

        String authorization = appcode+"  "+appkey+"  "+appsecret+"  ";//两个空格,后面以空格分隔
        headers.put("authorization", "APPCODE " + authorization);

        //银行卡号,只要是输入的string就行
        querys.put("bankcard", "6215281052606815");
        //身份证号
        querys.put("idcard", "500240199908190010");
        //姓名
        querys.put("name", "杨晨");
        //电话
        querys.put("mobile", "15923775760");
        bodys = null;

        HttpResponse response = HttpUtils.doPost(host, bank4path, method, headers, querys, bodys);
        String str = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject jsonObject = JSONObject.fromObject(str);

        System.out.println(jsonObject);
    }


    @Test
    public void bigDecimalTest(){
        Random ra =new Random();
        for (int i=0;i<30;i++) {
            BigDecimal bigDecimal = new BigDecimal("0." + (ra.nextInt(9)+1));
            System.out.println("bigDecimal = " + bigDecimal);
        }
    }
    
    @Test
    public void testTojson(){
        String s = ToJson.stringToJson("{fileid=group2;;;M00/01/02/wKjkm11ETAKAXpksAAIt1qlPrgc382.jpg}");
        System.out.println(s);
    }

    @Test
    public void testAuth(){
        String s = "1101001";
        String substring = StringUtils.substring(s, 1, 7);
        System.out.println("substring = " + substring);
        String ss = "{}";
        String sss=StringUtils.remove(ss,"{}");
        System.out.println("ss = " + sss);
    }

    @Test
    public void testFileId(){
        String file = "group1;;;M00/02/00/wKjkmV1T0YWASvxkAAAHbegmzyM.500266";
        String replace = StringUtils.replace(file, ";;;", "/");
        System.out.println("replace = " + replace);
    }
}
