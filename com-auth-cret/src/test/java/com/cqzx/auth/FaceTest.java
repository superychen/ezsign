package com.cqzx.auth;

import com.cqzx.comm.util.HttpUtils;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 人脸识别test
 * @Author: cqyc
 * @Date: 2019-7-31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FaceTest {
    /**
     * 页面生成4位随机数
     */
    @Test
    public void faceTest(){
        String host = "http://192.168.228.200:9051";
        String path = "/service/ecloud/ecloudface/geturl.action";
        String method = "GET";
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
            System.out.println("===" + math + "==" + requestid );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void faceVideoTest(String requestId,String idcardname,String idcardnumber) throws Exception {
        String  host  =  "http://192.168.228.200:9051"; //测试环境
        String  facepath = "/service/ecloud/ecloudface/writeIdcardAndVideo.action";
        String  method = "post";
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        String bodys = null;
        String appcode = "";
        String appkey = "";
        String appsecret = "";

        String authorization = appcode+"  "+appkey+"  "+appsecret+"  ";//两个空格,后面以空格分隔
        headers.put("authorization", "APPCODE " + authorization);

        //随机数在Redis中的id，第一步中返回的requestid
        querys.put("requestid",requestId);
        querys.put("idcardname",idcardname); //身份证姓名
        querys.put("idcardnumber",idcardnumber); //身份证号
        bodys = ""; //Base64.encodeBase64String(“文件byte[]”)语音视频文件字符串

        HttpResponse response = HttpUtils.doPost(host, facepath , method, headers, querys, bodys);
        String str = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject jsonObject = JSONObject.fromObject(str);
        System.out.println(jsonObject);
    }
}
