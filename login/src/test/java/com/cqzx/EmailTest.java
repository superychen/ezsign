package com.cqzx;

import com.cqzx.comm.util.HttpUtils;
import com.cqzx.comm.util.ValidUtils;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
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
public class EmailTest {

    /**
     * 发送合同签署完成后的邮件
     */
    @Test
    public void testEmail(){
        String host = "http://192.168.228.200:9051"; //云平台测试地址
        String path = "/service/ecloud/ecloudNoteEmail/sendEmailAddress.action";
        String method = "post";
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        String body = null;
        String appcode = "d64ee7a82fb04b82a989d3440b2cc48c";
        String appkey ="00186db542004dd9b57b67cc530da317";
        String appsecret = "2ae2294f4d7a4176fa0885ca4482a244";
        //两个空格 英文状态
        String Authorization = appcode+"  "+appkey+"  "+appsecret+"  ";
        headers.put("Authorization", "APPCODE " + Authorization);
        //接收邮件邮箱
        querys.put("email", "2430728530@qq.com");
        //邮件标题
        querys.put("subject", "易通签");
        //合同短地址
        querys.put("address", "dageZaoShangHao");
        //接收方名称
        querys.put("rename", "大哥");
        try {
            long stratTime = System.currentTimeMillis();
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys,body);
            long endTime = System.currentTimeMillis();
            System.out.println("耗时：-------"+(endTime-stratTime) + "毫秒");
            //获取response的body
            JSONObject jsonObject = HttpUtils.httpResponseToJson(response);
            //返回结果{“code”:”200”,”msg”:”发送成功”}
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试发送邮箱验证码
     */
    @Test
    public void testEmailCode(){
        String host = "http://192.168.228.200:9051"; //云平台测试地址
        String path = "/service/ecloud/ecloudNoteEmail/sendEmailCode.action";
        String method = "post";
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        String body = null;
        String appcode = "d64ee7a82fb04b82a989d3440b2cc48c";
        String appkey ="00186db542004dd9b57b67cc530da317";
        String appsecret = "2ae2294f4d7a4176fa0885ca4482a244";
        //两个空格,英文
        String Authorization = appcode+"  "+appkey+"  "+appsecret+"  ";
        headers.put("Authorization", "APPCODE " + Authorization);
        //接收邮件邮箱
        querys.put("email", "825467364@qq.com");
        try {
            long stratTime = System.currentTimeMillis();
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys,body);
            long endTime = System.currentTimeMillis();
            System.out.println("耗时：-------"+(endTime-stratTime) + "毫秒");
            //获取response的body
            JSONObject jsonObject = HttpUtils.httpResponseToJson(response);
            System.out.println("===" + jsonObject.get("code") + "---" + jsonObject.get("msg") + "--" + jsonObject.get("emailcode"));
            com.alibaba.fastjson.JSONObject jsonObject1 = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString());
            System.out.println("fastjson获取的验证码==》"+jsonObject1.getString("code"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试邮箱格式是否正确
     */
    @Test
    public void testEmailRegix(){
        String eamil = "825467364@abc.com";
        boolean b = ValidUtils.checkEmail(eamil);
        System.out.println("b = " + b);
    }

}
