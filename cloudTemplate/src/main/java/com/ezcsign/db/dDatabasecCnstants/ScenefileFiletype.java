package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * scenefile（签约现场文件）
 * 文件类型
 *
 * 1.图片
 * 2.视频文件
 * @author donting
 * @date 2019/07/18
 */
public enum ScenefileFiletype {
    One(1,"图片"),
    Tow(2,"视频文件");
    private String comment;
    private int value;

    ScenefileFiletype(int value, String comment) {
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
