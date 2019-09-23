package com.ezcsign.db;


import com.ezcsign.utile.ToHump;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * describe:
 *
 * @author donting
 * @date 2019/07/25
 */
public class DbParam {

    Map<String, Object> params;


    public DbParam(Object o) {
        params = new HashMap<>();
// 获取所有的字段
        try {
            Class c = o.getClass();
            boolean clAnnotation = c.isAnnotationPresent(Table.class);
            Field[] fields = o.getClass().getDeclaredFields();

            for (Field f : fields) {
                f.setAccessible(true);
                // 判断字段注解是否存在
                boolean annotationPresent2 = f.isAnnotationPresent(Column.class);
                boolean id = f.isAnnotationPresent(Id.class);
                Object val = null;
                val = f.get(o);
                if (val != null) {
                    params.put(ToHump.humpTostring(f.getName()), val.toString());
                }

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "DbParam{" +
                "params=" + params +
                '}';
    }
}
