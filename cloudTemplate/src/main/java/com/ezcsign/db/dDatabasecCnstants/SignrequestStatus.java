package com.ezcsign.db.dDatabasecCnstants;

/**
 * describe:
 * signrequest(签约合同流水表)
 *
 * 签署状态
 * 1 未发起
 * 2 审批退回
 * 3 审批中
 * 4 审批通过未签署
 * 5 部分签署
 * 6 签署完毕
 * 7 用户拒签
 * 8 保全完毕
 * 9 公证完毕
 * 10 已过期
 * 11 回收站
 * 12 合同中止
 * @author donting
 * @date 2019/07/18
 */
public enum SignrequestStatus {

    One(1,"未发起"),
    Tow(2,"审批退回"),
    Three(3,"审批中"),
    Four(4,"审批通过未签署"),
    Five(5,"部分签署"),
    Six(6,"签署完毕"),
    Seven(7,"用户拒签"),
    Eight(8,"保全完毕"),
    Nine(9,"公证完毕"),
    Ten(10,"已过期"),
    Eleven(11,"回收站"),
    Twelve(12,"合同中止");

    private String comment;
    private int value;
    SignrequestStatus(int value, String comment) {
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
