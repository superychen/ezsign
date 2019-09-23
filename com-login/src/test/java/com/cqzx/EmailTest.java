package com.cqzx;

import com.cqzx.comm.util.HttpUtils;
import com.cqzx.comm.util.ToJson;
import com.cqzx.comm.util.ValidUtils;
import com.cqzx.domain.Perosoninfo;
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
//@SpringBootTest
//@RunWith(SpringRunner.class)
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
        querys.put("email", "825467364@qq.com");
        //邮件标题
        querys.put("subject", "易通签");
        //合同短地址
        querys.put("address", "fdsa");
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

    @Test
    public void testCustomerToStringMap(){
        String stringMap = "{personinfo_id=123456,personinfo_name=fs_df11,personinfo_idcard=1001001,personinfo_conf_ss=sfdfg}";
        System.out.println("stringMap = " + stringMap);
        //将查询到的结果转换为json字符串
        String data = ToJson.stringToJson(stringMap);
        Perosoninfo peroson = com.alibaba.fastjson.JSONObject.parseObject(data, Perosoninfo.class);
        System.out.println("peroson = " + peroson);

        //
        System.out.println("-----------这个是分割线------------");
        String jsonObject = JSONObject.fromObject(stringMap).toString();
        String convert = ToJson.convert(jsonObject).toString();
        Perosoninfo perosoninfo = com.alibaba.fastjson.JSONObject.parseObject(convert, Perosoninfo.class);
        System.out.println("jsonObject = " + jsonObject);
        System.out.println("perosoninfo = " + perosoninfo.getPersoninfoIdcard());
    }


    //todo 如果判断那边服务转换错误，则使用这个stringMap
    @Test
    public void testStringToMap(){
        String map = "{personinfo_id=123456,personinfo_state=1001010,personinfo_authtype=1001001,personinfo_name=fs_df11,personinfo_idcard=1001001,personinfo_conf_ss=sfdfg}";
        if (map.startsWith("{")) {
            map = map.substring(1, map.length());
        }
        if (map.endsWith("}")) {
            map = map.substring(0, map.length() - 1);
        }
        Map map1 = new HashMap(16);

        String[] out = map.split(",");

        for (String anOut : out) {
            String[] inn = anOut.split("=");
            map1.put(inn[0], inn[1]);
        }
        String jsonObject = JSONObject.fromObject(map1).toString();
        String convert = ToJson.convert(jsonObject).toString();
        Perosoninfo perosoninfo = com.alibaba.fastjson.JSONObject.parseObject(convert, Perosoninfo.class);
        System.out.println("map1 = " + map1);
        System.out.println("jsonObject = " + jsonObject);
        System.out.println("convert = " + convert);
        System.out.println("perosoninfo = " + perosoninfo);
    }

    @Test
    public void testEmailTwice(){
        String host = "http://192.168.228.200:9051"; //云平台测试地址
        String path = "/service/ecloud/ecloudNoteEmail/sendEmailTimesWarn.action";
        String method = "post";
        Map<String, String> headers = new HashMap<String, String>();
        Map<String, String> querys = new HashMap<String, String>();
        String body = null;
        String appcode = "d64ee7a82fb04b82a989d3440b2cc48c";
        String appkey ="00186db542004dd9b57b67cc530da317";
        String appsecret = "2ae2294f4d7a4176fa0885ca4482a244";
        //两个空格 英文状态
        String Authorization = appcode+"  "+appkey+"  "+appsecret+"  ";
        headers.put("Authorization", "APPCODE " + Authorization);
        //接收邮件邮箱
        querys.put("email", "825467364@qq.com");
        //邮件标题
        querys.put("subject", "易通签");
        //接收方名称
        querys.put("rename", "唐顺余");
        //业务名称
        querys.put("business", "PDF合同签署");
        //剩余次数
        querys.put("times", "5");
        try {
            long stratTime = System.currentTimeMillis();
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys,body);
            long endTime = System.currentTimeMillis();
            System.out.println("耗时：-------"+(endTime-stratTime) + "毫秒");
//获取response的body
            JSONObject jsonObject = HttpUtils.httpResponseToJson(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*返回结果{“code”:”200”,”msg”:”发送成功”}
         *	200	发送成功
         *	301	邮箱格式不合法
         *	302	邮箱发送失败*/
    }


}
