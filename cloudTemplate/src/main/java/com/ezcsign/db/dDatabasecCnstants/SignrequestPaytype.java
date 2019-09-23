package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * signrequest(签约合同流水表)
 *
 * 付款类型
 * 1 发起人付费
 * 2 签署人分摊
 * @author donting
 * @date 2019/07/18
 */
public enum SignrequestPaytype {
    One(1, "发起人付费"),
    Two(2, "签署人分摊");
    private String comment;
    private int value;

    SignrequestPaytype(int value, String comment) {
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
