package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * signgrant（合同查阅授权表）
 * <p>
 *
 * 1 发起人授权
 * 2 签署人授权
 * 3 审批人授权
 * 4 抄送人授权
 * 5 管理员授权
 *
 * @author donting
 * @date 2019/07/18
 */
public enum SigngrantGranttype {
    One(1, "发起人授权"),
    Two(2, "签署人授权"),
    Three(3, "审批人授权"),
    Four(4, "抄送人授权"),
    Five(5,"管理员授权");
    private String comment;
    private int value;

    SigngrantGranttype(int value, String comment) {
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
