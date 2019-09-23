package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * comfiles（企业档案表）
 *状态
 *1 可查阅
 * 2  封存
 * 4  销毁
 * @author donting
 * @date 2019/07/18
 */
public enum ComfilesStatus {

    One(1,"可查阅"),
    Tow(2,"封存"),
    Four(4,"销毁");

    private String comment;
    private int value;
    ComfilesStatus(int value, String comment) {
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
