package com.cqzx.controller;

import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.domain.Companyinfo;
import com.cqzx.domain.Customer;
import com.cqzx.domain.vo.CustomerVo;
import com.cqzx.service.ApiRegisterService;
import com.cqzx.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @Description: 注册控制层
 * @Author: cqyc
 * @Date: 2019-8-14
 */
@RestController
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private ApiRegisterService apiRegisterService;

    /**
     * 用户注册新的用户
     * registerType 用户注册的类型 0为手机号码注册，1为邮箱注册
     * code 用户输入的验证码
     * @param customer 前台接收的用户名和密码
     * @return
     */
    @PostMapping("/register")
    public Boolean register(@RequestBody @Valid CustomerVo customerVo,BindingResult result,HttpServletRequest request, Customer customer) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if(result.hasFieldErrors()){
            throw new ZxException(ExceptionEnums.ALL_CUSTOMER_NOT_NULL);
        }
        customer.setCustomerLoginname(customerVo.getCustomerLoginname());
        customer.setCustomerPassword(customerVo.getCustomerPassword());
        //注册完成后session保存当前的customer的信息
        Customer customer1 = registerService.register(customer, customerVo.getRegisterType(), customerVo.getCode());
        request.getSession().setAttribute("registerCustomer",customer1);
        return true;
    }

    /**
     * 注册时发送验证码，调用登录服务的发送验证码
     * @param registerName 注册的用户名
     * @param registerType 注册时的注册账户类型,0表示手机，1表示邮箱
     * @return
     */
    @GetMapping("/code")
    public String sendCode(@RequestParam("registerName") String registerName,
                           @RequestParam("registerType")Integer registerType){
        return registerService.sendCode(registerName,registerType);
    }


    /**
     * 系统默认注册（管理员注册）
     * todo 先将认证服务这一块写完再来进行操作
     * @param name 姓名
     * @param telephone 手机号
     * @param idcard 身份证号
     * @return
     */
    @PostMapping("/admin/register")
    public Boolean adminRegister(@RequestParam("name") String name,
                                 @RequestParam("telephone") String telephone,
                                 @RequestParam("idcard") String idcard) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return registerService.adminRegister(name,telephone,idcard);
    }


    /**
     * 通过api注册用户，保存companyinfo的数据,添加memberinfo信息
     * @param map 用户传递的数据必须包含：
     * companyinfoName,companyinfoCreditcode,companyinfoLegalperson,companyinfoFile,companyinfoTelephone
     * companyinfoEmail,companyinfoAgent,companyinfoAgenttele(企业授权人身份证号码,授权人联系电话对应数据库没有对应字段)
     * 账号传递过来的参数需要：
     * customerLoginname 登录名
     * @return
     */
    @PostMapping("/api/register")
    public Boolean apiRegister(@RequestBody Map<String,String> map) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return apiRegisterService.apiRegister(map);
    }
}
