package com.cqzx.controller;

import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.util.ValidUtils;
import com.cqzx.domain.Customer;
import com.cqzx.domain.Perosoninfo;
import com.cqzx.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 个人认证之人脸识别，银行卡4要素，身份证认证，手机号认证，QQ、微信认证
 * @Author: cqyc
 * @Date: 2019-7-31
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * 人脸认证
     * @param file 人脸识别的文件
     * @param idCardname 身份证姓名
     * @param idCardnumber 身份证号码
     * @param requestid requestid的值,从下面的
     * @return 返回认证成功后的基本信息结果json字符串，如果是失败会抛出异常
     * @throws IOException
     */
    @PostMapping("/face")
    public String faceAuthtication(@RequestParam("file") MultipartFile file,
                                   @RequestParam("requestid") String requestid,
                                   @RequestParam("idCardname") String idCardname,
                                   @RequestParam("idCardnumber") String idCardnumber) throws IOException {
        if(!file.isEmpty()){
            //首先判断是不是空的文件
            //对文文件的全名进行截取然后在后缀名进行删选。
            int begin = file.getOriginalFilename().indexOf(".");
            int last = file.getOriginalFilename().length();
            //获得文件后缀名
            String a = file.getOriginalFilename().substring(begin, last);
            //我这边需要的mp4文件所以说我这边直接判断就是了
            if (a.endsWith(".mp4")) {
                byte[] bytes = file.getBytes();
                String res = authenticationService.faceAuthtication(bytes, requestid, idCardname, idCardnumber);
                return res;
            } else {
                throw new ZxException(ExceptionEnums.FACE_AUTH_ERROR);
            }
        }else {
            throw new ZxException(ExceptionEnums.FACE_AUTH_ERROR);
        }
    }

    /**
     * 进入人脸识别的页面时需要发起一个请求，
     * 随机生成一个四位数，在人脸识别需要录制这段视频需要读出这个验证码才能进行验证
     * @return 返回requestid的值，以及math验证码的值
     */
    @GetMapping("/face/code")
    public Map<String,String> faceAuthCode(){
        String res = authenticationService.faceAuthCode();
        JSONObject jsonObject = JSONObject.parseObject(res);
        Map<String, String> map = new HashMap<>();
        map.put("requestid",String.valueOf(jsonObject.get("requestid")));
        map.put("math",String.valueOf(jsonObject.get("math")));
        return map;
    }

    /**
     * 银行卡4要素验证
     * @param bankid 银行卡号
     * @param idnumber 身份证号
     * @param fullname 姓名
     * @param phonenum 电话
     * @return 返回接口验证信息，如果验证成功返回json字符串，如果没有则抛出异常
     */
    @PostMapping("/bank4")
    public String bank4Auth(@RequestParam("bankid") String bankid,
                            @RequestParam("idnumber") String idnumber,
                            @RequestParam("fullname") String fullname,
                            @RequestParam("phonenum") String phonenum){
        boolean mobile = ValidUtils.isMobile(phonenum);
        if(mobile){
            return authenticationService.bank4Auth(bankid,idnumber,fullname,phonenum);
        }else{
            throw new ZxException(ExceptionEnums.BANK4_AUTH_PHONE_ERROR);
        }
    }

    /**
     * 在注册时后人脸识别通过之后，进行关联个人信息，注册个人信息
     * @param  perosoninfo 里面需要包含的参数 idCardname 身份证姓名，idCardnumber 身份证号码，telephone 手机号码
     * @return
     */
    @PostMapping("/person/regist")
    public Boolean registerPerson(@RequestBody Perosoninfo perosoninfo){
        return authenticationService.registerPerson(perosoninfo);
    }

}
