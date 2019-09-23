package com.cqzx.comm.util.cert;

import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.properties.AuthProperties;
import com.cqzx.comm.properties.CertProperties;
import com.cqzx.comm.util.CertUtils;
import com.cqzx.comm.util.HttpUtils;
import com.cqzx.comm.util.MD5Hash;
import com.cqzx.comm.util.enums.CertDayEnum;
import com.cqzx.domain.Certinfo;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Component
@EnableConfigurationProperties({CertProperties.class,AuthProperties.class})
public class CreateCert {

    @Autowired
    private CertProperties certProperties;

    @Autowired
    private AuthProperties authProperties;

    public Certinfo createCert(String certMsg) {
        String  host  =  certProperties.getEcloudUrl(); //测试环境
        String  certpath = certProperties.getCertpath();
        String  method = "get";
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        String appcode = authProperties.getAppcode();
        String appkey = authProperties.getAppkey();
        String appsecret = authProperties.getAppsecret();

        String authorization = appcode+"  "+appkey+"  "+appsecret+"  ";//两个空格,后面以空格分隔
        headers.put("authorization", "APPCODE " + authorization);

        //个人(判断邮箱是否存在)
//		String dn = "E=575934162@qq.com,CN=唐顺余,SN=18725693471,OU=50010119921007603X,L=重庆,C=CN";
//		String dn = "CN=唐顺余,SN=18725693471,OU=50010119921007603X,L=重庆,C=CN";

        //企业，(判断手机号是否存在)
        //"E=575934162@qq.com,SN=18725693471,CN=重庆市海创人力资源服务有限公司,OU=91500243MA5YN3EL1F,O=重庆市海创人力资源服务有限公司,L=重庆,ST=重庆,C=CN";
        String dn = certMsg;
//		String dn = "E=575934162@qq.com,CN=重庆市海创人力资源服务有限公司,OU=91500243MA5YN3EL1F,O=重庆市海创人力资源服务有限公司,L=重庆,ST=重庆,C=CN";
        //生成密钥对
        String pkcs8= null; //CertUtils参考“证书申请”文件夹
        try {
            pkcs8=CertUtils.Genkey(certProperties.getRas2(),certProperties.getRsaKeylen1());
            //生成证书请求pkcs10
            String p10=CertUtils.GenPkcs10(dn, pkcs8, certProperties.getHashalg1());
            //证书制作天数
            String VALIDITY= String.valueOf(CertDayEnum.THREE_HUNDRED_AND_SIXTY_DAY.getDay());
            //加密证书的密码
            String pwfpwd=certProperties.getPwfpwd();
            querys.put("P10",p10);
            querys.put("DN", dn);
            querys.put("PFXPWD", pwfpwd);
            querys.put("VALIDITY", VALIDITY);
            HttpResponse response  = HttpUtils.doGet(host, certpath , method, 	headers, querys);
            String str = EntityUtils.toString(response.getEntity(), "utf-8");
            JSONObject jsonObject = JSONObject.fromObject(str);
//            System.out.println(jsonObject);

            JSONObject jsonObject1 = JSONObject.fromObject(jsonObject.get("result"));
            String signCert = String.valueOf(jsonObject1.get("signCert"));
//            System.out.println("证书CER文件,二进制文件 = " + signCert);
            String certinfoPfx =CertUtils.GenPkcs12(pkcs8,signCert,pwfpwd);
//            System.out.println("证书PFX,二进制文件 = " + certinfoPfx);
//            System.out.println("---------解析签名证书---------------");
            String certsn=CertUtils.ParsingCert(signCert, 2, "");
//            System.out.println("签名证书序列号： "+certsn);
            String certdn=CertUtils.ParsingCert(signCert, 1, "");
//            System.out.println("签名证书主题： "+certdn);
            String certIssuer = CertUtils.ParsingCert(signCert,3,"");
//            System.out.println("3证书颁发者主题 = " + certIssuer);
            String certinfoFromtime = CertUtils.ParsingCert(signCert,4,"");
//            System.out.println("4证书起始时间 = " + certinfoFromtime);
            String certinfoEndtime = CertUtils.ParsingCert(signCert,5,"");
//            System.out.println("5证书结束时间 = " + certinfoEndtime);
            String certinfoAlg = CertUtils.ParsingCert(signCert,7,"");
//            System.out.println("7签名算法 = " + certinfoAlg);
//            System.out.println("证书密码 = " + pwfpwd);
            Certinfo certinfo = new Certinfo();
            SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间

            certinfo.setCertinfoId(MD5Hash.UUIDCreate());//插入证书信息id
            certinfo.setCertinfoSn(certsn);//插入证书序列号
            certinfo.setCertinfoDn(certMsg);//证书DN
            certinfo.setCertinfoAlg(certinfoAlg);//证书算法
            certinfo.setCertinfoFromtime(sf.parse(certinfoFromtime));//证书生效日期
            certinfo.setCertinfoEndtime(sf.parse(certinfoEndtime));//证书失效日期
            certinfo.setCertinfoIssuer(certIssuer);//证书颁发者
            certinfo.setCertinfoState(0);//证书状态:0=有效;1=失效
            certinfo.setCertinfoPfx(certinfoPfx);//证书PFX,二进制文件

            certinfo.setCertinfoPwd(pwfpwd);//证书密码
            System.out.println("sf = " + sf);
            certinfo.setCertinfoCer(signCert);//证书CER文件,二进制文件

            return certinfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ZxException(ExceptionEnums.CERT_CREATE_ERROR);
        }
    }
}
