package com.cqzx.comm.util;

import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.properties.SmsPropertis;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


/**
 * @Description: 发送短信调用接口实现类
 * @Author: cqyc
 * @Date: 2019-7-26
 */

@EnableConfigurationProperties(SmsPropertis.class)
@Component
@Slf4j
public class SendSMS {
    @Autowired
    private SmsPropertis smsPropertis;

    private static final  String SMS_METHOD = "post";
    private static final String SMS_AUTHORIZATION = "Authorization";
    private static final String SMS_APPCODE="APPCODE ";
    private static final String SMS_SIGNNAME="signName";
    private static final String SMS_TEMPID = "tempId";
    private static final String SMS_PHONENUM="phonenum";
    private static final String SMS_PARAM="param";
    private static final String SMS_TEMPID_VAL = "SMS_160760338";
    private static final String SMS_EMAIL="email";
    private static final String SMS_ADDRESS = "address";
    private static final String SMS_RENAME="rename";

    /**
     * 发送短信验证码
     * @param map 设置的短信验证码的参数
     * @param phonenum 发送短信验证码的手机号
     * @return
     */
    public String sendSmsWithcode(Map<String,Object> map,String phonenum){
        String host = smsPropertis.getEcloudUrl(); //云平台测试地址
        String path = smsPropertis.getPath();
        String method = SMS_METHOD;
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        String body = null;
        //从配置文件中获取密钥
        String appcode = smsPropertis.getAppcode();
        String appkey =smsPropertis.getAppkey();
        String appsecret = smsPropertis.getAppsecret();

        //两个空格,英文状态
        String Authorization = appcode+"  "+appkey+"  "+appsecret+"  ";

        headers.put(SMS_AUTHORIZATION,SMS_APPCODE + Authorization);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        //将参数传递map转换成json的形式
        String param = gson.toJson(map);

        //签名名称
        querys.put(SMS_SIGNNAME, "易通签");
        /*
         * 模板编号
         * 登录手机验证码	SMS_160760338
         * 发起签约短地址	SMS_161385016
         * 签约完成短地址	SMS_161570118
         */
        querys.put(SMS_TEMPID, SMS_TEMPID_VAL);
        //接收短信手机号
        querys.put(SMS_PHONENUM, phonenum);
        //模板中需要填写的参数
        querys.put(SMS_PARAM, param);

        try {
            long stratTime = System.currentTimeMillis();
            //发送http请求到
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys,body);
            long endTime = System.currentTimeMillis();
            System.out.println("耗时：-------"+(endTime-stratTime) + "毫秒");
            //获取response的body
            JSONObject jsonObject = HttpUtils.httpResponseToJson(response);

            System.out.println(jsonObject.get("code")+","+jsonObject.get("msg"));
            log.debug("短信验证码返回的结果--->{}",jsonObject.toString());

            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送邮箱验证码
     * @param email
     * @return
     */
    public String sendEmailWithcode(String email){
        String host = smsPropertis.getEcloudUrl(); //云平台测试地址
        String path = smsPropertis.getEmailcode();
        String method = SMS_METHOD;
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        String body = null;
        String appcode = smsPropertis.getAppcode();
        String appkey =smsPropertis.getAppkey();
        String appsecret = smsPropertis.getAppsecret();
        //两个空格,英文
        String Authorization = appcode+"  "+appkey+"  "+appsecret+"  ";
        headers.put(SMS_AUTHORIZATION, SMS_APPCODE + Authorization);
        //接收邮件邮箱
        querys.put(SMS_EMAIL, email);
        try {
            long stratTime = System.currentTimeMillis();
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys,body);
            long endTime = System.currentTimeMillis();
            System.out.println("耗时：-------"+(endTime-stratTime) + "毫秒");
            //获取response的body
            JSONObject jsonObject = HttpUtils.httpResponseToJson(response);
            System.out.println("===" + jsonObject.get("code") + "---" + jsonObject.get("msg") + "--" + jsonObject.get("emailcode"));
            return jsonObject.toString();
        } catch (Exception e) {
            throw new ZxException(ExceptionEnums.SEND_ERROR_CODE);
        }
    }


    /**
     * 发用邮件 --->签约完成邮件
     * @param email 接收邮件邮箱
     * @param address 合同短地址,在登录的时候发送的验证码
     * @param rename 接收方名称
     * @return
     */
    public String sendEmailWithFinsh(String email,String address,String rename){
        String host = smsPropertis.getEcloudUrl(); //云平台测试地址
        String path = smsPropertis.getEmailpath();
        String method = SMS_METHOD;
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        String body = null;
        String appcode = smsPropertis.getAppcode();
        String appkey =smsPropertis.getAppkey();
        String appsecret = smsPropertis.getAppsecret();
        //两个空格 英文状态
        String Authorization = appcode+"  "+appkey+"  "+appsecret+"  ";
        headers.put(SMS_AUTHORIZATION, SMS_APPCODE + Authorization);
        //接收邮件邮箱
        querys.put(SMS_EMAIL, email);
        //邮件标题
        querys.put("subject", "易通签");
        //合同短地址
        querys.put(SMS_ADDRESS, address);
        //接收方名称
        querys.put(SMS_RENAME, rename);
        try {
            long stratTime = System.currentTimeMillis();
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys,body);
            long endTime = System.currentTimeMillis();
            System.out.println("耗时：-------"+(endTime-stratTime) + "毫秒");
            //获取response的body
            JSONObject jsonObject = HttpUtils.httpResponseToJson(response);
            log.debug("邮箱验证码返回的结果--->{}",jsonObject.toString());
            return jsonObject.toString();
            //返回结果{“code”:”200”,”msg”:”发送成功”}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 业务剩余次数邮件通知
     * @param email 邮箱
     * @param rename 名字
     * @param business 内容
     * @param times 次数
     * @return 返回结果
     */
    public String sendEmailTimes(String email,String rename,String business,String times){String host = smsPropertis.getEcloudUrl();//云平台测试地址
        String path = smsPropertis.getEmailtime();
        String method = SMS_METHOD;
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        String body = null;
        String appcode = smsPropertis.getAppcode();
        String appkey =smsPropertis.getAppkey();
        String appsecret = smsPropertis.getAppsecret();
        //两个空格 英文状态
        String Authorization = appcode+"  "+appkey+"  "+appsecret+"  ";
        headers.put("Authorization", "APPCODE " + Authorization);
        //接收邮件邮箱
        querys.put("email", email);
        //邮件标题
        querys.put("subject", "易通签");
        //接收方名称
        querys.put("rename", rename);
        //业务名称
        querys.put("business", business);
        //剩余次数
        querys.put("times", times);
        try {
            long stratTime = System.currentTimeMillis();
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys,body);
            long endTime = System.currentTimeMillis();
            System.out.println("耗时：-------"+(endTime-stratTime) + "毫秒");
            //获取response的body
            JSONObject jsonObject = HttpUtils.httpResponseToJson(response);
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
