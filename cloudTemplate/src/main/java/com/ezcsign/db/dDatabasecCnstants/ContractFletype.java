package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * contract（合同文件和附件）
 *
 * 文件类型
 * 1 pdf
 * 2 word
 * 3 ofd
 * 4 图片
 * @author donting
 * @date 2019/07/18
 */
public enum ContractFletype {
    One(1,"pdf"),
    Tow(2,"word"),
    Three(3,"ofd"),
    Four(4,"图片");
    private String comment;
    private int value;

    ContractFletype(int value, String comment) {
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
