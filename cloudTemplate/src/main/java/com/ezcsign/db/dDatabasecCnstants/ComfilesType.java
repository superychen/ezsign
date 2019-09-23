package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * comfiles（企业档案表）
 *节点类型
 *1 文件夹
 * 2 文件
 * @author donting
 * @date 2019/07/18
 */
public enum ComfilesType {

    One(1,"文件夹"),
    Tow(2,"文件");

    private String comment;
    private int value;
    ComfilesType(int value, String comment) {
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
