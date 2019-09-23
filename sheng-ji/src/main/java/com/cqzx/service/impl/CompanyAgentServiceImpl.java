package com.cqzx.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.util.ToJson;
import com.cqzx.comm.util.ValidUtils;
import com.cqzx.comm.util.api.CompanyApi;
import com.cqzx.domain.CodeEntity;
import com.cqzx.domain.Companyinfo;
import com.cqzx.feign.DbService;
import com.cqzx.feign.FileService;
import com.cqzx.feign.SmsService;
import com.cqzx.service.AuthenticationService;
import com.cqzx.service.CompanyAgentService;
import com.cqzx.service.CompanyAuthenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 授权书认证业务逻辑层
 * @Author: cqyc
 * @Date: 2019-8=02
 */
@Service
@Slf4j
public class CompanyAgentServiceImpl implements CompanyAgentService {

    private final static Integer COMPANY_AGENT_STATE_UNPASS=1;//已提交认证未审核
    private static final Integer COMPANY_LEGAL_STATE_PASS=2;//法人认证通过未开通组织服务
    private final static Integer COMPANY_AGENT_AUTHTYPE=3;//授权书认证
    private static final Integer COMPANY_LEGAL_AUTHTYPE=2;//法人认证：银行卡4要素
    private final static Integer PHONE_CODE_TYPE=0;//存入的redis中的为手机类型

    @Autowired
    private FileService fileService;

    @Autowired
    private DbService dbService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CompanyAuthenService companyAuthenService;

    @Autowired
    private CompanyApi companyApi;

    /**
     * 授权书认证
     * @param bytes 授权书字节流
     * @param originalFilename 授权书文件名称
     * @param companyinfoAgent 企业授权人,
     * @param companyinfoAgenttele 企业授权人电话,
     * @return
     */
    @Override
    public Boolean agentAuth(byte[] bytes, String originalFilename, String companyinfoAgent,
                             String companyinfoAgenttele,String companyinfoName) {
        Companyinfo companyinfo = queryCompany(companyinfoName);

        //上传授权书到fastdfs
        String fileId = uploadFile(bytes,originalFilename);

        String sql="update companyinfo set companyinfo_state="+COMPANY_AGENT_STATE_UNPASS+","+
                "companyinfo_authtype="+COMPANY_AGENT_AUTHTYPE+","+
                "companyinfo_agent='"+companyinfoAgent+"',"+
                "companyinfo_agenttele='"+companyinfoAgenttele+"',"+
                "companyinfo_authfileid='"+fileId+"' where companyinfo_name='"+companyinfoName+"'";
        Map<String, Object> upResult = dbService.executeSql(sql);

        //TODO 通知管理端那边去审核授权书，审核后需要修改当前企业的状态为2--->认证通过
        String upData = String.valueOf(upResult.get("data"));
        //比较当前比较的结果
        if (StringUtils.equals(upData,"1")) {
            return true;
        }
        return false;
    }

    /**
     * 发送法人手机验证码
     * @param phoneNum 法人手机号
     * @return
     */
    @Override
    public String legalAuthSendSMS(String phoneNum) {
        String res = smsService.loginWithPhone(phoneNum, PHONE_CODE_TYPE);
        return res;
    }

    /**
     * 法人认证业务处理
     * @param bankid 银行卡号
     * @param idnumber 身份证号
     * @param fullname 姓名
     * @param phonenum 手机号
     * @param code 验证码
     * @param companyinfoName 企业名称
     * @return
     */
    @Override
    public Boolean legalAuth(String bankid, String idnumber, String fullname, String phonenum, String code,String companyinfoName) {
        //判断当前企业是否已经被认证过
        Companyinfo companyinfo = queryCompany(companyinfoName);
        //企查查
        String res = companyLook(companyinfoName);

        JSONObject jsonObject = JSONObject.parseObject(res);

        //得到企查查的基本信息
        String result = String.valueOf(jsonObject.get("Result"));
        JSONObject companyLook = JSONObject.parseObject(result);
        //查到企查查中的法人姓名
        String operName = String.valueOf(companyLook.get("OperName"));
        //判断当前法人是否是对应企业的法人，如果不是抛出异常
        if (!StringUtils.equals(companyinfo.getCompanyinfoLegalperson(),operName)) {
            throw new ZxException(ExceptionEnums.BANK4_LEGAL_PERSON_ERROR);
        }

        //验证手机号格式是否正确
        boolean mobile = ValidUtils.isMobile(phonenum);
        if(!mobile){
            throw new ZxException(ExceptionEnums.BANK4_AUTH_PHONE_ERROR);
        }
        //校验验证码
        Map<String, Object> phoneCode = dbService.getString(phonenum + ":phoneCode");

        String phoneData = (String) phoneCode.get("data");
        if (!StringUtils.equals(code,phoneData)) {
            throw new ZxException(ExceptionEnums.BANK4_CODE_ERROR);
        }
        //银行卡4要素认证
        authenticationService.bank4Auth(bankid, idnumber, fullname, phonenum);

        //修改法人信息
        String sql="update companyinfo set companyinfo_state="+COMPANY_LEGAL_STATE_PASS+","+
                "companyinfo_authtype="+COMPANY_LEGAL_AUTHTYPE+","+
                "companyinfo_legalpersonid='"+idnumber+"',"+
                "companyinfo_legalpersonprono = '"+bankid+"',"+
                "companyinfo_legalpersontel= '"+phonenum+"' where companyinfo_name='"+companyinfoName+"'";

        Map<String, Object> upResult = dbService.executeSql(sql);

        String upData = String.valueOf(upResult.get("data"));
        //比较当前比较的结果
        if (StringUtils.equals(upData,"1")) {
            //认证完成之后进行的操作
            companyAuthenService.afterCompanyAuth(companyinfo);
            return true;
        }
        return false;
    }

    //授权书认证之后需要做的事情
    @Override
    public Boolean afterAgentAuth(String companyinfoCreditcode) {
        Map<String, Object> map = dbService.get("companyinfo", "companyinfo_creditcode", companyinfoCreditcode);
        //将查询到的结果转换为json字符串
        if(StringUtils.equals(String.valueOf(map.get("data")),"{}")){
            throw new ZxException(ExceptionEnums.SELECT_COMPANY_ERROR);
        }
        String data = ToJson.stringToJson(String.valueOf(map.get("data")));
        Companyinfo companyinfo = JSONObject.parseObject(data, Companyinfo.class);
        log.info("授权书认证时得到的企业名称--->{}",companyinfo.getCompanyinfoName());


        String sql = "update companyinfo set companyinfo_state="+2+" ,companyinfo_authtype="+3+" where companyinfo_creditcode='"+companyinfoCreditcode+"'";
        //修改后的结果
        Map<String, Object> upResult = dbService.executeSql(sql);
        String upData = String.valueOf(upResult.get("data"));
        log.debug("修改后的结果--->{}",upResult);

        //企业认证成功后需要做的事情
        companyAuthenService.afterCompanyAuth(companyinfo);

        //比较当前比较的结果
        if (StringUtils.equals(upData,"1")) {
            return true;
        }
        return false;
    }

    /**
     * 上传文件
     * @param bytes
     * @param originalFilename
     * @return
     */
    protected String uploadFile(byte[] bytes, String originalFilename){
        //上传文件
        CodeEntity upload = fileService.upload(bytes, originalFilename);
        Map<String,Object> data = (Map<String, Object>) upload.getData();
        log.debug("uploadfile--->data--->{}",data.get("fileid"));
        String res = StringUtils.replace(String.valueOf(data.get("fileid")),";;;","/");
        return res;
    }


    /**
     * 企查查接口
     * @param companyinfoName 企业名称
     * @return
     */
    protected String companyLook(String companyinfoName){
        String res = companyApi.companyAuth(companyinfoName);
        JSONObject jsonObject = JSONObject.parseObject(res);

        log.debug("企查查查询结果--->{}",res);
        Object datas = jsonObject.get("datas");

        JSONObject jsonObject1 = JSONObject.parseObject(String.valueOf(datas));

        Object status = jsonObject1.get("Status");

        //判断企查查是否有这个企业
        if (!StringUtils.equals(String.valueOf(status),"200")) {
            throw new ZxException(ExceptionEnums.HAS_COMPANY_ERROR);
        }

        return String.valueOf(datas);
    }

    /**
     * 查询企业信息,判断企业是否已经被认证过，如果已经被认证，则抛出异常
     * @param companyinfoName
     * @return
     */
    protected Companyinfo queryCompany(String companyinfoName){
        //先查询得到的企业的信息
        Map<String, Object> getResult = dbService.get("companyinfo", "companyinfo_name", companyinfoName);
        //将查询到的结果转换为json字符串
        Map<String, Object> map = (Map<String, Object>) getResult.get("data");
        String jsonString = JSON.toJSONString(map);
        Companyinfo companyinfo = JSONObject.parseObject(jsonString, Companyinfo.class);

        //如果当前企业已经认证过,就不需要当前认证
        if(companyinfo.getCompanyinfoAuthtype() != 0){
            throw new ZxException(ExceptionEnums.COMPANY_HAS_AUTH);
        }
        return companyinfo;
    }
}
