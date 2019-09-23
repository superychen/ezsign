package com.ezcsign.utile;


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
public class DbEntity {


    String tableName;
    String tableKey;
    String tableKeyValue;
    Map<String, Object> params;



    public DbEntity(Object o) {
        params=new HashMap<>();
// 获取所有的字段
        try {
            Class c = o.getClass();
            boolean clAnnotation = c.isAnnotationPresent(Table.class);
            if (clAnnotation) {
                Table table = (Table) c.getAnnotation(Table.class);
                tableName = table.name();
            } else {
                throw new RuntimeException(c + " 不是一个实体类");
            }
            Field[] fields = o.getClass().getDeclaredFields();

            for (Field f : fields) {
                f.setAccessible(true);
                // 判断字段注解是否存在
                boolean annotationPresent2 = f.isAnnotationPresent(Column.class);
                boolean id = f.isAnnotationPresent(Id.class);
                Object val = null;
                val = f.get(o);
                if (id) {
                    tableKey = ToHump.humpTostring(f.getName());
                    tableKeyValue= val.toString();
                }

                if(val!=null){
                    params.put(ToHump.humpTostring(f.getName()),val);
                }

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
    public String getTableKeyValue() {
        return tableKeyValue;
    }

    public void setTableKeyValue(String tableKeyValue) {
        this.tableKeyValue = tableKeyValue;
    }
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableKey() {
        return tableKey;
    }

    public void setTableKey(String tableKey) {
        this.tableKey = tableKey;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "DbEntity{" +
                "tableName='" + tableName + '\'' +
                ", tableKey='" + tableKey + '\'' +
                ", params=" + params +
                '}';
    }
}
