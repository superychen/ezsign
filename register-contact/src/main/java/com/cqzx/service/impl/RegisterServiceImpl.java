package com.cqzx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.util.JavaBeanUtil;
import com.cqzx.comm.util.MD5Hash;
import com.cqzx.comm.util.ValidUtils;
import com.cqzx.domain.Customer;
import com.cqzx.domain.Perosoninfo;
import com.cqzx.feign.CertSealService;
import com.cqzx.feign.DbService;
import com.cqzx.feign.GetService;
import com.cqzx.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

/**
 * @Description: 注册用户逻辑业务层
 * @Author: cqyc
 * @Date: 2019-7-29
 */
@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    private final static Integer PHONE_REGISTER_TYPE=0;
    private final static Integer EMAIL_REGISTER_TYPE=1;
    private final static Integer CUSTOMER_NORMAL_STATE=1;
    private final static String  REGISTER_TABLE_NAME="customer";
    private final static String  REGISTER_PRIMARY_KEY="customer_id";

    @Autowired
    private DbService dbService;

    @Autowired
    private GetService getService;

    @Autowired
    private CertSealService certSealService;



    /**
     * 注册账号，如果成功需要删除redis中的验证码，这里调用的是登录的验证码
     * @param customer 对应的用户账号信息
     * @param registerType 当前注册的类型，0为手机号码注册，1为邮箱注册
     * @param code 验证码
     * @return 是否注册成功
     */
    @Override
    public Customer register(Customer customer, Integer registerType, String code) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //对输入的验证码进行比较
        registerEquals(customer.getCustomerLoginname(),registerType,code);
        //设置用户主键以及密码
        customer.setCustomerId(MD5Hash.UUIDCreate());
        customer.setCustomerPassword(MD5Hash.encodeByMd5(customer.getCustomerLoginname()+customer.getCustomerPassword()));
        customer.setCustomerRegisttime(new Date());
        customer.setCustomerState(CUSTOMER_NORMAL_STATE);
        customer.setCustomerLastlogtime(new Date());

        //转换为map
        Map<String, Object> map = JavaBeanUtil.convertBeanToMap(customer);

        String save = dbService.save(REGISTER_TABLE_NAME, REGISTER_PRIMARY_KEY, map);
        log.debug("保存结果--> {}",save);

        if(registerType == PHONE_REGISTER_TYPE){
            dbService.remove(customer.getCustomerLoginname()+":phoneCode");
        }else {
            dbService.remove(customer.getCustomerLoginname()+":emailCode");
        }

        return customer;
    }

    /**
     * 调用登录的服务发送验证码服务
     * @param registerName
     * @param registerType
     * @return
     */
    @Override
    public String sendCode(String registerName, Integer registerType) {
        return getService.loginWithPhone(registerName,registerType);
    }

    /**
     * 系统默认注册，超级管理员手动注册
     * @param name 姓名
     * @param telephone 手机号
     * @param idcard 身份证号
     * @return true/false
     */
    @Override
    public Boolean adminRegister(String name, String telephone, String idcard) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Customer customer = new Customer();
        Perosoninfo perosoninfo = new Perosoninfo();
        perosoninfo.setPersoninfoId(MD5Hash.UUIDCreate());//生成个人账号id

        customer.setCustomerId(MD5Hash.UUIDCreate());//创建id
        customer.setCustomerLoginname(telephone);//将手机号设置为登录用户账号
        customer.setCustomerState(2);//设置为初始账户,登录时需要修改密码
        customer.setCustomerPersonid(perosoninfo.getPersoninfoId());
        customer.setCustomerRegisttime(new Date());
        //设置初始密码，加密后的6个111111
        customer.setCustomerPassword(MD5Hash.encodeByMd5(telephone+"111111"));
        customer.setCustomerLastlogtime(new Date());//最后登录时间
        Map<String, Object> cusMap = JavaBeanUtil.convertBeanToMap(customer);
        //保存结果
        String save = dbService.save("customer", "customer_id", cusMap);
        log.debug("保存结果-->{}",save);

        JSONObject saveRes = JSONObject.parseObject(save);
        String msg = (String) saveRes.get("data");
        if(StringUtils.equals(msg,"could not execute statement")){
            throw new ZxException(ExceptionEnums.ADMIN_REGISTER_ERROR);
        }

        //生成对应账号的personinfo
        //todo 系统默认创建的账号也要配置资金管理，现在无法得到接口，模拟创建接口
//        perosoninfo.setPersoninfoMealid();
        perosoninfo.setPersoninfoName(name);//姓名
        perosoninfo.setPersoninfoIdcard(idcard);//身份证号
        perosoninfo.setPersoninfoTelephone(telephone);//个人电话
        perosoninfo.setPersoninfoState(0);//状态：0:未认证
        perosoninfo.setPersoninfoAuthtype(1000000);//表示全部没有未认证
        registPerson(perosoninfo);

        Map<String, Object> perMap = JavaBeanUtil.convertBeanToMap(perosoninfo);
        //保存结果
        String perSave = dbService.save("personinfo", "personinfo_id", perMap);
        log.debug("保存结果-->{}",save);

        JSONObject perSaveRes = JSONObject.parseObject(perSave);
        String perMsg = (String) perSaveRes.get("data");
        if(StringUtils.equals(perMsg,"could not execute statement")){
            throw new ZxException(ExceptionEnums.ADMIN_REGISTER_ERROR);
        }

        //保存personinfo后生成证书，1：表示传入的用户类型为个人
        certSealService.generateCertificates(perosoninfo.getPersoninfoId(),"1");
        //生成个人印章, 1：表示传入的用户类型为个人
        certSealService.generateSeal(perosoninfo.getPersoninfoId(),"1");

        return true;
    }


    /**
     * 对输入的验证码进行判断
     * @param loginname
     * @param loginType
     * @param code
     */
    protected void registerEquals(String loginname, Integer loginType, String code){
        //手机注册
        if(loginType == PHONE_REGISTER_TYPE){
            boolean mobile = ValidUtils.isMobile(loginname);
            if(!mobile){
                throw new ZxException(ExceptionEnums.REGISTER_PHONE_REGEX);
            }
            //就进行验证码的判断，判断当前是否有该用户手机的值
            Map<String, Object> phoneCode = dbService.getString(loginname + ":phoneCode");
            String data = (String) phoneCode.get("data");
            log.debug("手机验证码--->{}",data);

            if(StringUtils.isNotBlank(data)){
                boolean isPhoneCode = StringUtils.equals(code, data);
                //如果比较失败，则抛出异常
                if(!isPhoneCode){
                    throw new ZxException(ExceptionEnums.INPUT_CODE_ERROR);
                }
            }else{
                throw new ZxException(ExceptionEnums.REGISTER_SEND_CODE_ERROR);
            }
        }else if(loginType == EMAIL_REGISTER_TYPE){
            //对邮箱验证码进行验证
            boolean isEmail = ValidUtils.checkEmail(loginname);
            if(!isEmail){
                throw new ZxException(ExceptionEnums.REGISTER_EMAIL_REGEX);
            }
            Map<String, Object> emailCCode = dbService.getString(loginname + ":emailCode");
            String data = (String) emailCCode.get("data");
            log.debug("邮箱验证码--->",data);

            if(StringUtils.isNotBlank(data)){
                boolean isEmailCode = StringUtils.equals(code, data);
                if(!isEmailCode){
                    throw new ZxException(ExceptionEnums.INPUT_CODE_ERROR);
                }
            }else {
                throw new ZxException(ExceptionEnums.REGISTER_SEND_CODE_ERROR);
            }
        }else {
            throw new ZxException(ExceptionEnums.INPUT_CODE_ERROR);
        }
    }

    /**
     * 个人信息表注册时插入的参数
     */
    protected Perosoninfo registPerson(Perosoninfo perosoninfo){

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
