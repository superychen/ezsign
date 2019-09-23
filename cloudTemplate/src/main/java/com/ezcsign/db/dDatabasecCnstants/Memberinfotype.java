package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * memberinfo（成员节点表）
 *
 * 节点类型
 * 0   根节点公司
 * 1   子公司账户
 * 2   子成员（部门账户或个人账户）
 * 3   子部门（不对应账户）
 *
 * @author donting
 * @date 2019/07/18
 */
public enum Memberinfotype {
    Zero(0, "根节点公司"),
    One(1, "子公司账户"),
    Two(2, "子成员（部门账户或个人账户）"),
    Three(3, "子部门（不对应账户");
    private String comment;
    private int value;

    Memberinfotype(int value, String comment) {
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
