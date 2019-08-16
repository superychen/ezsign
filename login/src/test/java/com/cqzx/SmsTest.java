package com.cqzx;

import com.cqzx.comm.util.HttpUtils;
import com.cqzx.comm.util.MD5Hash;
import com.cqzx.comm.util.ValidUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SmsTest {
    @Test
    public void testSMS() throws Exception {
        String host = "http://192.168.228.200:9051"; //云平台测试地址
        String path = "/service/ecloud/ecloudNoteEmail/sendNote.action";
        String method = "post";
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        String body = null;
        String appcode = "d64ee7a82fb04b82a989d3440b2cc48c";
        String appkey ="00186db542004dd9b57b67cc530da317";
        String appsecret = "2ae2294f4d7a4176fa0885ca4482a244";
        //两个空格,英文状态
        String Authorization = appcode+"  "+appkey+"  "+appsecret+"  ";
        headers.put("Authorization", "APPCODE " + Authorization);

        Map<String, Object> map = new HashMap<>();
        map.put("var1", "dagezaoshanghao");

        //import com.google.gson.Gson;
        //import com.google.gson.GsonBuilder;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String param = gson.toJson(map);

        //签名名称
        querys.put("signName", "易通签");
        /*
         * 模板编号
         * 登录手机验证码	SMS_160760338
         * 发起签约短地址	SMS_161385016
         * 签约完成短地址	SMS_161570118
         */
        querys.put("tempId", "SMS_160760338");
        //接收短信手机号
        querys.put("phonenum", "18325180610");
        //模板中需要填写的参数
        querys.put("param", param);
        try {
            long stratTime = System.currentTimeMillis();
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys,body);
            long endTime = System.currentTimeMillis();
            System.out.println("耗时：-------"+(endTime-stratTime) + "毫秒");
            //获取response的body
            JSONObject jsonObject = HttpUtils.httpResponseToJson(response);
            System.out.println(jsonObject.get("code")+","+jsonObject.get("msg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 测试当前手机号码的格式是否正确
     */
    @Test
    public void testSmsRegix(){
        String phone = "13896410328";
        boolean mobile = ValidUtils.isMobile(phone);
        System.out.println("mobile = " + mobile);
    }

    @Test
    public void MD5test() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String password = "825467364@qq.com"+"123456";
        String s = MD5Hash.encodeByMd5(password);
        System.out.println(s);
    }


}
