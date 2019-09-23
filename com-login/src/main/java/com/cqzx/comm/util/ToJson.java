package com.cqzx.comm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import sun.rmi.runtime.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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


        //如果获取出来的值为空，那么就直接抛出异常
        if(StringUtils.isBlank(o)){
            throw new ZxException(ExceptionEnums.TYPE_CONVERT_ERROR);
        }
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
//        System.out.println("yc = " + yc);
        return yc;
    }

    // net.sf.json
    public final static void convert(Object json) {
        if (json instanceof JSONArray) {
            JSONArray arr = (JSONArray) json;
            for (Object obj : arr) {
                convert(obj);
            }
        } else if (json instanceof JSONObject) {
            JSONObject jo = (JSONObject) json;
            Set<String> keys = jo.keySet();
            String[] array = keys.toArray(new String[keys.size()]);
            for (String key : array) {
                Object value = jo.get(key);
                String[] key_strs = key.split("_");
                if (key_strs.length > 1) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < key_strs.length; i++) {
                        String ks = key_strs[i];
                        if (!"".equals(ks)) {
                            if (i == 0) {
                                sb.append(ks);
                            } else {
                                int c = ks.charAt(0);
                                if (c >= 97 && c <= 122) {
                                    int v = c - 32;
                                    sb.append((char) v);
                                    if (ks.length() > 1) {
                                        sb.append(ks.substring(1));
                                    }
                                } else {
                                    sb.append(ks);
                                }
                            }
                        }
                    }
                    jo.remove(key);
                    jo.put(sb.toString(), value);
                }
                convert(value);
            }
        }
    }

    public final static Object convert(String json) {
        Object obj = JSON.parse(json);
        convert(obj);
        return obj;
    }

}
