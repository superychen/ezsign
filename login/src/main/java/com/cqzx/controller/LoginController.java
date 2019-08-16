package com.cqzx.controller;

import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.domain.Customer;
import com.cqzx.service.LoginService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Description: 用户登录控制层
 * @Author: cqyc
 * @Date: 2019-7-26
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    private final static Integer PHONE_LOGIN_TYPE=0;
    private final static Integer EAMIL_LOGIN_TYPE=1;

    @Autowired
    private LoginService loginService;

    //验证码
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    /**
     * 账号密码登录
     * @param loginname 登录名，不能为空
     * @param password 密码，不能为空
     * @param imgcode 可以为空
     * @return
     */
    @PostMapping("/login")
    public Customer loginWithPassword(@RequestParam("loginname") String loginname,
                                      @RequestParam("password") String password,
                                      @RequestParam(value = "imgcode",required = false) String imgcode,
                                      HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Customer customer = loginService.loginWithPassword(loginname, password, imgcode);
        request.getSession().setAttribute("customer",customer);
        return customer;
    }


    /**
     * 发送验证码
     * 如果是手机号或者邮箱
     * @param loginname 手机号或者邮箱，不能为空
     * @param loginType 登录的账号的类型,0表示手机登陆，1表示邮箱登录
     * @return
     */
    @GetMapping("/code")
    public String loginWithPhone(@RequestParam("loginname") String loginname,
                                   @RequestParam("loginType")Integer loginType){
        if(loginType == PHONE_LOGIN_TYPE){
            //判断当前登录的类型是否是手机登陆
            return loginService.sendWithPhone(loginname);
        }else if(loginType == EAMIL_LOGIN_TYPE){
            //登录类型是否为邮箱登录
            return loginService.sendWithEamil(loginname);
        }
        return null;
    }

    /**
     * 登录之后选择个人签约、或者企业签约
     * @param session session
     * @param userType 用户在登录之后选择的类型 1 表示进入个人签约，2 表示进入企业签约
     * @return
     */
    @PostMapping("/type")
    public Boolean changeUserType(HttpSession session,@RequestParam("userType") Integer userType){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            throw new ZxException(ExceptionEnums.SESSION_USER_ERROR);
        }
        return loginService.changeUserType(customer,userType);
    }

    /**
     * 验证码登录
     * @param loginname 登录的名称
     * @param loginType 登录的类型
     * @param code 验证码
     * @return 当前账户信息
     */
    @PostMapping("/login/code")
    public Customer loginWithCode(@RequestParam("loginname") String loginname,
                                  @RequestParam("loginType") Integer loginType,
                                  @RequestParam("code") String code){
        return loginService.loginWithCode(loginname,loginType,code);
    }

    /**
     * 生成随机验证码，必须要携带用户输入的账号
     */
    @GetMapping("/kaptcha.png")
    public  void kapatchaImg(HttpServletResponse response,@RequestParam("loginname") String loginname) throws IOException {
        //生成随机文本
        String str = defaultKaptcha.createText();
        loginService.kapatchaImg(str,loginname);
        //生成图像
        BufferedImage image = defaultKaptcha.createImage(str);
        //设置输出类型
        response.setContentType("image/png");
        //输出图片流
        ImageIO.write(image,"png",response.getOutputStream());
    }

}
