package com.cqzx.comm.util;

import com.google.common.base.CaseFormat;

/**
 * @Description: 转换为json的工具类
 * @Author: cqyc
 * @Date: 2019-7-26
 */
public class ToJson {

    /**
     * 将字符串不是json格式，而是customer=123这种格式转换为json
     * @param o
     * @return
     */
    public static String stringToJson(String o){
        o = o.replaceAll("=",":");
        o = o.replaceAll("\\{","");
        o = o.replaceAll("\\}","");
        o = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,o);
        //截取字符串
        String[] res = o.split(",");
        String yc="";
        for (String re : res) {
            if(!re.contains("null")){
                yc += "\"" + re.trim().replaceFirst(":","\":\"") + "\",";
            }
        }
        yc = "{"+yc+"}";
        yc = yc.replace(",}","}");
        System.out.println("yc = " + yc);
        return yc;
    }
}
