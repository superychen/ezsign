package com.cqzx.domain.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Description: 用户注册扩展类
 * @Author: cqyc
 * @Date: 2019-7-30
 */
@Data
public class CustomerVo {
    /**
     *  登录名
     */
    @NotBlank(message = "用户名不能为空")
    private String customerLoginname;

    /**
     * 密码
     */
    @NotBlank(message = "用户输入的密码不能为空")
    private String customerPassword;

    /**
     * 用户注册的类型 0为手机号码注册，1为邮箱注册
     */
    private Integer registerType;

    /**
     * 用户输入的验证码
     */
    @NotBlank(message = "用户输入验证码不能为空")
    private String code;
}
