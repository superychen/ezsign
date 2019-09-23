package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * contract（合同文件和附件）
 * 状态
 *
 * 1 可用
 * 2 不可用
 * @author donting
 * @date 2019/07/18
 */
public enum ContractStatus {
    One(1, "可用"),
    Two(2, "不可用");
    private String comment;
    private int value;

    ContractStatus(int value, String comment) {
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
