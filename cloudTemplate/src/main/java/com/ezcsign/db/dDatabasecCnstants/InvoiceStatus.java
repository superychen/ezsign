package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * invoice（发票申请表）
 *
 * 发票状态
 *
 * 0 未开票
 * 1 已开票
 * 2 未成功
 * 3 提交发票申请未开票
 * 4 开票中
 * @author donting
 * @date 2019/07/18
 */
public enum InvoiceStatus {
    zero(0,"未开票"),
    One(1,"已开票"),
    Tow(2,"未成功"),
    Three(3,"提交发票申请未开票"),
    Four(4,"开票中");
    private String comment;
    private int value;
    InvoiceStatus(int value, String comment) {
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
