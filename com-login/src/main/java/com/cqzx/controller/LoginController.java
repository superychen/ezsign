package com.cqzx.controller;

import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.util.JavaBeanUtil;
import com.cqzx.domain.CodeEntity;
import com.cqzx.domain.Customer;
import com.cqzx.domain.ReCode;
import com.cqzx.service.LoginService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Map;

/**
 * @Description: 用户登录控制层
 * @Author: cqyc
 * @Date: 2019-7-26
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class LoginController {

    private final static Integer PHONE_LOGIN_TYPE=0;
    private final static Integer EAMIL_LOGIN_TYPE=1;

    @Autowired
    private LoginService loginService;

    //验证码
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private HttpServletRequest request;

    /**
     * 账号密码登录
     * @param loginname 登录名，不能为空
     * @param password 密码，不能为空
     * @return
     */
    @PostMapping("/login")
    public Customer loginWithPassword(@RequestParam("loginname") String loginname,
                                      @RequestParam("password") String password,
                                      @RequestParam(value = "imgcode",required = false) String imgcode
                                      ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Customer customer = loginService.loginWithPassword(loginname, password, imgcode);
        Map<String, Object> map = JavaBeanUtil.convertBeanToMap(customer);
        request.getSession().setAttribute("customer",map);// 将cuestomer转换为map，存入session
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
        Map<String,Object> customerMap = (Map<String, Object>) session.getAttribute("customer");
        if(customerMap.size() <= 0){
            throw new ZxException(ExceptionEnums.SESSION_USER_ERROR);
        }
        String jsonObject = new JSONObject(customerMap).toJSONString();
        Customer customer = JSONObject.parseObject(jsonObject, Customer.class);

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
    public  void kapatchaImg(HttpServletResponse response,HttpSession session) throws IOException {
        //生成随机文本
        String str = defaultKaptcha.createText();
        session.setAttribute("imgcode",str);
//        loginService.kapatchaImg(str,loginname);
        //生成图像
        BufferedImage image = defaultKaptcha.createImage(str);
        //设置输出类型
        response.setContentType("image/png");
        //输出图片流
        ImageIO.write(image,"png",response.getOutputStream());
    }

    /**
     * 发送邮箱剩余次数
     * @param email 邮箱地址
     * @param rename 姓名（人）
     * @param business 内容
     * @param times 剩余次数
     * @return 返回json格式的结果
     */
    @PostMapping("/email/times")
    public String sendEmailAddress(@RequestParam("email") String email,
                                   @RequestParam("rename") String rename,
                                    @RequestParam("business") String business,
                                   @RequestParam("times") String times){
        return loginService.sendEmailAddress(email,rename,business, times);
    }

    @PostMapping("/quit/user")
    public CodeEntity quitUser(HttpSession session){
        session.invalidate();
        return new CodeEntity(200,"ok");
    }
}
