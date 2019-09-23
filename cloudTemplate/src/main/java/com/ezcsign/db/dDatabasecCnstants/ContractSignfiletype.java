package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * contract（合同文件和附件）
 * 签署的文件类型
 *
 * 1 合同源文件
 * 2 附件（不需要签署的文件）
 * @author donting
 * @date 2019/07/18
 */
public enum ContractSignfiletype {
    One(1,"合同源文件"),
    Tow(2," 附件（不需要签署的文件）");
    private String comment;
    private int value;

    ContractSignfiletype(int value, String comment) {
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
