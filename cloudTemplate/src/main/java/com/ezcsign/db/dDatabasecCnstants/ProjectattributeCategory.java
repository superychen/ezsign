package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * projectattribute（项目属性表）
 *
 * 分类方式
 * 0自动按企业建立
 * 1.区域
 * 2.行业
 * 3.手动分类
 *
 * @author donting
 * @date 2019/07/18
 */
public enum ProjectattributeCategory {
    Zero(1, "0自动按企业建立"),
    One(1, "区域"),
    Two(2, "行业"),
    Three(3, "手动分类");
    private String comment;
    private int value;

    ProjectattributeCategory(int value, String comment) {
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
