package com.cqzx.controller;

import com.cqzx.domain.Perosoninfo;
import com.cqzx.service.PersonAuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 个人登录后认证控制层
 * @Author: cqyc
 * @Date: 2019-8-9
 */
@RestController
@RequestMapping("/person")
public class PersonAuthenController {

    @Autowired
    private PersonAuthenService personAuthenService;

    /**
     * 在登录之后如果用户没有在注册的时候人脸识别，在登录之后进行的人脸识别通过后执行这个请求
     * @param perosoninfo 里面需要包含的参数 idCardname 身份证姓名，idCardnumber 身份证号码，telephone 手机号码
     * @return true/false
     */
    @PostMapping("/face")
    public Boolean updateAfterFace(@RequestBody Perosoninfo perosoninfo){
        return personAuthenService.updateAfterFace(perosoninfo);
    }

    /**
     * 登录之后的手机号认证，手机号认证必须在其他认证做了之后才能进行操作
     * @param personinfoName 个人姓名
     * @param personinfoTelephone 个人手机号码
     * @param imgCode 图片验证码
     * @param code 手机号的短信验证码
     * @return true/false
     */
    @PostMapping("/phone/auth")
    public Boolean authAfterPhone(@RequestParam("personinfoName") String personinfoName,
                                  @RequestParam("personinfoTelephone") String personinfoTelephone,
                                  @RequestParam("imgCode") String imgCode,
                                  @RequestParam("code") String code){
        return personAuthenService.authAfterPhone(personinfoName,personinfoTelephone,imgCode,code);
    }


    /**
     * 银行卡4要素验证通过之后需要做的事情
     * @param bankid 银行卡号
     * @param idnumber 身份证号
     * @param fullname 姓名
     * @param phonenum 电话
     * @param code 银行卡预留电话验证码
     * @return 返回接口验证信息，如果验证成功返回json字符串，如果没有则抛出异常
     */
    @PostMapping("/bank")
    public Boolean updateAfterBank(@RequestParam("bankid") String bankid,
                                   @RequestParam("idnumber") String idnumber,
                                   @RequestParam("fullname") String fullname,
                                   @RequestParam("phonenum") String phonenum,
                                   @RequestParam("code") String code){
        return personAuthenService.updateAfterBank(bankid,idnumber,fullname,phonenum,code);
    }
}
