package com.cqzx.comm.util;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Slf4j
public class SQLStringHelper<T> {

    private EntityHelper entityHelper = new EntityHelper();

    /***
     * 创建insert语句
     * @param entity
     * @return
     */
    public String createinsert(T entity){
        String sql= "insert into ";
        String column = "";//列
        String c_values = "";//对应的列的值
        List<Map<String,Object>> list = entityHelper.getFiledInfo(entity);

        sql += list.get(0).get("obj_name").toString() + " ";
        for (int i = 0; i < list.size(); i++) {
            //约定id在数据库中生成
//            if(list.get(i).get("f_name").toString() == "id")
//                i++;
            if(list.get(i).get("f_value") != null){
                column += list.get(i).get("f_name")+ ",";
                c_values += "'" + list.get(i).get("f_value") + "',";
            }
        }
        sql += "("+column.substring(0,column.length()-1) +") values (" +
                c_values.substring(0,c_values.length()-1)+ ");";
        return sql;
    }


    /**
     * 拼裝Delete SQL語句
     *
     * @param entity
     * @param param 根据这个选择要删除的where条件参数
     * @param inputParam 输入的的参数类型为任意的参数
     * @return
     */
    @SuppressWarnings("unchecked")
    public String createDelete(T entity,String param,T inputParam){
        String sql = "delete from ";
        List<Map<String,Object>> list = entityHelper.getFiledInfo(entity);

        //输出得到的list
//        System.out.println(Collections.unmodifiableList(list));

        sql += list.get(0).get("obj_name").toString() + " where "+ param +"= " + inputParam + ";";
        return sql;
    }

    /**
     * 查找
     *
     * @param entity
     * @return
     */
    @SuppressWarnings("unchecked")
    public String createSelect(T entity){
        String sql = "select * from ";
        String column = "";
        List<Map<String,Object>> list = entityHelper.getFiledInfo(entity);

        sql += list.get(0).get("obj_name").toString() + " where ";
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).get("f_value") != null){
                column += list.get(i).get("f_name") + " like %"+list.get(i).get("f_value") + "% or ";
            }
        }
        return sql += column.substring(0,column.length()-4) + ";";
    }

    /**
     * 根据id更新实体数据
     *
     * @param entity
     * @param id
     *            实体id
     * @return 拼装好的 Update SQL语句
     */
    @SuppressWarnings("unchecked")
    public String createUpdate(T entity, int id){
        String sql = "update ";
        String column = "";//列
        List<Map<String,Object>> list = entityHelper.getFiledInfo(entity);

        sql += list.get(0).get("obj_name").toString() + " set ";
        for (int i = 0; i < list.size(); i++) {
            // id不能修改
            if (list.get(i).get("f_value") != null) {
                column += list.get(i).get("f_name") + "='"
                        + list.get(i).get("f_value")+"',";
            }
        }
        return sql += column.substring(0,column.length()-1) + " where id = '" + id +"'";
    }
}
