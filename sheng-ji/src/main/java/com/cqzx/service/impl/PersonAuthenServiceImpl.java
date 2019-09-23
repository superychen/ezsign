package com.cqzx.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.util.JavaBeanUtil;
import com.cqzx.comm.util.MD5Hash;
import com.cqzx.comm.util.ToJson;
import com.cqzx.comm.util.ValidUtils;
import com.cqzx.domain.Customer;
import com.cqzx.domain.Perosoninfo;
import com.cqzx.feign.DbService;
import com.cqzx.service.AuthenticationService;
import com.cqzx.service.CertService;
import com.cqzx.service.PersonAuthenService;
import com.cqzx.service.SealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 个人登录后认证业务逻辑层
 * @Author: cqyc
 * @Date: 2019-8-12
 */
@Service
@Slf4j
public class PersonAuthenServiceImpl implements PersonAuthenService {

    @Autowired
    private DbService dbService;

    @Autowired
    private HttpSession session;

    @Autowired
    private CertService certService;

    @Autowired
    private SealService sealService;

    @Autowired
    private AuthenticationService authenticationService;
    /**
     * 在登录之后如果用户没有在注册的时候人脸识别，在登录之后进行的人脸识别通过后执行这个请求
     * @param  idCardname 身份证姓名，idCardnumber 身份证号码，telephone 手机号码
     * @return true/false
     */
    @Override
    public Boolean updateAfterFace(String customerId, String idCardname, String idCardnumber, String telephone) {
        //先判断当前的personinfo是否已经认证过了的,就不能在认证
        Map<String, Object> personMap = dbService.get("personinfo", "personinfo_idcard", idCardnumber);

        //将获取出来的数据判断是否为空，为空就保存当前账号到personinfo中去
        log.info("人脸识别取出的数据是否为空-->{}",String.valueOf(personMap.get("data")));
        if(StringUtils.equals(String.valueOf(personMap.get("data")),"{}")){
            Perosoninfo person = new Perosoninfo();
            person.setPersoninfoName(idCardname);
            person.setPersoninfoIdcard(idCardnumber);
            if(ValidUtils.isMobile(telephone)){
                person.setPersoninfoTelephone(telephone);//可有可无
            }else {
                person.setPersoninfoEmail(telephone);
            }
            return authenticationService.registerPerson(person,customerId);
        }else{
            //将查询到的结果转换为json字符串
            String data = ToJson.stringToJson(String.valueOf(personMap.get("data")));
            log.debug("data ----> {}",data);
            Perosoninfo peroson = JSONObject.parseObject(data, Perosoninfo.class);
            //将六位二进制的数转换为string，然后一一对比
            char[] pers = String.valueOf(peroson.getPersoninfoAuthtype()).toCharArray();

            //如果第一位为最后一位为1的话，表示人脸识别已经认证过的了
            if(pers[0] == '1' && pers[6] == '1'){
                dbService.putRedisStringAndTimeOut(customerId+":authFace","false",300);
                throw new ZxException(ExceptionEnums.FACE_AUTH_HAS_BEAN);
            }else{
                //修改personinfo表中的用户身份认证方式
                for (int i = 0; i < pers.length; i++) {
                    pers[0] = '1';
                    pers[1] = '0';
                    pers[3] = '1';
                    pers[6] = '1';
                    System.out.println("pers[i] = " + pers[i]);
                }
                //向redis存取一个值
                dbService.putRedisStringAndTimeOut(customerId+":authFace","true",300);
                //修改认证的方式
                return updateAuthenType(pers,peroson);
                //只要认证对应的六位二进制有一个为已经认证过的（1），就不在创建证书以及印章
                //todo 只要personinfo有值，就不创建证书以及印章
               /* boolean contains = StringUtils.substring(String.valueOf(peroson.getPersoninfoAuthtype()),1,7).contains("1");
                if(contains){

                }else{
                    //创建证书以及印章
                    createSealAndCert(peroson);
                    //修改认证的方式
                    return updateAuthenType(pers,peroson);
                }*/
            }
        }
    }

    /**
     * 脸识别错误后下向redis存入一个false,证明该人脸识别认证失败
     */
    @Override
    public void faceAuthError(String customerId) {
        dbService.putRedisStringAndTimeOut(customerId+":authFace","false",30);
    }

    /**
     *人脸识别成功后向redis注入一个值,证明人脸识别认证成功
     */
    @Override
    public void faceAuthTypeTwo(String customerId) {
        dbService.putRedisStringAndTimeOut(customerId+":authFace","true",30);
    }

    /**
     * 银行卡4要素认证成功后需要进行的操作
     * @param bankid 银行卡号
     * @param idnumber 身份证号
     * @param fullname 姓名
     * @param phonenum 电话
     * @param code 银行卡预留电话验证码
     * @return
     */
    @Override
    public Boolean updateAfterBank(String bankid, String idnumber, String fullname, String phonenum,String code) {
        //判断手机验证码是否正确
        Map<String, Object> phoneCode = dbService.getString(phonenum + ":phoneCode");

        String phoneData = (String) phoneCode.get("data");
        if (!StringUtils.equals(code,phoneData)) {
            throw new ZxException(ExceptionEnums.BANK4_CODE_ERROR);
        }
        //银行卡4要素认证
        String bank4Auth = authenticationService.bank4Auth(bankid, idnumber, fullname, phonenum);
        log.info("银行卡四要素验证结果：{}",bank4Auth);
        Perosoninfo perosoninfo = new Perosoninfo();

        //先判断当前的personinfo是否已经认证过了的,就不能在认证
        Map<String, Object> personMap = dbService.get("personinfo", "personinfo_idcard", idnumber);

        //将获取出来的数据判断是否为空，为空就保存当前账号到personinfo中去
        if(StringUtils.equals(String.valueOf(personMap.get("data")),"{}")){
            perosoninfo.setPersoninfoName(fullname);//姓名,
            perosoninfo.setPersoninfoIdcard(idnumber);//身份证号码,
            perosoninfo.setPersoninfoTelephone(phonenum);//用户联系人电话,
            perosoninfo.setPersoninfoAuthprocessid(bankid);//个人银行卡四要素认证流水id,
            //将一些配置自定义
            perosoninfo = registPerson(perosoninfo);
            //注册以及人脸完成之后;第一位表示人脸识别;第二位表示手机号认证;第三位表示银行卡四要素;第四位表示身份证;
            perosoninfo.setPersoninfoAuthtype(1011000);//如果二进制数还有第七位，说明没有被人脸识别
            Boolean hasPerson = isHasPerson(perosoninfo);
            if(!hasPerson){
                throw new ZxException(ExceptionEnums.AUTH_PASS_ERROR);
            }
            //创建证书以及印章
            createSealAndCert(perosoninfo);
        }else{
            log.debug("map---》{}",personMap.get("data"));
            //将查询到的结果转换为json字符串
            String data = ToJson.stringToJson(String.valueOf(personMap.get("data")));
            log.debug("data ----> {}",data);
            Perosoninfo peroson = JSONObject.parseObject(data, Perosoninfo.class);
            //将六位二进制的数转换为string，然后一一对比
            char[] pers = String.valueOf(peroson.getPersoninfoAuthtype()).toCharArray();

            //如果第三位为1的话，表示银行卡4要素已经认证过，无需继续认证
            if(pers[2] == '1'){
                throw new ZxException(ExceptionEnums.BANK4_AUTH_HAS_BEAN);
            }else{
                //修改personinfo表中的用户身份认证方式
                for (int i = 0; i < pers.length; i++) {
                    pers[0] = '1';
                    pers[1] = '0';//手机
                    pers[2] = '1';//银行卡4要素
                    pers[3] = '1';//身份证
                    System.out.println("pers[i] = " + pers[i]);
                }
                //修改认证的方式
                return updateAuthenType(pers,peroson);
                //todo 只要personinfo有值，就不创建证书以及印章
                //只要认证对应的六位二进制有一个为已经认证过的（1），就不在创建证书以及印章
                /*boolean contains = StringUtils.substring(String.valueOf(peroson.getPersoninfoAuthtype()),1,7).contains("1");
                if(contains){
                }else{
                    //创建证书以及印章
//                    createSealAndCert(peroson);
                    //修改认证的方式
                    return updateAuthenType(pers,peroson);
                }*/
            }
        }
        return null;
    }

    /**
     * 登录之后的手机号认证，手机号认证必须在其他认证做了之后才能进行操作
     * @param personinfoName 个人姓名
     * @param personinfoTelephone 个人手机号码
     * @param imgCode 图片验证码
     * @param code 手机号的短信验证码
     * @return true/false
     */
    @Override
    public Boolean authAfterPhone(String personinfoName, String personinfoTelephone, String imgCode, String code) {
        //校验图片验证码,从session里面取出图形验证码
        String codeImg = (String) session.getAttribute("imgcode");
        log.debug("从session里面取出来的图形验证码：{}",codeImg);
        if (!StringUtils.equals(imgCode,codeImg)) {
            throw new ZxException(ExceptionEnums.IMG_CODE_ERROR);
        }
        //校验短信验证码
        Map<String, Object> smsCode = dbService.getString(personinfoTelephone + ":phoneCode");
        String codeSms = (String) smsCode.get("data");
        if (!StringUtils.equals(codeSms,code)) {
            throw new ZxException(ExceptionEnums.SMS_CODE_ERROR);
        }

        String sql = "select * from personinfo where personinfo_name='"+personinfoName+"' and "+
                     "personinfo_telephone = '"+personinfoTelephone+"'";

        HashMap<String, Object> map = new HashMap<>();
        Map<String, Object> selectRes = dbService.findBySQL(sql, map);
        //将查询出来的数据
        List<Map<String,Object>> data = (List<Map<String, Object>>) selectRes.get("data");
        if(CollectionUtils.isEmpty(data)){
            throw new ZxException(ExceptionEnums.PHONE_USER_AUTH_NULL);
        }
        Map<String, Object> selRes = data.get(0);//得到首个的值
        String jsonString = JSON.toJSONString(selRes);//将map转换为string
        Perosoninfo perosoninfo = JSONObject.parseObject(jsonString, Perosoninfo.class);//转换为实体类

        //得到查询的认证的bytes
        char[] chars = String.valueOf(perosoninfo.getPersoninfoAuthtype()).toCharArray();
        //如果手机号已经被认证过了,则不进行下一步操作
        if(chars[1] == '1'){
            throw new ZxException(ExceptionEnums.PHONE_AUTH_HAS_BEAN);
        }
        chars[1] = '1';
        perosoninfo.setPersoninfoAuthtype(Integer.parseInt(String.valueOf(chars)));

        //修改手机号以及手机号认证
        String updateSql="update personinfo set personinfo_telephone='"+personinfoTelephone+"',personinfo_authtype='"+
                perosoninfo.getPersoninfoAuthtype()+"' "+
                " where personinfo_id = '"+perosoninfo.getPersoninfoId()+"'";

        //执行返回结果
        Map<String, Object> upRes = dbService.executeSql(updateSql);
        String updateRes = String.valueOf(upRes.get("data"));
        //如果修改成功，则认证成功
        if (StringUtils.equals(updateRes,"1")) {
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


    /**
     * 这个方法时认证后判断当前登录的账号是否在注册的时候做过人脸识别认证
     * 做过人脸识别认证则这个方法不用管，没有做过需要在个人表中重新写入一条消息
     * 然后与账号表进行关联
     */
    protected Boolean isHasPerson(Perosoninfo perosoninfo){
        //从session里面取出登录的customer的信息，将personId存入到customer表中
        Map<String,Object> cusMap = (Map<String, Object>) session.getAttribute("customer");
        String jsonObject = new JSONObject(cusMap).toJSONString();
        Customer customer = JSONObject.parseObject(jsonObject, Customer.class);
        //
        customer.setCustomerPersonid(perosoninfo.getPersoninfoId());
        if(customer == null){
            throw new ZxException(ExceptionEnums.SESSION_GET_ERROR);
        }
        Map<String, Object> map = JavaBeanUtil.convertBeanToMap(perosoninfo);
        String save = dbService.save("personinfo", "personinfo_id", map);

        log.debug("保存结果--> {}",save);

        String sql = "update customer set customer_personid = '"+customer.getCustomerPersonid()+"' where " +
                "customer_id = '"+customer.getCustomerId()+"'";
        //修改后的结果
        Map<String, Object> upResult = dbService.executeSql(sql);
        String upData = String.valueOf(upResult.get("data"));
        log.info("修改后的结果--->{}",upResult);
        //现在再将session重写进去,这样的话再次访问userType页面的时候取出来的session就会被认证成功
        customer.setCustomerPersonid(perosoninfo.getPersoninfoId());
        Map<String, Object> reCusMap = JavaBeanUtil.convertBeanToMap(customer);
        session.setAttribute("customer",reCusMap);

        //比较当前比较的结果,修改结果
        if (StringUtils.equals(upData,"1")) {
            return true;
        }else {
            return false;
        }
    }


    /**
     * 不管通过任何认证，生成个人证书以及印章
     */
    protected void createSealAndCert(Perosoninfo perosoninfo){
        //颁发用户证书
        Boolean isCert = certService.createPersonCert(perosoninfo);
        if(!isCert){
            throw new ZxException(ExceptionEnums.CERT_CREATE_ERROR);
        }
        //生成个人印章
        Boolean isSeal = sealService.createPersonSeal(perosoninfo);
        if(!isSeal){
            throw new ZxException(ExceptionEnums.SEAL_CREATE_ERROR);
        }
    }

    /**
     * 修改认证的方式
     */
    protected Boolean updateAuthenType(char[] pers,Perosoninfo peroson){
        //再次重新设置认证后的结果
        peroson.setPersoninfoAuthtype(Integer.parseInt(String.valueOf(pers)));
        String sql = "update personinfo set personinfo_authtype = "+peroson.getPersoninfoAuthtype()+
                " where personinfo_id= '" +peroson.getPersoninfoId()+"'";
        //执行返回结果
        Map<String, Object> map = dbService.executeSql(sql);
        String updateRes = String.valueOf(map.get("data"));
        //如果修改成功，则认证成功
        if (StringUtils.equals(updateRes,"1")) {
            return true;
        }
        return false;
    }

}
