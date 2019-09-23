package com.cqzx.comm.util;

import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 实体类转换为sql(插入方法)
 * @Author: cqyc
 * @Date:
 */
@Slf4j
public class EntityHelper {


    /**
     * 根据属性名获取属性值
     *
     */
    protected Object getFieldValueByName(String fieldName,Object o){

        try {
            //将表名的第第一个字母全部变为大写
            String firstLetter = fieldName.substring(0,1).toUpperCase();
            //将表名拼接成get+表名
            String getter = "get" + firstLetter + fieldName.substring(1);
            //将对应的object对应对应的类的getter方法，getUser
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception  e) {
            log.info(e.getMessage(),e);
            return null;
        }
    }

    /**
     * 类名(obj_name)获取属性类型(f_type)，属性名(f_name)，属性值(f_value)的map组成的list
     * unused屏蔽
     */
    @SuppressWarnings("unused")//表示该属性在方法或类中没有使用。添加此注解可以去除属性上的黄色警告！！！
    protected List getFiledInfo(Object o){
        String obj_name = o.getClass().getSimpleName();
        System.out.println("obj_name =====>"+obj_name);
        Field[] fields = o.getClass().getDeclaredFields();

//      String[] fieldNames = new String[fields.length];
        
        List<Map> list = new ArrayList<>();
        Map<String ,Object> infoMap;

        for (int i = 0; i < fields.length; i++) {
            infoMap = new HashMap<>();

            infoMap.put("obj_name",CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,obj_name));
            infoMap.put("f_type",fields[i].getType().toString());
            //将类型的属性名字转换为对应的数据库的类型
            infoMap.put("f_name",CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,fields[i].getName()));
            infoMap.put("f_value",getFieldValueByName(fields[i].getName(),o));

            list.add(infoMap);
        }
        
//        list.forEach(i-> System.out.println("i = " + i));
        return list;
    }
}
