package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * signerauth（签约用户身份认证）
 *
 * 认证状态
 * 0 未做认证
 * 1 认证通过
 * 2 认证未通过
 * @author donting
 * @date 2019/07/18
 */
public enum SignerauthAuthstatus {
    One(1, "未做认证"),
    Two(2, "认证通过"),
    Three(3,"认证未通过");
    private String comment;
    private int value;

    SignerauthAuthstatus(int value, String comment) {
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
