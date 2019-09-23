package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * signerinfo(签约人信息)
 *
 * 签署设备形式
 *  1 易通签平台签署
 * 2 使用key签署
 * @author donting
 * @date 2019/07/18
 */
public enum SignerinfoSgndevice {
    One(1, "易通签平台签署"),
    Two(2, "使用key签署");
    private String comment;
    private int value;

    SignerinfoSgndevice(int value, String comment) {
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
