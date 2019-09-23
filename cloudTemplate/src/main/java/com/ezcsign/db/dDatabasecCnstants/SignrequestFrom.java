package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * signrequest(签约合同流水表)
 * 签约来源
 *
 * 1 saas
 * 2 api
 * 3 用户自认证调用
 * @author donting
 * @date 2019/07/18
 */
public enum SignrequestFrom {

    One(1,"saas"),
    Two(2,"Api"),
    Three(3,"用户自认证调用");

    private String comment;
    private int value;
    SignrequestFrom(int value, String comment) {
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
