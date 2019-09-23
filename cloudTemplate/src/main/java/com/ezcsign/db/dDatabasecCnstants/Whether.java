package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * 是否
 *      1 是
 *      2 否
 * @author donting
 * @date 2019/07/18
 */
public enum  Whether {
    One(1, "是"),
    Two(2, "否");
    private String comment;
    private int value;

    Whether(int value, String comment) {
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
