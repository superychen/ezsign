package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * invoice（发票申请表）
 *发票类型
 *1 在线普票
 * 2 在线专票
 * 3 线下普票
 * 4 线下专票
 * @author donting
 * @date 2019/07/18
 */
public enum InvoiceType {

    One(1,"在线普票"),
    Tow(2,"在线专票"),
    Three(3,"线下普票"),
    Four(4,"线下专票");

    private String comment;
    private int value;
    InvoiceType(int value, String comment) {
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
