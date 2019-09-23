package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * memberinfo（成员节点表）
 *
 * 节点状态
 * 1 有效
 * 2 冻结
 * 3 注销
 *
 * @author donting
 * @date 2019/07/18
 */
public enum MemberinfoStatus {
    One(1, "有效"),
    Two(2, "冻结"),
    Three(3, "注销");
    private String comment;
    private int value;

    MemberinfoStatus(int value, String comment) {
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
