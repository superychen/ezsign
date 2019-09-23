package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * memberinfo（成员节点表）
 *
 * 节点类型
 * 0 成员
 * 1 超级管理员
 * 2 审批管理员
 * 3 档案管理员
 * 4 系统管理员
 * 5 部门（不拥有独立账号）
 *
 * @author donting
 * @date 2019/07/18
 */
public enum MemberinfoRole {
    Zero(0, "成员"),
    One(1, "超级管理员"),
    Two(2, "审批管理员"),
    Three(3, "档案管理员"),
    Four(4, "系统管理员"),
    Five(5, "部门（不拥有独立账号");
    private String comment;
    private int value;

    MemberinfoRole(int value, String comment) {
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
