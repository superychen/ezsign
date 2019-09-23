package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * recharge（用户充值表）
 * 充值方式
 *
 * 1、银行卡2、微信3、支付宝
 * @author donting
 * @date 2019/07/18
 */
public enum RechargeChargetype {
    One(1, "银行卡2"),
    Two(2, "微信"),
    Three(3, "支付宝");
    private String comment;
    private int value;

    RechargeChargetype(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }


    public int getValue() {
        return value;
    }

}
