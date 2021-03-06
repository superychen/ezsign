package com.cqzx.service;

import com.alibaba.fastjson.JSONObject;
import com.cqzx.domain.CodeEntity;
import com.cqzx.domain.Companyinfo;
import com.cqzx.domain.Perosoninfo;

/**
 * 证书业务接口，将印章也放在这一块
 */
public interface CertService {

    /**
     * 创建个人证书
     * @param perosoninfo
     * @return
     */
    Boolean createPersonCert(Perosoninfo perosoninfo);

    /**
     * 创建企业证书
     * @param companyinfo
     * @return
     */
    Boolean createCompanyCert(Companyinfo companyinfo);

    /**
     *  提供签约的接口，生成证书
     */
    CodeEntity generateCertificates(String userid, String userType);

    //生成印章的接口
    CodeEntity generateSeal(String userid, String userType);

    //提供组织架构创建子公司结构
    CodeEntity generateCertOrg(String customerLoginname, String companyinfoName, String creditcode,String companyId);

    //提供组织架构创建子公司印章结构
    CodeEntity generateSealOrg(String customerId, String companyinfoName, String creditcode, String companyId);
}
