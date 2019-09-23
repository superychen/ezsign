package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * recharge（用户充值表）
 * 操作类型
 *
 * 1 线上充值
 * 2 线下充值
 * @author donting
 * @date 2019/07/18
 */
public enum RechargeOperattype {
    One(1, "线上充值"),
    Two(2, "线下充值");
    private String comment;
    private int value;

    RechargeOperattype(int value, String comment) {
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
