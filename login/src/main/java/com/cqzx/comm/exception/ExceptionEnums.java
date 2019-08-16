package com.cqzx.comm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 异常枚举类
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnums {

    EXECUTE_SQL_INSERT_ERROR(500,"执行insert语句插入异常"),
    EXECUTE_SQL_UPDATE_ERROR(500,"执行update语句修改异常"),
    EXECUTE_SQL_SELECT_ERROR(500,"执行select语句查询异常"),
    EXECUTE_SQL_DELETE_ERROR(500,"执行delete语句删除异常"),
    EXECUTE_REDIS_PUT_ERROR(500,"向redis存值错误"),
    EXECUTE_REDIS_GET_ERROR(500,"向redis取值错误"),
    EXECUTE_REDIS_DELETE_ERROR(500,"向redis删除值错误"),
    LOGIN_USERNAME_PASSWORD_ERROR(500,"输入的用户名或者密码不正确"),
    LOGIN_IMG_CODE(500,"输入的验证码不正确"),
    LOGIN_PHONE_REGEX(500,"手机号码格式不正确"),
    LOGIN_READY_PHONE_CODE(500,"请稍后在发送短信验证码"),
    LOGIN_EMAIL_REGEX(500,"邮箱格式不正确"),
    LOGIN_READY_EMAIL_CODE(500,"请稍后在发送邮箱验证码"),
    LOGIN_PHONE_SEND_PHONE(500,"还没有发送手机验证码或者验证码已经失效哟"),
    LOGIN_EMAIL_SEND_PHONE(500,"还没有发送邮箱验证码或者验证码已经失效哟"),
    INPUT_ERROR_CODE(500,"没有输入验证码或者验证码输入错误"),
    SEND_ERROR_CODE(500,"发送验证码失败"),
    LOGIN_CODE_INIT(402,"当前登录类型为初始用户，需修改密码才能正常登陆"),
    SESSION_USER_ERROR(500,"当前的登录用户已失效,请重新登录"),
    USER_SHOULD_AUTH(500,"进入个人签必须先通过认证"),
    COMPANY_SHOULD_AUTH(500,"进入企业签必须通过认证"),
    ;
    private int code;
    private String msg;
}
