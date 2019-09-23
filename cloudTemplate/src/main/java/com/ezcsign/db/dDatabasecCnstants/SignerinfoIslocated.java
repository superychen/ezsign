package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * signerinfo(签约人信息)
 * 是否指定签章位置
 *  1 发起时候已指定
 * 2 用户自己拖动
 * @author donting
 * @date 2019/07/18
 */
public enum SignerinfoIslocated {
    One(1, "发起时候已指定"),
    Two(2, "用户自己拖动");
    private String comment;
    private int value;

    SignerinfoIslocated(int value, String comment) {
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
