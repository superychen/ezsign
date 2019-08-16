package com.cqzx.service;

/**
 * @Description: 授权书认证业务接口层
 * @Author: cqyc
 * @Date: 2019-8-02
 */
public interface CompanyAgentService {

    Boolean agentAuth(byte[] bytes, String originalFilename, String companyinfoAgent, String companyinfoAgenttele,String companyinfoName);

    String legalAuthSendSMS(String phoneNum);

    Boolean legalAuth(String bankid, String idnumber, String fullname, String phonenum, String code,String companyinfoName);
}
