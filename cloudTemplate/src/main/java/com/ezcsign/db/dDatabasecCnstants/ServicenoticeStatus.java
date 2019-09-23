package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * servicenotice(系统提醒)
 * 状态
 * 1 提醒
 * 2 已读
 *
 * @author donting
 * @date 2019/07/18
 */
public enum ServicenoticeStatus {

    One(1, "提醒"),
    Tow(2, "已读");

    private String comment;
    private int value;

    ServicenoticeStatus(int value, String comment) {
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
