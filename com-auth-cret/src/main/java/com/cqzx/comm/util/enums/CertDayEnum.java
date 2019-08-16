package com.cqzx.comm.util.enums;


import lombok.Getter;

@Getter
public enum CertDayEnum {

    ONE_DAY("天数",1),
    THREE_DAY("天数",3),
    FIVE_DAY("天数",5),
    SEVEN_DAY("天数",7),
    TEN_DAY("天数",10),
    FIFTEEN_DAY("天数",15),
    TWENTY_DAY("天数",20),
    THIRTY_DAY("天数",30),
    SIXTY_DAY("天数",60),
    NINETY_DAY("天数",90),
    ONE_HUNDRED_AND_EIGHTY_DAY("天数",180),
    THREE_HUNDRED_AND_SIXTY_DAY("天数",360),
    ;
    private String name;
    private Integer day;

    CertDayEnum(String name, Integer day) {
        this.name = name;
        this.day = day;
    }
}
