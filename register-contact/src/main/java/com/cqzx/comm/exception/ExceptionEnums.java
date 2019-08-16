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
    SEND_CODE_ERROR(500,"发送验证码失败"),
    REGISTER_PHONE_REGEX(500,"输入的手机格式不正确"),
    REGISTER_EMAIL_REGEX(500,"输入的邮箱格式不正确"),
    INPUT_CODE_ERROR(500,"输入验证码错误"),
    REGISTER_SEND_CODE_ERROR(500,"验证码还没有输入或者验证码已经失效"),
    ALL_CUSTOMER_NOT_NULL(500,"输入的内容不能为空"),
    ADMIN_REGISTER_ERROR(500,"企业管理员注册账户异常"),
    API_REGISTER_ERROR(500,"api注册失败"),
    FIND_USERNAME_ERROR(500,"找回用户名失败"),
    ;
    private int code;
    private String msg;
}
