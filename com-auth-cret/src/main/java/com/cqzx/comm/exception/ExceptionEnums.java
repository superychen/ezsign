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
    REDIS_QUERY_ERROR(500,"获取redis中值异常"),
    FACE_REQUEST_CODE_ERROR(500,"人脸识别获取随机四位数异常"),
    FACE_AUTH_ERROR(500,"人脸认证异常"),
    BANK4_AUTH_PHONE_ERROR(500,"银行卡手机号码输入格式有误"),
    BANK4_AUTH_ERROR(500,"银行卡4要素认证失败"),
    REMIT_MONEY_ERROR(500,"对公打款发生异常"),
    HAS_COMPANY_ERROR(500,"没有相关企业的信息"),
    FILE_UPLOAD_ERROR(500,"文件上传异常"),
    REMIT_AUTH_CREDIT_LEGAL(500,"对公打款认证提交法人以及社会信用代码填写不正确"),
    REMIT_AUTH_AMOUNT_ERROR(500,"对公打款提交的金额错误"),
    REMIT_AUTH_CODE_ERROR(500,"对公打款提交的验证码错误"),
    COMPANY_HAS_AUTH(500,"企业已经认证过了,请不要重复认证"),
    COMPANY_REGISTER_ERROR(500,"企业注册失败"),
    SMS_SERVICE_ERROR(500,"短信服务异常"),
    BANK4_CODE_ERROR(500,"法人认证验证码输入错误"),
    BANK4_LEGAL_PERSON_ERROR(500,"企业对应法人输入错误"),
    SESSION_GET_ERROR(500,"获取数据失败,请重新注册"),
    CERT_CREATE_ERROR(500,"数字证书创建失败"),
    SEAL_CREATE_ERROR(500,"创建印章失败"),
    CUSTOMER_SESSION_ERROR(500,"用户账号已经失效,请重新登录"),
    PROJECT_CREATE_ERROR(500,"企业项目创建失败"),
    FACE_AUTH_HAS_BEAN(500,"人脸识别已经认证过了,请不要重复认证"),
    BANK4_AUTH_HAS_BEAN(500,"银行卡4要素已经被认证过了,请不要重复认证"),
    AUTH_PASS_ERROR(500,"认证后异常"),
    IMG_CODE_ERROR(500,"图片验证码输入错误"),
    SMS_CODE_ERROR(500,"短信验证码输入错误"),
    PHONE_USER_AUTH_NULL(500,"您还未通过其他的认证，请通过其他认证再次进行手机认证"),
    PHONE_AUTH_HAS_BEAN(500,"您已经做过此认证，请不要重复认证"),
    ;
    private int code;
    private String msg;
}
