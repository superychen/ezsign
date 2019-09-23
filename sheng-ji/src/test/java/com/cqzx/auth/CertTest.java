package com.cqzx.auth;

import com.cqzx.comm.properties.CertProperties;
import com.cqzx.comm.util.CertUtils;
import com.cqzx.comm.util.HttpUtils;
import com.cqzx.comm.util.enums.CertDayEnum;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties(CertProperties.class)
public class CertTest {

    @Autowired
    private CertProperties certProperties;

    @Test
    public void certTest() throws Exception {
        String  host  =  "http://192.168.228.200:9051"; //测试环境
        String  certpath = "/service/ecloud/ecloudCert/applyCert.action";
        String  method = "get";
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        String appcode = "d64ee7a82fb04b82a989d3440b2cc48c";
        String appkey = "00186db542004dd9b57b67cc530da317";
        String appsecret = "2ae2294f4d7a4176fa0885ca4482a244";

        String authorization = appcode+"  "+appkey+"  "+appsecret+"  ";//两个空格,后面以空格分隔
        headers.put("authorization", "APPCODE " + authorization);

        //个人(判断邮箱是否存在)
//		String dn = "E=575934162@qq.com,CN=唐顺余,SN=18725693471,OU=50010119921007603X,L=重庆,C=CN";
//		String dn = "CN=唐顺余,SN=18725693471,OU=50010119921007603X,L=重庆,C=CN";

        //企业，(判断手机号是否存在)
        String dn = "E=55555@qq.com,SN=187256653471,CN=重庆市海力资源服务有限公司,OU=900243MA5YN3EL1F,O=重庆市海创人力资源服务有限公司,L=重庆,ST=重庆,C=CN";
//		String dn = "E=575934162@qq.com,CN=重庆市海创人力资源服务有限公司,OU=91500243MA5YN3EL1F,O=重庆市海创人力资源服务有限公司,L=重庆,ST=重庆,C=CN";
        //生成密钥对
        //CertUtils参考“证书申请”文件夹
        String pkcs8=CertUtils.Genkey(1,1024);
        //生成证书请求pkcs10
        String p10=CertUtils.GenPkcs10(dn, pkcs8, "sha1");
        //证书制作天数
        String VALIDITY= String.valueOf(CertDayEnum.THREE_HUNDRED_AND_SIXTY_DAY.getDay());
        //加密证书的密码
        String pwfpwd="777777";
        querys.put("P10",p10);
        querys.put("DN", dn);
        querys.put("PFXPWD", pwfpwd);
        querys.put("VALIDITY", VALIDITY);
        HttpResponse response  = HttpUtils.doGet(host, certpath , method, 	headers, querys);
        String str = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject jsonObject = JSONObject.fromObject(str);
        System.out.println(jsonObject);

        JSONObject jsonObject1 = JSONObject.fromObject(jsonObject.get("result"));
        String signCert = String.valueOf(jsonObject1.get("signCert"));
        byte[] certBytes=Base64.decode(signCert);
        System.out.println("certBytes = " + certBytes);
        System.out.println("证书CER文件,二进制文件 = " + signCert);
        String certinfoPfx =CertUtils.GenPkcs12(pkcs8,signCert,pwfpwd);
        byte[] pfxbytes=Base64.decode(certinfoPfx);
        System.out.println("pfxbytes = " + pfxbytes);
        System.out.println("证书PFX,二进制文件 = " + certinfoPfx);
        System.out.println("---------解析签名证书---------------");
        String certsn=CertUtils.ParsingCert(signCert, 2, "");
        System.out.println("签名证书序列号： "+certsn);
        String certdn=CertUtils.ParsingCert(signCert, 1, "");
        System.out.println("签名证书主题： "+certdn);
        String certIssuer = CertUtils.ParsingCert(signCert,3,"");
        System.out.println("3证书颁发者主题 = " + certIssuer);
        String certinfoFromtime = CertUtils.ParsingCert(signCert,4,"");
        System.out.println("4证书起始时间 = " + certinfoFromtime);
        String certinfoEndtime = CertUtils.ParsingCert(signCert,5,"");
        System.out.println("5证书结束时间 = " + certinfoEndtime);
        String certinfoAlg = CertUtils.ParsingCert(signCert,7,"");
        System.out.println("7签名算法 = " + certinfoAlg);
        System.out.println("证书密码 = " + pwfpwd);
    }

    @Test
    public void testCert(){
        System.out.println("certProperties = ");
    }
}
