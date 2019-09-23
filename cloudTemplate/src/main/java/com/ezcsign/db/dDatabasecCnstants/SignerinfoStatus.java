package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * signerinfo(签约人信息)
 *
 * 签署状态
 * 1 未签署
 * 2 已签署
 * 3 拒签
 * @author donting
 * @date 2019/07/18
 */
public enum SignerinfoStatus {
    One(1, "未签署"),
    Two(2, "已签署"),
    Three(3,"拒签");
    private String comment;
    private int value;

    SignerinfoStatus(int value, String comment) {
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
