package com.ezcsign.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recharge {
    /**
     * 充值信息ID,
     */
    private String rechargeId;

    /**
     * 开票申请ID,
     */
    private String rechargeInvoiceid;

    /**
     * 开票状态:1=未开;2=已开
     */
    private Integer rechargeInvoicestatus;

    /**
     * 充值发起用户ID,（对应personinfo或member的id）
     */
    private String rechargeOperatorid;

    /**
     * 操作者类型,1 对应personinfo;2 对应memberinfo
     */
    private Integer rechargeOperatortype;

    /**
     * 充值到账用户ID,（对应personinfo或member的id）
     */
    private String rechargeArrivalid;

    /**
     * 到账用户类型,1 对应personinfo;2 对应memberinfo
     */
    private Integer rechargeArrivaltype;

    /**
     * 充值金额,
     */
    private Long rechargeMoneyamount;

    /**
     * 到账用户项目ID,
     */
    private String rechargeArrivalprojectid;

    /**
     * 操作类型,1 线上充值;2 线下充值
     */
    private Integer rechargeOperattype;

    /**
     * 充值方式,1、银行卡2、微信3、支付宝
     */
    private Integer rechargeChargetype;

    /**
     * 收款银行卡账号,
     */
    private String rechargeReceivebkaccount;

    /**
     * 充值银行卡账号,
     */
    private String rechargeSponsbkaccount;

    /**
     * 充值时间,
     */
    private Date rechargeTime;

    /**
     * 充值状态,1 可用;2 不可用
     */
    private Integer rechargeStatus;

    /**
     * 套餐ID,
     */
    private String rechargeMealid;

    /**
     * 线下充值流水id,
     */
    private String rechargeOfflinerechargeid;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建者id
     */
    private String createId;

    /**
     * 创建者姓名
     */
    private String createName;

    private String rechargeTradeId;

    /**
     * 修改者id
     */
    private String updateId;

    /**
     * 修改者姓名
     */
    private String updateName;

    /**
     * 修改者时间
     */
    private Date updateDatetime;

    /**
     * 数据状态:0=正常数据;1=逻辑删除
     */
    private Integer status;

}