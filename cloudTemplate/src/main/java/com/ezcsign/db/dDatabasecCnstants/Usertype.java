package com.ezcsign.db.dDatabasecCnstants;

public enum Usertype{

    One(1, "个人 personinfo的id"),
    Tow(2, "企业memberinfo的id"),
    Three(3, "api发起apiid");
    private String comment;
    private int value;

     Usertype(int value, String comment) {
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
