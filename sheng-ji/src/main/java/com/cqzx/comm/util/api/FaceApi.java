package com.cqzx.comm.util.api;

import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.properties.AuthProperties;
import com.cqzx.comm.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 人脸识别api
 * @Author: cqyc
 * @Date: 2019-7-31
 */
@Slf4j
@Component
@EnableConfigurationProperties(AuthProperties.class)
public class FaceApi {

    private final static String FACE_REQUEST_METHOD="GET";
    private final static String FACE_AUTH_METHOD="post";
    private final static String FACE_AUTHORIZATION="authorization";
    private final static String FACE_APPCODE="APPCODE ";
    private final static String FACE_REQUEST_ID = "requestid";
    private final static String FACE_IDCARD_NAME = "idcardname";
    private final static String FACE_IDCARD_NUMBER = "idcardnumber" ;
    @Autowired
    private AuthProperties authProperties;
    /**
     * 随机生成四位随机数,然后存入到redis中
     * @return 返回的是一个redis中的key，从key中取出随机的四位数
     */
    public String faceRequstId(){
        String host = authProperties.getEcloudUrl();
        String path = authProperties.getRequestPath();
        String method = FACE_REQUEST_METHOD;
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        try {
            long stratTime = System.currentTimeMillis();
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            long endTime = System.currentTimeMillis();
            System.out.println("耗时：-------"+(endTime-stratTime) + "毫秒");
            //获取response的body
            JSONObject jsonObject = HttpUtils.httpResponseToJson(response);
            String math = String.valueOf(jsonObject.get("math")); //4位随机数
            // 随机数地址，
            String requestid = String.valueOf(jsonObject.get("requestid"));

            log.debug("math ===={} ====requestid==={}",math,requestid);
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ZxException(ExceptionEnums.FACE_REQUEST_CODE_ERROR);
        }
    }


    public String faceAuth(String requestId,String idcardname,String idcardnumber,byte[] bytes){
        String  host  =  authProperties.getEcloudUrl(); //测试环境
        String  facepath = authProperties.getAuthPath();
        String  method = FACE_AUTH_METHOD;
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        String bodys = null;
        String appcode = authProperties.getAppcode();
        String appkey = authProperties.getAppkey();
        String appsecret = authProperties.getAppsecret();

        String authorization = appcode+"  "+appkey+"  "+appsecret+"  ";//两个空格,后面以空格分隔
        headers.put(FACE_AUTHORIZATION, FACE_APPCODE + authorization);

        //随机数在Redis中的id，第一步中返回的requestid
        querys.put(FACE_REQUEST_ID,requestId);
        querys.put(FACE_IDCARD_NAME,idcardname); //身份证姓名
        querys.put(FACE_IDCARD_NUMBER,idcardnumber); //身份证号

        bodys = Base64.encodeBase64String(bytes); //Base64.encodeBase64String(“文件byte[]”)语音视频文件字符串

        HttpResponse response = null;
        try {
            response = HttpUtils.doPost(host, facepath , method, headers, querys, bodys);
            String str = EntityUtils.toString(response.getEntity(), "utf-8");
            JSONObject jsonObject = JSONObject.fromObject(str);
            log.info("人脸识别返回的结果-->{}",jsonObject);
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ZxException(ExceptionEnums.FACE_AUTH_ERROR);
        }
    }

}
