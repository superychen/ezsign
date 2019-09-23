package com.ezcsign.utile;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author icechen1219
 * @date 2019/02/04
 */
public class StringUtil {
    /**
     * 定义下划线
     */
    public static final char UNDERLINE = '_';
    public static final char LOWER_CHAR_A = 97;
    public static final char LOWER_CHAR_Z = 122;
    public static final String DEFAULT_CHAR_SET = "utf-8";

    private static Logger logger = LoggerFactory.getLogger(StringUtil.class);


    /**
     * 首字母小写
     * @param old
     * @return
     */
    public static String lowerFirstChar(String old) {
        char first = old.charAt(0);
        StringBuilder sb = new StringBuilder(old);
        sb.replace(0, 1, String.valueOf(Character.toLowerCase(first)));
        return sb.toString();
    }

    /**
     * 首字母大写
     *
     * @param old
     * @return
     */
    public static String upperFirstChar(String old) {
        char[] chars = old.toCharArray();
        if (chars[0] >= LOWER_CHAR_A && chars[0] <= LOWER_CHAR_Z) {
            chars[0] -= 32;
        }
        return String.valueOf(chars);
    }

    /**
     * String为空判断
     * @param str 需判断字符串
     * @return true:为空 false:不为空
     */
    public static boolean isStrEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * String不为空判断
     *
     * @param str 需判断字符串
     * @return true:不为空 false:为空
     */
    public static boolean isStrNotEmpty(String str) {
        return str != null || str.trim().length() != 0;
    }

    /**
     * 驼峰转下划线工具
     *
     * @param param 需要转换的字符串
     * @return 转换好的小写字符串
     */
    public static String camelToUnderline(String param) {
        if (isStrNotEmpty(param)) {
            int len = param.length();
            StringBuilder sb = new StringBuilder(len);
            sb.append(Character.toLowerCase(param.charAt(0)));

            for (int i = 1; i < len; ++i) {
                char c = param.charAt(i);
                if (Character.isUpperCase(c)) {
                    sb.append(UNDERLINE);
                }
                sb.append(Character.toLowerCase(c));
            }

            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * 下划线转驼峰工具
     *
     * @param param 需要转换的字符串
     * @return 转换好的驼峰字符串
     */
    public static String underlineToCamel(String param) {
        if (isStrNotEmpty(param)) {
            int len = param.length();
            param = param.toLowerCase();
            StringBuilder sb = new StringBuilder(len);

            for (int i = 0; i < len; ++i) {
                char c = param.charAt(i);
                if (c == 95) {
                    ++i;
                    if (i < len) {
                        sb.append(Character.toUpperCase(param.charAt(i)));
                    }
                } else {
                    sb.append(c);
                }
            }

            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * @param str
     * @return
     */
    public static String formatLike(String str) {
        if (isStrNotEmpty(str)) {
            return "%" + str + "%";
        } else {
            return null;
        }
    }

    /**
     * 在字符串两周添加''
     *
     * @param param 字符串
     * @return 处理后的字符串
     */
    public static String addSingleQuotes(String param) {
        return "\'" + param + "\'";
    }
}
///:StringUtil.java
