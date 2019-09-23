package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * comfiles（企业档案表）
 * <p>
 * 文件类型
 * 1 pdf
 * 2 word
 * 3 ofd
 * 4 Excel
 * 5 图片
 *
 * @author donting
 * @date 2019/07/18
 */
public enum ComfilesFiletype {
    One(1, "pdf"),
    Two(2, "word"),
    Three(3, "ofd"),
    Four(4, "Excel"),
    Five(5, "图片");
    private String comment;
    private int value;

    ComfilesFiletype(int value, String comment) {
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
