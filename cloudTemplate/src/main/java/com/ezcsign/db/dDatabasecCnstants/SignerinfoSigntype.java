package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * signerinfo(签约人信息)
 *
 * 签章形式
 * 1 按单个位置签章
 * 2 每一页都盖签章
 * 3 只盖单数页
 * 4 只盖双数页
 * 5 骑缝章（单数页一个位置，双数页一个位置）
 * @author donting
 * @date 2019/07/18
 */
public enum SignerinfoSigntype {

    One(1,"按单个位置签章"),
    Tow(2,"每一页都盖签章"),
    Three(3,"只盖单数页"),
    Four(4,"只盖双数页"),
    Five(5,"骑缝章（单数页一个位置，双数页一个位置）");
    private String comment;
    private int value;
    SignerinfoSigntype(int value, String comment) {
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
