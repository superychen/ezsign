package com.cqzx.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.util.*;
import com.cqzx.domain.Customer;
import com.cqzx.domain.Memberinfo;
import com.cqzx.domain.Perosoninfo;
import com.cqzx.feign.GetService;
import com.cqzx.service.LoginService;
import com.google.common.base.CaseFormat;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Description: 登录业务逻辑层
 * @Author: cqyc
 * @Date: 2019-7-26
 */
@Service
@Slf4j
//TODO 登录业务，存在的问题：1 安全性
public class LoginServiceImpl implements LoginService {

//    private final static String LOGIN_IMG="kapaImg";
    private final static String CUSTOMER_TABLE="customer";
    private final static String CUSTOMER_PKEY="customer_loginname";
    private final static String MAP_DATA="data";
    private final static String DEGREE = ":kapadegree";
    private final static String INIT_DEGREEE = "1";
    private final static String SMS_PARAM = "var1";
    private final static Integer PHONE_LOGIN_TYPE=0;
    private final static Integer EAMIL_LOGIN_TYPE=1;

    @Autowired
    private GetService getService;

    @Autowired
    private SendSMS sendSMS;

    @Autowired
    private HttpSession session;

    /**
     * 账号密码登录，如果失败3次，则需要判断用户输入的图形验证码
     * @param loginname
     * @param password
     * @param imgcode
     * @return
     */

    @Override
    public Customer loginWithPassword(String loginname, String password, String imgcode) throws UnsupportedEncodingException, NoSuchAlgorithmException {


        Map<String, Object> isTwice = getService.getString(loginname + DEGREE);
        log.debug("isTwice --->{}",isTwice);

        String data = (String) isTwice.get("data");
        log.debug("data ---> {}",data);
        //先查询redis里面是否有失败的次数，如果有如果大于3次就必须输入验证码，如果没有则继续
        if(data != null){
            Integer twice = Integer.parseInt(data);

            //如果当前的次数是否大于三次
            if(twice >= 3){
                //如果redis中没有对应账号的登录验证码记录，抛出异常
                if(StringUtils.isBlank(data)){
                    throw new ZxException(ExceptionEnums.LOGIN_IMG_CODE);
                }
                //调用redis服务获取当前登录用户的图片验证码
//                Map<String, Object> reImgCode = getService.getString(loginname + LOGIN_IMG);
                String reImgCode = (String) session.getAttribute("imgcode");
                log.debug("从session中获取的图片验证码-->{}",reImgCode);

                if (!StringUtils.equals(reImgCode,imgcode)) {
                    throw new ZxException(ExceptionEnums.LOGIN_IMG_CODE);
                }else{
                    //输入成功则删除redis中的验证码和对应的失败次数
//                    getService.remove(loginname+LOGIN_IMG);
                    getService.remove(loginname + DEGREE);
                }
            }
        }
        //调用远程服务查询customer数据返回回来的map
        Map<String, Object> map = getService.get(CUSTOMER_TABLE, CUSTOMER_PKEY, loginname);
        System.out.println("--->" + map);
        //将从数据库服务的查询出来的数据转换为json字符串
        String yc = ToJson.stringToJson(String.valueOf(map.get(MAP_DATA)));
        log.debug("yc ----> {}",yc);
        Customer customer = JSONObject.parseObject(yc, Customer.class);

        if(customer.getCustomerLoginname() == null){
            throw new ZxException(ExceptionEnums.LOGIN_USERNAME_PASSWORD_ERROR);
        }
        //将密码全部转为小写
        String pasLower = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,MD5Hash.encodeByMd5(customer.getCustomerLoginname()+password));
        //判断用户输入的密码是否跟数据库中的密码相同
        boolean b = StringUtils.equals( pasLower , customer.getCustomerPassword());
//        log.debug("两次校验的结果-->{} ---> {}",b,customer.getCustomerPassword());
        if(!b){
            if(data != null){
                //在redis中记录用户输入失败的次数
                Integer twice = Integer.parseInt(data);
                getService.putRedisStringAndTimeOut(loginname+DEGREE,String.valueOf(twice+1),100);
                throw new ZxException(ExceptionEnums.LOGIN_USERNAME_PASSWORD_ERROR);
            }else{
                getService.putRedisStringAndTimeOut(loginname+DEGREE,INIT_DEGREEE,100);
                throw new ZxException(ExceptionEnums.LOGIN_USERNAME_PASSWORD_ERROR);
            }
        }
        //如果customer的类型为初始账户，则表示没有账号被查询到为初始账户，抛出异常，提示用户先修改密码
        if(customer.getCustomerState() == 2){
            throw new ZxException(ExceptionEnums.LOGIN_CODE_INIT);
        }
        return customer;
    }

    /**
     * 将生成的验证码存入到redis中，如果登录失败超过3次，则会出现图形验证码
     * @param str
     * @param loginname
     */
    @Override
    public void kapatchaImg(String str, String loginname) {
        //向redis中存储登录名+自定义形成唯一的key，然后在插入验证码
//        getService.putRedisString(loginname+LOGIN_IMG,str);
    }


    /**
     * 用户手机号或者邮箱登录验证手机校验码
     * @param loginname 登录用户的账号（手机号或邮箱）
     * @param loginType 登录的类型  0表示手机登陆，1表示邮箱登录
     * @param code 用户输入的验证码
     * @return
     */
    @Override
    public Customer loginWithCode(String loginname, Integer loginType, String code) {
        //对用户输入的验证码进行判断
        loginEquals(loginname,loginType,code);
        //todo 后期可以考虑将这一段踢出去写一个方法
        //调用远程服务查询customer数据返回回来的map
        Map<String, Object> map = getService.get(CUSTOMER_TABLE, CUSTOMER_PKEY, loginname);
        log.debug("--->{}",map);
        //将从数据库服务的查询出来的数据转换为json字符串
        String yc = ToJson.stringToJson(String.valueOf(map.get(MAP_DATA)));
        Customer customer = JSONObject.parseObject(yc, Customer.class);
        //如果customer的类型为初始账户，则表示没有账号被查询到为初始账户，抛出异常，提示用户先修改密码
        if(customer.getCustomerState() == 2){
            throw new ZxException(ExceptionEnums.LOGIN_CODE_INIT);
        }
        //如果customer为空，则表示没有账号被查询到
        if(StringUtils.isBlank(customer.getCustomerLoginname())){
            throw new ZxException(ExceptionEnums.LOGIN_USERNAME_PASSWORD_ERROR);
        }
        Map<String, Object> map1 = JavaBeanUtil.convertBeanToMap(customer);
        session.setAttribute("customer",map1);
        return customer;
    }

    /**
     * 发送手机短信码
     * @param loginname 用户输入的手机用户名
     * @return 当前用户的信息
     */
    @Override
    public String sendWithPhone(String loginname) {
        Map<String, Object> map = new HashMap<>();
        //随机生成一个随机4位数
        String ranCode;
        long round = Math.round(Math.random() * 10000);
        if(round < 1000){
            ranCode = "0" + String.valueOf(round);
        }else{
            ranCode = String.valueOf(round);
        }
        map.put(SMS_PARAM,ranCode);
        boolean isMobile = ValidUtils.isMobile(loginname);

        //判断短信是否符合格式
        if(isMobile){
            Map<String, Object> phoneCode = getService.getString(loginname + ":phoneCode");
            String data = (String) phoneCode.get("data");

            //将发送的短信写入redis，300秒后过期
            if(StringUtils.isBlank(data)){
                //发送短信
                String res = sendSMS.sendSmsWithcode(map, loginname);
                getService.putRedisStringAndTimeOut(loginname+":phoneCode",ranCode,300);
                return res;
            }else{
                throw new ZxException(ExceptionEnums.LOGIN_READY_PHONE_CODE);
            }
        }else {
            throw new ZxException(ExceptionEnums.LOGIN_PHONE_REGEX);
        }
    }

    /**
     * 发送邮箱
     * @param loginname
     * @return
     */
    @Override
    public String sendWithEamil(String loginname) {

        boolean isEmail = ValidUtils.checkEmail(loginname);

        //判断邮箱是否符合格式
        if(isEmail){
            //将发送的邮箱验证码写入redis，300秒后过期
            Map<String, Object> emailCode = getService.getString(loginname + ":emailCode");
            log.debug("获取服务的中map结果-->{}",emailCode);
            String data = (String) emailCode.get("data");
            log.debug("redis中获取验证码 data--->",data);
            if(StringUtils.isBlank(data)){
                //发送邮箱
                String res = sendSMS.sendEmailWithcode(loginname);
                JSONObject jsonObject = JSONObject.parseObject(res);
                log.debug("邮箱验证码返回的结果--->{}",res);
                getService.putRedisStringAndTimeOut(loginname+":emailCode", String.valueOf(jsonObject.get("emailcode")),300);
                return res;
            }else{
                throw new ZxException(ExceptionEnums.LOGIN_READY_EMAIL_CODE);
            }
        }else{
            throw new ZxException(ExceptionEnums.LOGIN_EMAIL_REGEX);
        }
    }

    /**
     * 登录之后选择个人签约、或者企业签约
     * @param customer session获取出来的customer信息
     * @param userType 用户在登录之后选择的类型 1 表示进入个人签约，2 表示进入企业签约
     * @return
     */
    @Override
    public Boolean changeUserType(Customer customer,Integer userType) {
        //类型为1，则进入个人签约
        if(userType == 1){
            customer.setUserType(1);//设置当前登录账号的用户类型为1:个人签约
            //判断当前的登录账号是否有对应的个人信息
            Map<String, Object> perRes = getService.get("personinfo", "personinfo_id", customer.getCustomerPersonid());
            if(StringUtils.equals(String.valueOf(perRes.get("data")),"{}")){
                throw new ZxException(ExceptionEnums.USER_SHOULD_AUTH);
            }
            //将从数据库服务的查询出来的数据转换为json字符串
            String yc = ToJson.stringToJson(String.valueOf(perRes.get(MAP_DATA)));
            Perosoninfo personinfo = JSONObject.parseObject(yc, Perosoninfo.class);

            session.invalidate();//先清除session
//            Map<String, Object> map = JavaBeanUtil.convertBeanToMap(customer);
            Map<String, Object> map1 = MapUtile.beanToMap(customer);
            session.setAttribute("customer",map1);
            session.setAttribute("userId",personinfo.getPersoninfoId());//将当前的用户个人信息的id存入到session里面去
            return true;
        }else if (userType == 2){//类型为1，则进入企业签约
            customer.setUserType(2);//设置当前登录账号的类型为2:企业签约
            //判断当前memberinfo是否有对应登录账号的存在
            Map<String, Object> comRes = getService.get("memberinfo", "memberinfo_customerid", customer.getCustomerId());
            log.debug("comRes--->{}",comRes);
            if(StringUtils.equals(String.valueOf(comRes.get("data")),"{}")){
                throw new ZxException(ExceptionEnums.MEMBER_SHOULD_AUTH);
            }
            String yc = ToJson.stringToJson(String.valueOf(comRes.get(MAP_DATA)));
            Memberinfo memberinfo = JSONObject.parseObject(yc, Memberinfo.class);

            session.invalidate();//先清除session
//            Map<String, Object> map = JavaBeanUtil.convertBeanToMap(customer);
            Map<String, Object> map1 = MapUtile.beanToMap(customer);
            session.setAttribute("customer",map1);
            session.setAttribute("userId",memberinfo.getMemberinfoId());//将进入企业签约对应的memberId注入到session中去
            String sql="select memberinfo_id,memberinfo_name from memberinfo where memberinfo_projectid = '"+memberinfo.getMemberinfoProjectid()+"' and memberinfo_type = '0' ";
            Map<String,Object> map = new HashMap<>();
            Map<String, Object> memMap = getService.findBySQL(sql,map);
            List<Map<String,Object>> dataList = (List<Map<String, Object>>) memMap.get("data");
            if(dataList.size() <= 0){
                throw new ZxException(ExceptionEnums.ADMIN_MEMBER_INFO_NOE_FOUND);
            }
            Map<String, Object> memberMap = dataList.get(0);
            session.setAttribute("adminMan",memberMap);
            return true;
        }else {//如果为其他值则全部抛出异常
            throw new ZxException(ExceptionEnums.SESSION_USER_ERROR);
        }
    }

    /**
     * 发送邮箱短地址
     */
    @Override
    public String sendEmailAddress(String email,String rename,String business,String times) {
        return sendSMS.sendEmailTimes(email,rename,business,times);
    }


    /**
     * 对输入的验证码进行判断
     * @param loginname
     * @param loginType
     * @param code
     */
    protected void loginEquals(String loginname, Integer loginType, String code){
        //手机登陆
        if(loginType == PHONE_LOGIN_TYPE){
            //就进行验证码的判断，判断当前是否有该用户手机的值
            boolean mobile = ValidUtils.isMobile(loginname);
            if(!mobile){
                throw new ZxException(ExceptionEnums.LOGIN_PHONE_REGEX);
            }
            Map<String, Object> phoneCode = getService.getString(loginname + ":phoneCode");
            String phoneData = (String) phoneCode.get("data");

            //判断手机验证码是否存在
            if(StringUtils.isNotBlank(phoneData)){
                boolean isPhoneCode = StringUtils.equals(code, phoneData);
                //如果比较失败，则抛出异常
                if(!isPhoneCode){
                    throw new ZxException(ExceptionEnums.INPUT_ERROR_CODE);
                }
            }else{
                throw new ZxException(ExceptionEnums.LOGIN_PHONE_SEND_PHONE);
            }
        }else if(loginType == EAMIL_LOGIN_TYPE){
            //对邮箱验证码进行验证
            boolean isEmail = ValidUtils.checkEmail(loginname);
            if(!isEmail){
                throw new ZxException(ExceptionEnums.LOGIN_EMAIL_REGEX);
            }
            //调用服务查询是否含有邮件验证码
            Map<String, Object> emailCode = getService.getString(loginname + ":emailCode");
            String data = (String) emailCode.get("data");
            if(StringUtils.isNotBlank(data)){
                boolean isEmailCode = StringUtils.equals(code, data);
                if(!isEmailCode){
                    throw new ZxException(ExceptionEnums.INPUT_ERROR_CODE);
                }
            }else {
                throw new ZxException(ExceptionEnums.LOGIN_EMAIL_SEND_PHONE);
            }
        }else {
            throw new ZxException(ExceptionEnums.INPUT_ERROR_CODE);
        }
    }
}
