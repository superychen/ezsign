package com.cqzx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.util.JavaBeanUtil;
import com.cqzx.comm.util.ToJson;
import com.cqzx.comm.util.ValidUtils;
import com.cqzx.comm.util.cert.CreateCert;
import com.cqzx.domain.*;
import com.cqzx.feign.DbService;
import com.cqzx.service.CertService;
import com.cqzx.service.SealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description: 证书以及印章业务处理层
 * @Author: cqyc
 * @Date: 2019-8-8
 */
@Service
@Slf4j
public class CertServiceImpl implements CertService {

    @Autowired
    private CreateCert createCert;

    @Autowired
    private DbService dbService;

    @Autowired
    private HttpSession session;

    @Autowired
    private SealService sealService;

    /**
     * 生成个人证书
     * @param perosoninfo personinfo的实体
     * @return 返回是否成功
     */
    @Override
    public Boolean createPersonCert(Perosoninfo perosoninfo) {
//        Map<String, Object> person = dbService.get("personinfo", "personinfo_id", personinfoId);
//        Perosoninfo perosoninfo = JSONObject.parseObject(ToJson.stringToJson(String.valueOf(person.get("data"))),Perosoninfo.class);
//        log.debug("转换出来的person--->{}",perosoninfo);
        //将获取出来的个人信息判断是否有想为空
        String certMsg = "";
        if(StringUtils.isBlank(perosoninfo.getPersoninfoEmail())){
            certMsg =  "CN="+perosoninfo.getPersoninfoName()+"-"+System.currentTimeMillis()+",SN="+perosoninfo.getPersoninfoTelephone()+"," +
                       "OU="+perosoninfo.getPersoninfoIdcard()+",L=重庆,C=CN";
        }else {
            certMsg  = "E="+perosoninfo.getPersoninfoEmail()+"-"+System.currentTimeMillis()+"," +
                       "CN="+perosoninfo.getPersoninfoName()+",SN="+perosoninfo.getPersoninfoTelephone()+"," +
                       "OU="+perosoninfo.getPersoninfoIdcard()+",L=重庆,C=CN";
        }
        Certinfo cert = createCert.createCert(certMsg);
        cert.setCertinfoType(1);//证书类型:1=个人，2=企业
        cert.setCertinfoUserid(perosoninfo.getPersoninfoId());//使用方ID:证书类型=1时为person_info_id

        return insertCert(cert);
    }

    /**
     * 创建企业证书
     * @param companyinfo 企业信息
     * @return
     */
    @Override
    public Boolean createCompanyCert(Companyinfo companyinfo) {
        String certMsg = "";
        Map<String,Object> customerMap = (Map<String, Object>) session.getAttribute("customer");
        String jsonObject = new JSONObject(customerMap).toJSONString();

        Customer customer = JSONObject.parseObject(jsonObject, Customer.class);
        if (customer == null) {
            throw new ZxException(ExceptionEnums.CUSTOMER_SESSION_ERROR);
        }
        boolean mobile = ValidUtils.isMobile(customer.getCustomerLoginname());
        if(mobile){//如果当前登录账号为手机登录
            certMsg = "SN="+customer.getCustomerLoginname()+"-"+System.currentTimeMillis()+"," +
                      "CN="+companyinfo.getCompanyinfoName()+",OU="+companyinfo.getCompanyinfoCreditcode()+"," +
                      "O="+companyinfo.getCompanyinfoName()+",L=重庆,ST=重庆,C=CN";
        }else{//当前登录账号为邮箱登录
            certMsg = "E="+customer.getCustomerLoginname()+"-"+System.currentTimeMillis()+"," +
                    "CN="+companyinfo.getCompanyinfoName()+",OU="+companyinfo.getCompanyinfoCreditcode()+"," +
                    "O="+companyinfo.getCompanyinfoName()+",L=重庆,ST=重庆,C=CN";
        }
        Certinfo cert = createCert.createCert(certMsg);
        cert.setCertinfoType(2);//证书类型:1=个人，2=企业
        cert.setCertinfoUserid(companyinfo.getCompanyinfoId());//使用方ID:证书类型=2时为CompanyinfoId

       return insertCert(cert);
    }

    /**
     * 提供签约的接口，生成证书
     * @param userid 根据用户的类型来判断传入的是personinfoId还是companyinfoId
     * @param userType 用户的类型 1:个人签约类型，2：企业签约类型
     * @return 返回状态码
     */
    @Override
    public CodeEntity generateCertificates(String userid, String userType) {
        //如果当前用户类型为个人签约类型
        if(StringUtils.equals(userType,"1")){
            //判断当前的登录账号是否有对应的个人信息
            Map<String, Object> perRes = dbService.get("personinfo", "personinfo_id", userid);
            if(StringUtils.equals(String.valueOf(perRes.get("data")),"{}")){
                throw new ZxException(ExceptionEnums.CERT_CREATE_ERROR);
            }
            String perString = ToJson.stringToJson(String.valueOf(perRes.get("data")));
            Perosoninfo personinfo = JSONObject.parseObject(perString, Perosoninfo.class);

            log.debug("personinfo--->{}",personinfo);

            //创建个人证书
            createPersonCert(personinfo);
        }else if(StringUtils.equals(userType,"2")){//如果用户类型为2，则对应的是企业签约类型，生成企业证书
            Map<String, Object> comRes = dbService.get("companyinfo", "companyinfo_id", userid);
            if(StringUtils.equals(String.valueOf(comRes.get("data")),"{}")){
                throw new ZxException(ExceptionEnums.CERT_CREATE_ERROR);
            }
            String comString = ToJson.stringToJson(String.valueOf(comRes.get("data")));
            Companyinfo companyinfo = JSONObject.parseObject(comString, Companyinfo.class);
            String res = String.valueOf(new Random().nextInt(6));
            log.debug("companyinfo--->{}",companyinfo);
            createCompanyQianCert(companyinfo,res);
        }
        return new CodeEntity(200,"ok");
    }

    /**
     * 提供生成印章的接口（个人/企业）
     * @param userid 传入的personinfoid或者companyinfoid
     * @param userType 表示用户的类型 1：个人印章类型，2：企业印章类型
     * @return 返回状态码
     */
    @Override
    public CodeEntity generateSeal(String userid, String userType) {
        //如果当前用户类型为个人印章类型
        if(StringUtils.equals(userType,"1")){
            Map<String, Object> perMap = dbService.get("personinfo", "personinfo_id", userid);
            if(StringUtils.equals(String.valueOf(perMap.get("data")),"{}")){
                throw new ZxException(ExceptionEnums.SEAL_CREATE_ERROR);
            }
            String perString = ToJson.stringToJson(String.valueOf(perMap.get("data")));
            Perosoninfo personinfo = JSONObject.parseObject(perString, Perosoninfo.class);

            log.debug("personinfo--->{}",personinfo);

            //创建个人证书
            sealService.createPersonSeal(personinfo);
        }else if(StringUtils.equals(userType,"2")){//如果当前用户类型为企业印章类型
            Map<String, Object> comMap = dbService.get("companyinfo", "companyinfo_id", userid);
            if(StringUtils.equals(String.valueOf(comMap.get("data")),"{}")){
                throw new ZxException(ExceptionEnums.SEAL_CREATE_ERROR);
            }
            String comString = ToJson.stringToJson(String.valueOf(comMap.get("data")));
            Companyinfo companyinfo = JSONObject.parseObject(comString, Companyinfo.class);
            log.debug("companyinfo--->{}",companyinfo);


            //创建企业印章
            sealService.createCompanySeal(companyinfo);
        }else {
            throw new ZxException(ExceptionEnums.SEAL_CREATE_ERROR);
        }
        return new CodeEntity(200,"ok");
    }



    @Override
    public CodeEntity generateCertOrg(String customerLoginname, String companyinfoName, String creditcode,String companyId) {
        String certMsg = "";

        boolean mobile = ValidUtils.isMobile(customerLoginname);
        if(mobile){//如果当前登录账号为手机登录
            certMsg = "SN="+customerLoginname+"," +
                    "CN="+companyinfoName+",OU="+creditcode+"," +
                    "O="+companyinfoName+",L=重庆,ST=重庆,C=CN";
        }else{//当前登录账号为邮箱登录
            certMsg = "E="+customerLoginname+"," +
                    "CN="+companyinfoName+",OU="+creditcode+"," +
                    "O="+companyinfoName+",L=重庆,ST=重庆,C=CN";
        }
        Certinfo cert = createCert.createCert(certMsg);
        cert.setCertinfoType(2);//证书类型:1=个人，2=企业
        cert.setCertinfoUserid(companyId);//使用方ID:证书类型=2时为CompanyinfoId
        insertCert(cert);//生成证书
        return new CodeEntity(200,"ok");
    }

    /**
     * 为阻止架构创建子公司印章
     * @param customerId
     * @param companyinfoName
     * @param creditcode
     * @param companyId
     * @return
     */
    @Override
    public CodeEntity generateSealOrg(String customerId, String companyinfoName, String creditcode, String companyId) {
        //创建企业印章
        return sealService.createCompanySealOrg(customerId,companyinfoName,creditcode,companyId);
    }


    /**
     * 将个人或企业证书的信息保存到cert表中
     * @param cert
     * @return
     */
    protected Boolean insertCert(Certinfo cert){
        //将证书信息保存到数据库中
        Map<String, Object> map = JavaBeanUtil.convertBeanToMap(cert);

        String save = dbService.save("certinfo", "certinfo_id", map);
        log.debug("创建证书保存结果-->{}",save);

        JSONObject saveRes = JSONObject.parseObject(save);
        String msg = (String) saveRes.get("data");
        if(StringUtils.equals(msg,"could not execute statement")){
            throw new ZxException(ExceptionEnums.CERT_CREATE_ERROR);
        }
        return true;
    }


      /**
     * 创建企业证书
     * @param companyinfo 企业信息
     * @return
     */
    protected Boolean createCompanyQianCert(Companyinfo companyinfo,String loginname) {
        String certMsg = "";
            certMsg = "SN="+loginname+"," +
                    "CN="+companyinfo.getCompanyinfoName()+",OU="+companyinfo.getCompanyinfoCreditcode()+"," +
                    "O="+companyinfo.getCompanyinfoName()+",L=重庆,ST=重庆,C=CN";
        Certinfo cert = createCert.createCert(certMsg);
        cert.setCertinfoType(2);//证书类型:1=个人，2=企业
        cert.setCertinfoUserid(companyinfo.getCompanyinfoId());//使用方ID:证书类型=2时为CompanyinfoId

        return insertCert(cert);
    }
}
