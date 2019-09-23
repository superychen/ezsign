package com.cqzx.controller;

import com.cqzx.domain.CodeEntity;
import com.cqzx.service.CertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 数字证书控制层
 * @Author: cqyc
 * @Date: 2019-8-13
 */
@RestController
@RequestMapping("/create")
public class CertController {

    @Autowired
    private CertService certService;

    /**
     * 提供签约的接口，生成证书
     * @param userid 根据用户的类型来判断传入的是personinfoId还是companyinfoId
     * @param userType 用户的类型 1:个人签约类型，2：企业签约类型
     * @return 返回状态码
     */
    @PostMapping("/cert")
    public CodeEntity generateCertificates(@RequestParam("userid") String  userid,
                                           @RequestParam("userType") String userType){
        return certService.generateCertificates(userid,userType);
    }

    //根据接口.生成个人印章或者企业印章

    /**
     * 提供生成印章的接口（个人/企业）
     * @param userid 传入的personinfoid或者companyinfoid
     * @param userType 表示用户的类型 1：个人印章类型，2：企业印章类型
     * @return 返回状态码
     */
    @PostMapping("/seal")
    public  CodeEntity generateSeal(@RequestParam("userid") String userid,
                                    @RequestParam("userType") String userType){
        return certService.generateSeal(userid,userType);
    }

    /**
     * 组织管理创建子公司
     */
    @PostMapping("/certOrg")
    public CodeEntity generateCertOrg(@RequestParam("customerLoginname") String customerLoginname,
                                      @RequestParam("companyinfoName") String companyinfoName,
                                      @RequestParam("creditcode") String creditcode,
                                      @RequestParam("companyId") String companyId){
        return certService.generateCertOrg(customerLoginname,companyinfoName,creditcode,companyId);
    }

    /**
     * 组织管理创建子公司的印章
     * @param customerId 创建账户ID
     */
    @PostMapping("/sealOrg")
    public CodeEntity generateSealOrg(@RequestParam("customerId") String customerId,
                                      @RequestParam("companyinfoName") String companyinfoName,
                                      @RequestParam("creditcode") String creditcode,
                                      @RequestParam("companyId") String companyId){
        return certService.generateSealOrg(customerId,companyinfoName,creditcode,companyId);
    }


}
