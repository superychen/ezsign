package com.ezcsign.utile;

public class ToHump {

    /**
     * _转驼峰
     *
     * @param s
     * @return
     */
    public static String stringToHump(String s) {

        while (s.indexOf("_") > -1) {
            int i = s.indexOf("_");
            char c = s.charAt(i + 1);
            String a = (c + "").toUpperCase();
            s = s.replaceAll(s.substring(i, i + 2), a);
        }
        return s;
    }

    /**
     * 驼峰 转_
     *
     * @param s
     * @return
     */
    public static String humpTostring(String s) {

        int leng = s.length();
        for (int i = leng - 1; i >= 0; i--) {
            if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {
                s = s.replace(s.charAt(i) + "", "_" + (char) (s.charAt(i) + 32));
            }
        }

        return s;
    }

    /**
     *
     * @param className
     * @return
     */
    public static String tableTransfrom(String className) {
        className = className.replace(className.charAt(0), (char) (className.charAt(0) + 32));
        return humpTostring(className);

    }


    public static void main(String[] arg) {
        System.out.println(tableTransfrom("UserName"));
    }
}
