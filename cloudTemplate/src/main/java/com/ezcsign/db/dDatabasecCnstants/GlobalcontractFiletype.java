package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * globalcontract(通用合同模板)
 *模板文件类型
 *1 pdf
 * 2 word
 * 3 ofd
 * 4 jpg
 * @author donting
 * @date 2019/07/18
 */
public enum GlobalcontractFiletype {

    One(1,"pdf"),
    Tow(2,"word"),
    Three(3,"ofd"),
    Four(4,"jpg");

    private String comment;
    private int value;
    GlobalcontractFiletype(int value, String comment) {
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
