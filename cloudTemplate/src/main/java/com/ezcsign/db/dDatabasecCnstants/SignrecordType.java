package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * signrecord（用户签约详情记录表）
 *
 * 1 注册
 * 2 认证
 * 3 发起签约
 * 4 签署认证
 * 5 合同签署
 * 6 合同保全
 * 7 上传公证处
 * 8 司法鉴定中心
 * 9 对接在线仲裁单位
 * @author donting
 * @date 2019/07/18
 */
public enum SignrecordType {

    One(1,"注册"),
    Tow(2,"认证"),
    Three(3,"发起签约"),
    Four(4,"签署认证"),
    Five(5,"合同签署"),
    Six(6,"合同保全"),
    Seven(7,"上传公证处"),
    Eight(8,"司法鉴定中心"),
    Nine(9,"对接在线仲裁单位");

    private String comment;
    private int value;
    SignrecordType(int value, String comment) {
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
