package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * companyinfo（企业信息表）
 *
 * 企业认证方式
 * 1 对公打款
 * 2 法人银行卡四要素
 * 3 授权书认证
 *
 * @author donting
 * @date 2019/07/18
 */
public enum CompanyinfoAuthtype {
    One(1, "对公打款"),
    Two(2, "法人银行卡四要素"),
    Three(3, "授权书认证");
    private String comment;
    private int value;

    CompanyinfoAuthtype(int value, String comment) {
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
