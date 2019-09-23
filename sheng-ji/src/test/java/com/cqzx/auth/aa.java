package com.cqzx.auth;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description:
 * @Author:
 * @Date:
 */
public class aa {
    @Test
    public static void main(String[] args) {
        String sealinfoCreatername = "55";
        String sealinfoName = null;
        String sealinfoCreatetime = null;
        String sealinfoFiletype = "999sada";
        getMessge(sealinfoCreatername, sealinfoName, sealinfoCreatetime, sealinfoFiletype);
    }

    public static void getMessge(String sealName, String authorizer, String startTime, String endTime) {
        Map<String, Object> map = new TreeMap<>();
        map.put("seal_name", sealName);
        map.put("authorizer", authorizer);
        map.put("start_time", startTime);
        map.put("end_time", endTime);

        String where = " where ";
        String tableName = "";
        String selectObj = "*";
        String prefix = " and ";
        String suffix = "";

        tableName = "sealinfo";
        for (String s : map.keySet()) {
            if (map.get(s) == null || map.get(s).equals(" ")) {
//                map.remove(s);
                continue;
            } else {
                String k = s + " = '" + map.get(s) + "'";
                where += k;
                where += prefix;
            }
        }


        String sql = "select " + selectObj + " from " + tableName + " " + where;

        System.out.println(sql);


    }
}
