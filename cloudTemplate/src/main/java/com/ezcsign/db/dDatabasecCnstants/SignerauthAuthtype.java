package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * signerauth（签约用户身份认证）
 * 认证类型
 *
 * 1短信
 * 2人脸识别
 * 3银行卡三要素
 * 4银行卡四要素
 * @author donting
 * @date 2019/07/18
 */
public enum SignerauthAuthtype {
    One(1, "短信"),
    Two(2, "人脸识别"),
    Three(3,"银行卡三要素"),
    Four(4,"银行卡四要素");
    private String comment;
    private int value;

    SignerauthAuthtype(int value, String comment) {
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
