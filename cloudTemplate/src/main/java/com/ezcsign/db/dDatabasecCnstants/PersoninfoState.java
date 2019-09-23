package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * perosoninfo（个人信息表）
 *
 * 用户状态
 * 0未认证
 * 1已提交认证未审核
 * 2认证通过
 * 5冻结
 * 6注销
 * @author donting
 * @date 2019/07/18
 */
public enum PersoninfoState {
    Zero(0,"0未认证"),
    One(1, "1已提交认证未审核"),
    Two(2, "2认证通过"),
    Five(5,"5冻结"),
    Six(6,"6注销");
    private String comment;
    private int value;

    PersoninfoState(int value, String comment) {
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
