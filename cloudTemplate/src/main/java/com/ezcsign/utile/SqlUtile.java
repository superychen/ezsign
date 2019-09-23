package com.ezcsign.utile;


import com.ezcsign.entity.AaaaTest;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * describe:
 * 实体类 转sql
 *
 * @author donting
 * @date 2019/07/23
 */
public class SqlUtile {

    /**
     * 保存一个实体，null属性也会保存
     *
     * @param record
     * @return
     */
    public static String insert(Object record) {
        String sql = "insert into ?(?) values(?);";
        try {
            Class userCla = record.getClass();
            String tableName = ToHump.tableTransfrom(userCla.getSimpleName());
            sql = sql.replaceFirst("\\?", tableName);
            Field[] fs = userCla.getDeclaredFields();
            String tableField = "";
            String tableValue = "";
            for (Field f : fs) {
                f.setAccessible(true); //设置些属性是可以访问的
                Object val = null;//得到此属性的值

                val = f.get(record);

                tableField += "`" + ToHump.humpTostring(f.getName()) + "`,";
                String type = f.getType().toString();//得到此属性的类型 class java.lang.String
                if (val == null) {
                    tableValue += val + ",";
                } else {
                    tableValue += "'" + val + "',";
                }
            }
            sql = sql.replaceFirst("\\?", tableField.substring(0, tableField.length() - 1));
            sql = sql.replaceFirst("\\?", tableValue.substring(0, tableValue.length() - 1));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return sql;
    }
    /**
     * 保存一个实体，null属性不会保存
     *
     * @param record
     * @return
     */
    public static String insertSelective(Object record) {
        String sql = "insert into ?(?) values(?);";
        try {
            Class userCla = record.getClass();
            String tableName = ToHump.tableTransfrom(userCla.getSimpleName());
            sql = sql.replaceFirst("\\?", tableName);
            Field[] fs = userCla.getDeclaredFields();
            String tableField = "";
            String tableValue = "";
            for (Field f : fs) {
                //设置些属性是可以访问的
                f.setAccessible(true);
                //得到此属性的值
                Object val = null;

                val = f.get(record);

                if (val == null) {
                    continue;
                }
                tableField += "`" + ToHump.humpTostring(f.getName()) + "`,";
                //得到此属性的类型 class java.lang.String
                String type = f.getType().toString();
                if (val == null) {
                    tableValue += val + ",";
                } else {
                    tableValue += "'" + val + "',";
                }
            }
            sql = sql.replaceFirst("\\?", tableField.substring(0, tableField.length() - 1));
            sql = sql.replaceFirst("\\?", tableValue.substring(0, tableValue.length() - 1));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return sql;
    }
    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     */
    public static String delete(Object record) {
        String sql = "delete  from ? where ?;";
        try {
            Class userCla = record.getClass();
            String tableName = ToHump.tableTransfrom(userCla.getSimpleName());
            sql = sql.replaceFirst("\\?", tableName);
            Field[] fs = userCla.getDeclaredFields();
            String where = "";
            for (Field f : fs) {
                //设置些属性是可以访问的
                f.setAccessible(true);
                //得到此属性的值
                Object val = null;

                val = f.get(record);

                if (val != null) {
                    where = where + " `" + ToHump.humpTostring(f.getName()) + "`='" + val + "' and";
                }
            }
            sql = sql.replaceFirst("\\?", where.substring(0, where.length() - 3));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return sql;
    }
    public static String select(Object record) {
        String sql = "select *  from ? where ?;";
        try {
            Class userCla = record.getClass();
            String tableName = ToHump.tableTransfrom(userCla.getSimpleName());
            sql = sql.replaceFirst("\\?", tableName);
            Field[] fs = userCla.getDeclaredFields();
            String where = "";
            for (Field f : fs) {
                //设置些属性是可以访问的
                f.setAccessible(true);
                //得到此属性的值
                Object val = null;
                val = f.get(record);

                if (val != null) {
                    where = where + " `" + ToHump.humpTostring(f.getName()) + "`='" + val + "' and";
                }
            }
            if (where.length() > 1) {
                sql = sql.replaceFirst("\\?", where.substring(0, where.length() - 3));
            } else {
                sql = "select *  from " + tableName;
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return sql;

    }
    /**
     * @param record 更新值
     * @param where  条件
     * @return
     * @throws IllegalAccessException
     */
    public static String update(Object record, Object where) {
        String sql = "UPDATE ? SET ? where ?;";
        if (!record.getClass().getName().equals(where.getClass().getName())) {
            throw new RuntimeException("条件与更新值类型不一致:record:" + record.getClass().getName() + "--where:" + where.getClass().getName());
        }
        try {
            Class userCla = record.getClass();
            String tableName = ToHump.tableTransfrom(userCla.getSimpleName());
            sql = sql.replaceFirst("\\?", tableName);
            Field[] fs = userCla.getDeclaredFields();
            String whereSql = "";
            String set = "";
            for (Field f : fs) {
                //设置些属性是可以访问的
                f.setAccessible(true);
                //得到此属性的值
                Object val = null;

                val = f.get(record);

                if (val != null) {
                    set = set + "`" + ToHump.humpTostring(f.getName()) + "`='" + val + "',";
                }
            }
            sql = sql.replaceFirst("\\?", set.substring(0, set.length() - 1));
            userCla = where.getClass();
            fs = userCla.getDeclaredFields();
            for (Field f : fs) {
                //设置些属性是可以访问的
                f.setAccessible(true);
                //得到此属性的值
                Object val = f.get(where);
                if (val != null) {
                    whereSql = whereSql + " `" + ToHump.humpTostring(f.getName()) + "`='" + val + "' and";
                }
            }
            sql = sql.replaceFirst("\\?", whereSql.substring(0, whereSql.length() - 3));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return sql;
    }


    public static String dbUpdate(Object record, Object where) {
        String sql = "UPDATE ? SET ? where ?;";
        if (!record.getClass().getName().equals(where.getClass().getName())) {
            throw new RuntimeException("条件与更新值类型不一致:record:" + record.getClass().getName() + "--where:" + where.getClass().getName());
        }
        try {
            Class userCla = record.getClass();
            String tableName = ToHump.tableTransfrom(userCla.getSimpleName());
            sql = sql.replaceFirst("\\?", tableName);
            Field[] fs = userCla.getDeclaredFields();
            String whereSql = "";
            String set = "";
            for (Field f : fs) {
                //设置些属性是可以访问的
                f.setAccessible(true);
                //得到此属性的值
                Object val = null;

                val = f.get(record);

                if (val != null) {
//                    set += ":"+ToHump.humpTostring(f.getName())+",";
                    set = set + "`" + ToHump.humpTostring(f.getName()) + "`=:" + ToHump.humpTostring(f.getName()) + ",";
                }
            }
            sql = sql.replaceFirst("\\?", set.substring(0, set.length() - 1));
            userCla = where.getClass();
            fs = userCla.getDeclaredFields();
            for (Field f : fs) {
                //设置些属性是可以访问的
                f.setAccessible(true);
                //得到此属性的值
                Object val = f.get(where);
                if (val != null) {
                    whereSql = whereSql + " `" + ToHump.humpTostring(f.getName()) + "`=:" + ToHump.humpTostring(f.getName()) + " and";
//                    whereSql = whereSql + " `" + ToHump.humpTostring(f.getName()) + "`='" + val + "' and";
                }
            }
            sql = sql.replaceFirst("\\?", whereSql.substring(0, whereSql.length() - 3));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return sql;
    }
    public static String dbInsert(Object record) {
        String sql = "insert into ?(?) values(?);";
        try {
            Class userCla = record.getClass();
            String tableName = ToHump.tableTransfrom(userCla.getSimpleName());
            sql = sql.replaceFirst("\\?", tableName);
            Field[] fs = userCla.getDeclaredFields();
            String tableField = "";
            String tableValue = "";
            for (Field f : fs) {
                f.setAccessible(true); //设置些属性是可以访问的
                Object val = null;//得到此属性的值

                val = f.get(record);

                tableField += "`" + ToHump.humpTostring(f.getName()) + "`,";
                String type = f.getType().toString();//得到此属性的类型 class java.lang.String
                if (val == null) {
                    tableValue += val + ",";
                } else {
                    tableValue += ":" + ToHump.humpTostring(f.getName()) + ",";
                }
            }
            sql = sql.replaceFirst("\\?", tableField.substring(0, tableField.length() - 1));
            sql = sql.replaceFirst("\\?", tableValue.substring(0, tableValue.length() - 1));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return sql;
    }
    public static String dbDelete(Object record) {
        String sql = "delete  from ? where ?;";
        try {
            Class userCla = record.getClass();
            String tableName = ToHump.tableTransfrom(userCla.getSimpleName());
            sql = sql.replaceFirst("\\?", tableName);
            Field[] fs = userCla.getDeclaredFields();
            String where = "";
            for (Field f : fs) {
                //设置些属性是可以访问的
                f.setAccessible(true);
                //得到此属性的值
                Object val = null;

                val = f.get(record);

                if (val != null) {
                    where = where + " `" + ToHump.humpTostring(f.getName()) + "`=:" + ToHump.humpTostring(f.getName()) + " and";
                }
            }
            sql = sql.replaceFirst("\\?", where.substring(0, where.length() - 3));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return sql;
    }
    public static String dbSelect(Object record) {
        String sql = "select *  from ? where ?;";
        try {
            Class userCla = record.getClass();
            String tableName = ToHump.tableTransfrom(userCla.getSimpleName());
            sql = sql.replaceFirst("\\?", tableName);
            Field[] fs = userCla.getDeclaredFields();
            String where = "";
            for (Field f : fs) {
                //设置些属性是可以访问的
                f.setAccessible(true);
                //得到此属性的值
                Object val = null;
                val = f.get(record);

                if (val != null) {
                    where = where + " `" + ToHump.humpTostring(f.getName()) + "`=:" + ToHump.humpTostring(f.getName()) + " and";
                }
            }
            if (where.length() > 1) {
                sql = sql.replaceFirst("\\?", where.substring(0, where.length() - 3));
            } else {
                sql = "select *  from " + tableName;
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return sql;

    }

    public static void main(String[] args) {
        AaaaTest aaaaTest = new AaaaTest();
        aaaaTest.setName("[[[[");
        aaaaTest.setPas("ppp");
        System.out.println(dbSelect(aaaaTest));
    }

}
