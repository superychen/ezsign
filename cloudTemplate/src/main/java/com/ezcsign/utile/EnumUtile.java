package com.ezcsign.utile;


/**
 * describe:
 *
 * @author donting
 * @date 2019/07/18
 */
public class EnumUtile {

    public static <T extends Enum<T>> T getDatabseStatic(String name, Class<T> enumType) {
        T e = Enum.valueOf(enumType, name);
        return e;
    }

    public static String toNumber(int i) {
        switch (i) {
            case 1:
                return "One";
            case 2:
                return "Tow";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
            case 10:
                return "Ten";
            case 11:
                return "Eleven";
            case 12:
                return "Twelve";
            case 13:
                return "Thirteen";
            default:
                return "数字错误";
        }
    }
}
