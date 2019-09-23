package com.ezcsign.utile;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ezcsign.controller.Peron;

import java.util.*;

public class Json {
    public static <T> List<T> listToJson(List<Map<String,Object>> list, Class c){

        String jsonString = JSONObject.toJSONString(list);
        List<T> person = JSONArray.parseArray(jsonString,c);
        return person;
    }
    public static <T> T mapToJson(Map<String,Object> list, Class<T> c){

        String jsonString = JSONObject.toJSONString(list);
        return JSONObject.parseObject(jsonString,c);
    }

    public static void main(String[] args) {
        Map<String,Object> map=new HashMap<>();
        map.put("name","pp");
        map.put("age","12");
        System.out.println(mapToJson(map, Peron.class).toString());
    }
}
