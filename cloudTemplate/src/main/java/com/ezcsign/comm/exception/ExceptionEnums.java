package com.ezcsign.comm.exception;

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
    SESSION_HAS_ERROR(401,"账户信息已经失效了"),
    REDIS_QUERY_ERROR(500,"redis查询失败"),
    SELECT_MEMBER_INFO_ERROR(500,"企业没有对应的当前的账户信息"),
    NOTHING_RECHARGE_ERROR(415,"您的账号还没有任何充值记录"),
    INVOICE_CREATE_ERROR(500,"创建对应的发票申请失败"),
    INVOICE_FIELDS_NOT_NULL(500,"填写的字段不能为空哟"),
    APPLY_PAN_INSERT_ERROR(500,"钥匙盘申请出现异常"),
    SELECT_CONF_LIST_ERROR(500,"查询服务配置项出现异常,请仔细检查"),
    OUT_TRADE_NO_SELECT_ERROR(405,"您还没有进行充值操作"),
    ;
    private int code;
    private String msg;
}
