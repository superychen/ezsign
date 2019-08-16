package com.cqzx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.util.JavaBeanUtil;
import com.cqzx.comm.util.MD5Hash;
import com.cqzx.domain.Companyinfo;
import com.cqzx.domain.Customer;
import com.cqzx.domain.Memberinfo;
import com.cqzx.domain.Perosoninfo;
import com.cqzx.feign.CertSealService;
import com.cqzx.feign.DbService;
import com.cqzx.service.ApiRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

/**
 * @Description: api业务逻辑处理层
 * @Author: cqyc
 * @Date: 2019-8-14
 */
@Service
@Slf4j
public class ApiRegisterServiceImpl implements ApiRegisterService {

    @Autowired
    private DbService dbService;

    @Autowired
    private CertSealService certSealService;

    /**
     * todo 还要添加personinfo表中的数据
     * 通过api注册用户，保存companyinfo的数据,添加memberinfo信息
     * @param map 用户传递的数据必须包含：
     *  companyinfoName,companyinfoCreditcode,companyinfoLegalperson,
     *  companyinfoFile,companyinfoTelephone
     *  companyinfoEmail,
     *  companyinfoAgent,companyinfoAgenttele(企业授权人身份证号码,授权人联系电话,对应数据库没有对应字段)
     * 账号传递过来的参数需要：
     * customerLoginname 登录名
     * @return
     */
    @Override
    public Boolean apiRegister(Map<String,String> map) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Customer customer = new Customer();
        customer.setCustomerId(MD5Hash.UUIDCreate());//登录用户的id
        //设置用户登录的密码
        customer.setCustomerLoginname(map.get("userName"));//登录用用户名
        customer.setCustomerPassword(MD5Hash.encodeByMd5(customer.getCustomerLoginname()+"111111"));
        customer.setCustomerRegisttime(new Date());//设置注册时间
        customer.setCustomerState(2);//设置为初始账户,需要更改密码
        customer.setCustomerLastlogtime(new Date());//最后登录时间

        Map<String, Object> cusMap = JavaBeanUtil.convertBeanToMap(customer);
        //保存结果
        String save = dbService.save("customer", "customer_id", cusMap);
        log.debug("保存结果-->{}",save);

        JSONObject saveRes = JSONObject.parseObject(save);
        String msg = (String) saveRes.get("data");
        if(StringUtils.equals(msg,"could not execute statement")){
            throw new ZxException(ExceptionEnums.API_REGISTER_ERROR);
        }

        Perosoninfo perosoninfo = new Perosoninfo();
        perosoninfo.setPersoninfoId(MD5Hash.UUIDCreate());//创建个人用户id
        perosoninfo.setPersoninfoName(map.get("agentName"));//企业授权人姓名
        perosoninfo.setPersoninfoIdcard(map.get("agentIDcard"));//企业授权人身份证号码
        perosoninfo.setPersoninfoTelephone(map.get("agenttelephone"));//授权人联系电话
        perosoninfo.setPersoninfoState(0);//api注册personinfo都是 0:未认证
        perosoninfo.setPersoninfoAuthtype(1000000);//用户身份认证方式,第一位不算
        //todo 对应套餐id，没有得到接口
//        perosoninfo.setPersoninfoMealid();
        perosoninfo.setPersoninfoEmail(map.get("agentemail"));//授权人联系邮箱
        perosoninfo.setPersoninfoConfig1(1);//签约通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
        perosoninfo.setPersoninfoConfig2(1);//审批通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
        perosoninfo.setPersoninfoConfig3(1);//抄送通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
        perosoninfo.setPersoninfoConfig4(1);//审批通过（不通过）通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
        perosoninfo.setPersoninfoConfig5(1);//签约完成通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
        perosoninfo.setPersoninfoConfig6(1);//拒签通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
        perosoninfo.setPersoninfoConfig7(1);//用户提醒,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
        perosoninfo.setPersoninfoCheckbox8(1);//自动保存联系人,1 勾选保存;2 不保存
        Map<String, Object> personMap = JavaBeanUtil.convertBeanToMap(perosoninfo);
        //保存结果
        String personSave = dbService.save("personinfo", "personinfo_id", personMap);
        log.debug("保存结果-->{}",personSave);

        JSONObject personSaveRes = JSONObject.parseObject(personSave);
        String personMsg = (String) personSaveRes.get("data");
        if(StringUtils.equals(personMsg,"could not execute statement")){
            throw new ZxException(ExceptionEnums.API_REGISTER_ERROR);
        }

        Companyinfo companyinfo = new Companyinfo();
        //生成companyinfo数据
        companyinfo.setCompanyinfoId(MD5Hash.UUIDCreate());//企业id
        companyinfo.setCompanyinfoName(map.get("fullName"));//企业姓名
        companyinfo.setCompanyinfoCreditcode(map.get("creditCard"));//统一社会信用代码
        companyinfo.setCompanyinfoLegalperson(map.get("legalPerson"));//企业法人姓名
        companyinfo.setCompanyinfoFile(map.get("creditCardPath"));//企业的营业执照扫描件的地址
        companyinfo.setCompanyinfoTelephone(map.get("telephone"));//企业联系电话
        companyinfo.setCompanyinfoEmail(map.get("email"));//企业联系邮箱
        companyinfo.setCompanyinfoAgent(map.get("agentName"));//企业授权人姓名
        companyinfo.setCompanyinfoAgenttele(map.get("agenttelephone"));//授权人联系电话;
        companyinfo.setCompanyinfoState(0);//设置企业状态设置为未认证：0
        companyinfo.setCompanyinfoAuthtype(0);//设置企业认证方式什么认证方式都没有：0
        companyinfo.setCompanyinfoType(1);//组织关系类型,定义组织结构成员数量

        Map<String, Object> comMap = JavaBeanUtil.convertBeanToMap(companyinfo);
        //保存结果
        String comSave = dbService.save("companyinfo", "companyinfo_id", comMap);
        log.debug("保存结果-->{}",comSave);

        JSONObject comSaveRes = JSONObject.parseObject(comSave);
        String comMsg = (String) comSaveRes.get("data");
        if(StringUtils.equals(comMsg,"could not execute statement")){
            throw new ZxException(ExceptionEnums.API_REGISTER_ERROR);
        }

        Memberinfo memberinfo = new Memberinfo();
        memberinfo.setMemberinfoId(MD5Hash.UUIDCreate());
        memberinfo.setMemberinfoType(0);//根节点公司
        memberinfo.setMemberinfoCompanyname(companyinfo.getCompanyinfoName());//企业的名称
        memberinfo.setMemberinfoCompanyid(companyinfo.getCompanyinfoId());//节点对应的企业id
        memberinfo.setMemberinfoCustomerid(customer.getCustomerId());//节点关联的账户id
        memberinfo.setMemberinfoStatus(1);//设置节点状态为有效
        memberinfo.setMemberinfoCreattime(new Date());//企业节点创建的时间
        memberinfo.setMemberinfoRole(1);//默认创建企业角色为超级管理员
        //todo 企业创建时生成的memberinfo对应的用户套餐，未给出接口，现在不做
//        memberinfo.setMemberinfoMealid();
        Map<String, Object> memMap = JavaBeanUtil.convertBeanToMap(memberinfo);
        //保存结果
        String memSave = dbService.save("companyinfo", "companyinfo_id", memMap);
        log.debug("保存结果-->{}",memSave);

        JSONObject memSaveRes = JSONObject.parseObject(memSave);
        String memMsg = (String) memSaveRes.get("data");
        if(StringUtils.equals(memMsg,"could not execute statement")){
            throw new ZxException(ExceptionEnums.API_REGISTER_ERROR);
        }

        //保存personinfo后生成证书，2：表示传入的用户类型为企业
        certSealService.generateCertificates(companyinfo.getCompanyinfoId(),"2");
        //生成个人印章, 2：表示传入的用户类型为企业
        certSealService.generateSeal(companyinfo.getCompanyinfoId(),"2");
        return true;
    }

}
