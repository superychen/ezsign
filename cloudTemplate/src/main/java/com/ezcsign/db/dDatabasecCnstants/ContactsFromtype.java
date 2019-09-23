package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * contacts（联系人表）
 * <p>
 * 文件类型
 * 1 企业组织
 * 2 自动保存
 * 3 分享
 *
 * @author donting
 * @date 2019/07/18
 */
public enum ContactsFromtype {
    One(1, "企业组织"),
    Two(2, "自动保存"),
    Three(3, "分享");
    private String comment;
    private int value;

    ContactsFromtype(int value, String comment) {
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
