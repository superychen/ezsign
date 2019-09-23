package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * perosoninfo（个人信息表）
 *
 * 自动保存联系人
 * 1 勾选保存
 * 2 不保存
 * @author donting
 * @date 2019/07/18
 */
public enum MemberinfoCheckbox {
    One(1, "勾选保存"),
    Two(2, "不保存");
    private String comment;
    private int value;

    MemberinfoCheckbox(int value, String comment) {
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
