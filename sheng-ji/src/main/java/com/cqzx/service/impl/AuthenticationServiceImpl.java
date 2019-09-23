package com.cqzx.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.util.JavaBeanUtil;
import com.cqzx.comm.util.MD5Hash;
import com.cqzx.comm.util.ToJson;
import com.cqzx.comm.util.api.Bank4Api;
import com.cqzx.comm.util.api.FaceApi;
import com.cqzx.domain.CodeEntity;
import com.cqzx.domain.Customer;
import com.cqzx.domain.Perosoninfo;
import com.cqzx.feign.DbService;
import com.cqzx.service.AuthenticationService;
import com.cqzx.service.CertService;
import com.cqzx.service.SealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 个人认证业务服务层
 * @Author: cqyc
 * @Date: 2019-7-31
 */
@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final static String FACE_RESULT="0";//人脸结果正常为两百

    @Autowired
    private FaceApi faceApi;

    @Autowired
    private Bank4Api bank4Api;

    @Autowired
    private DbService dbService;

    @Autowired
    private HttpSession session;

    @Autowired
    private CertService certService;

    @Autowired
    private SealService sealService;

    /**
     * 人脸识别
     * @param bytes 视频文件数组
     * @param idCardname 身份证姓名
     * @param idCardnumber 身份证号码
     * @return 返回认证成功后的基本信息结果，如果是失败会抛出异常
     */
    @Override
    public String faceAuthtication(byte[] bytes,String requestid, String idCardname, String idCardnumber) {
        String result = faceApi.faceAuth(requestid, idCardname, idCardnumber, bytes);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String code = jsonObject.getString("code");
        if(!StringUtils.equals(code,FACE_RESULT)){
            //人脸认证失败
            throw new RuntimeException(jsonObject.getString("msg"));
//            throw new ZxException(ExceptionEnums.FACE_AUTH_ERROR);
        }
        //人脸认证成功
        return jsonObject.toJSONString();
    }

    /**
     * 人脸识别时发送的随机4位验证码
     * 验证人需要读取这个随机数
     * @return json字符串，里面有key和value的值
     */
    @Override
    public String faceAuthCode() {
        String requstId = faceApi.faceRequstId();
        if (StringUtils.isBlank(requstId)) {
            throw new ZxException(ExceptionEnums.FACE_REQUEST_CODE_ERROR);
        }
        return requstId;
    }

    /**
     * 银行卡4要素认证
     * @param bankid 银行卡号
     * @param idnumber 身份证号
     * @param fullname 姓名
     * @param phonenum 电话
     * @return 返回银行卡4要素验证信息
     */
    @Override
    public String bank4Auth(String bankid, String idnumber, String fullname, String phonenum) {
        Map<String, String> map = new HashMap<>();
        map.put("bankid",bankid);
        map.put("idnumber",idnumber);
        map.put("fullname",fullname);
        map.put("phonenum",phonenum);
        String res = bank4Api.bank4Auth(map);

        if(StringUtils.isBlank(res) || StringUtils.equals(res,"{}")){
            throw new ZxException(ExceptionEnums.BANK4_AUTH_ERROR);
        }

        JSONObject jsonObject = JSONObject.parseObject(res);
        log.info("银行卡4要素返回结果---->{}",jsonObject);
        if(!StringUtils.equals(jsonObject.getString("code"),"200") || StringUtils.isBlank(String.valueOf(jsonObject.getString("result")))){
            throw new RuntimeException(jsonObject.getString("msg"));
        }
        Object result = jsonObject.get("result");

        JSONObject jsonObject1 = JSONObject.parseObject(JSON.toJSONString(result));
        Object res1 = jsonObject1.get("result");

        if (StringUtils.equals(String.valueOf(res1),"0")) {
            return jsonObject.toJSONString();
        }else{
            throw new ZxException(ExceptionEnums.BANK4_AUTH_ERROR);
        }
    }

    /**
     * 在注册时后人脸识别通过之后，进行关联个人信息，注册个人信息
     * perosoninfo 里面需要包含的参数 idCardname 身份证姓名，idCardnumber 身份证号码，telephone 手机号码
     * @param customerId 当前用户登录或者注册的customerId
     */
    @Override
    public Boolean registerPerson(Perosoninfo perosoninfo,String customerId) {
        Perosoninfo person = registPerson(perosoninfo);
        //从session里面取出注册的customer的信息，将personId存入到customer表中
        Customer customer = new Customer();
        customer.setCustomerPersonid(person.getPersoninfoId());

        Map<String, Object> map = JavaBeanUtil.convertBeanToMap(person);
        String save = dbService.save("personinfo", "personinfo_id", map);

        log.debug("保存结果--> {}",save);

        //颁发用户证书
        Boolean isCert = certService.createPersonCert(person);
        if(!isCert){
            throw new ZxException(ExceptionEnums.CERT_CREATE_ERROR);
        }
        //生成个人印章
        Boolean isSeal = sealService.createPersonSeal(person);
        if(!isSeal){
            throw new ZxException(ExceptionEnums.SEAL_CREATE_ERROR);
        }

        String sql = "update customer set customer_personid = '"+customer.getCustomerPersonid()+"' where " +
                "customer_id = '"+customerId+"'";
        //修改后的结果
        Map<String, Object> upResult = dbService.executeSql(sql);
        String upData = String.valueOf(upResult.get("data"));

        log.info("修改后的结果--->{}",upResult);
        //比较当前比较的结果,修改结果
        if (StringUtils.equals(upData,"1")) {
            //注册后人脸识别成功后想redis存入一个值，这个值代表着那边二维码弹出框轮询收到,有效时间为5分钟
            dbService.putRedisStringAndTimeOut(customerId+":authFace","true",300);
            return true;
        }
        return false;
    }


    /**
     * 个人信息表注册时插入的参数
     */
    protected Perosoninfo registPerson(Perosoninfo perosoninfo){
        perosoninfo.setPersoninfoId(MD5Hash.UUIDCreate());
        perosoninfo.setPersoninfoState(2);//2为认证通过的标志
        //注册以及人脸完成之后;第一位表示人脸识别;第二位表示手机号认证;第三位表示银行卡四要素;第四位表示身份证;
        perosoninfo.setPersoninfoAuthtype(1001001);
        //todo 调用套餐api，将对应的套餐进行关联，现在无法获得套餐id
//        perosoninfo.setPersoninfoMealid();
        perosoninfo.setPersoninfoConfig1(1);//签约通知,1 短信和邮箱都通知
        perosoninfo.setPersoninfoConfig2(1);//审批通知,1 短信和邮箱都通知
        perosoninfo.setPersoninfoConfig3(1);//抄送通知,1 短信和邮箱都通知
        perosoninfo.setPersoninfoConfig4(1);//审批通过（不通过）通知,1 短信和邮箱都通知
        perosoninfo.setPersoninfoConfig5(1);//签约完成通知,1 短信和邮箱都通知
        perosoninfo.setPersoninfoConfig6(1);//拒签通知,1 短信和邮箱都通知
        perosoninfo.setPersoninfoConfig7(1);//用户提醒,1 短信和邮箱都通知
        perosoninfo.setPersoninfoCheckbox8(1);//自动保存联系人,1 勾选保存
        return perosoninfo;
    }

}
