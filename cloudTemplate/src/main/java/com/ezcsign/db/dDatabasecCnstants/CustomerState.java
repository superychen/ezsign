package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * customer（账号信息表）
 *
 * 签署状态
 * 1 正常使用
 * 2 冻结
 * 3 注销
 * @author donting
 * @date 2019/07/18
 */
public enum CustomerState {
    One(1, "正常使用"),
    Two(2, "冻结"),
    Three(3,"注销");
    private String comment;
    private int value;

    CustomerState(int value, String comment) {
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
