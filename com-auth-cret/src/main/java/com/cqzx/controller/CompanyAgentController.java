package com.cqzx.controller;

import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.service.CompanyAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description: 企业认证之授权书认证，法人认证
 * @Author: cqyc
 * @Date: 2019-8-2
 */
@RestController
@RequestMapping("/company")
public class CompanyAgentController {

    @Autowired
    private CompanyAgentService companyAgentService;

    /**
     * 授权书认证
     * @param file
     * @param companyinfoAgent
     * @param companyinfoAgenttele
     * @param companyinfoName
     * @return
     */
    @PostMapping("/agent/auth")
    public Boolean agentAuth(@RequestParam("file") MultipartFile file,
                             @RequestParam("companyinfoAgent") String companyinfoAgent,
                             @RequestParam("companyinfoAgenttele") String companyinfoAgenttele,
                             @RequestParam("companyinfoName") String companyinfoName){
        try {
            byte[] bytes = file.getBytes();
            return companyAgentService.agentAuth(bytes,file.getOriginalFilename(),companyinfoAgent,companyinfoAgenttele,companyinfoName);
        } catch (IOException e) {
            throw new ZxException(ExceptionEnums.FILE_UPLOAD_ERROR);
        }

    }


    //法人认证时发送短信验证码
    @GetMapping("/auth/sms")
    public String legalAuthSendSMS(@RequestParam("phoneNum") String phoneNum){
        return companyAgentService.legalAuthSendSMS(phoneNum);
    }

    /**
     * 法人认证: 银行卡4要素验证
     * @param bankid 银行卡号
     * @param idnumber 身份证号
     * @param fullname 姓名
     * @param phonenum 电话
     * @param code 手机验证码
     * @param companyinfoName 企业名称
     * @return 返回接口验证信息，如果验证成功返回json字符串，如果没有则抛出异常
     */
    @PostMapping("/auth/legal")
    public Boolean legalAuth(@RequestParam("bankid") String bankid,
                             @RequestParam("idnumber") String idnumber,
                             @RequestParam("fullname") String fullname,
                             @RequestParam("phonenum") String phonenum,
                             @RequestParam("companyinfoName") String companyinfoName,
                             @RequestParam("code") String code){
        return companyAgentService.legalAuth(bankid,idnumber,fullname,phonenum,code,companyinfoName);

    }
}
