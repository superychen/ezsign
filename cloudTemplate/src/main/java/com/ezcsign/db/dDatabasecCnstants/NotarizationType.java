package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * notarization（公证信息表）
 * 公证类型
 *
 * 1 合同文件公证
 * 2 签约过程公证
 * 3 合同文件的司法鉴定
 * @author donting
 * @date 2019/07/18
 */
public enum NotarizationType {
    One(1,"合同文件公证"),
    Tow(2,"签约过程公证"),
    Three(3,"合同文件的司法鉴定");
    private String comment;
    private int value;

    NotarizationType(int value, String comment) {
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
