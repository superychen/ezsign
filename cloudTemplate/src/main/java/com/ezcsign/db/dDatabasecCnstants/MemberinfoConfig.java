package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * memberinfo（成员节点表）
 *
 * 通知
 * 1 短信和邮箱都通知
 * 2 短信
 * 3 邮箱
 * 4 都不通知
 *
 * @author donting
 * @date 2019/07/18
 */
public enum MemberinfoConfig {
    One(1, "短信和邮箱都通知"),
    Two(2, "短信"),
    Three(3, "邮箱"),
    Four(4, "都不通知");
    private String comment;
    private int value;

    MemberinfoConfig(int value, String comment) {
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
