package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * companyinfo（企业信息表）
 *
 * 企业权限
 *1 不允许查看企业联系人
 * 2 允许查看本部门联系人
 * 3 允许查看企业内所有联系人
 * @author donting
 * @date 2019/07/18
 */
public enum CompanyinfoContactjurisdiction5 {
    One(1, "不允许查看企业联系人"),
    Two(2, "允许查看本部门联系人"),
    Three(3, "允许查看企业内所有联系人");
    private String comment;
    private int value;

    CompanyinfoContactjurisdiction5(int value, String comment) {
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
