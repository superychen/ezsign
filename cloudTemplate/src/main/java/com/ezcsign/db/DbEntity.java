package com.ezcsign.db;

import com.ezcsign.utile.Json;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * describe:
 *
 * @author donting
 * @date 2019/07/29
 */
public class DbEntity {
    int code;
    String msg;
    String sql;
    String dsql;
    int updateNum;
    Map<String, Object> params;
    List<Map<String, Object>> data;
    String radata;

    public void setDsql() {
        if (sql != null && this.params != null) {
            dsql=sql;
            for (String s : this.params.keySet()) {
                dsql = dsql.replaceAll(":" + s, "'" + this.params.get(s).toString() + "'");
            }
        }
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public String getRadata() {
        return radata;
    }

    public void setRadata(String radata) {
        this.radata = radata;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;

    }

    public Map<String, Object> getParams() {
        return params;
    }


    public List<Map<String, Object>> getData() {

        return data;
    }


    public int getUpdateNum() {
        return updateNum;
    }

    public void setUpdateNum(int updateNum) {
        this.updateNum = updateNum;
    }

    public <T> List<T> getData(Class entity) {
        try {
            return Json.listToJson(data, entity);
        } catch (Exception e) {
            throw new RuntimeException("data 不是[{}] 对象");
        }

    }

    public String getDsql() {
        return dsql;
    }


}
