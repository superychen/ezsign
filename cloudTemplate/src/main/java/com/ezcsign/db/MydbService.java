package com.ezcsign.db;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * describe:
 *
 * @author donting
 * @date 2019/07/25
 */
@Component
public class MydbService {

    Logger logger = LoggerFactory.getLogger(MydbService.class);
    @Autowired
    DbService dbService;

    public DbEntity executeSqlParams(String sql, Map<String, Object> params) {
        logger.info("executeSql::::"+sql);

        String text = dbService.executeSqlParams(sql, params);
        JSONObject jsonObject = JSONObject.parseObject(text);
        DbEntity dbEntity = jsonObject.toJavaObject(DbEntity.class);
        dbEntity.setDsql();
        dbEntity.setRadata(text);
        return dbEntity;
    }

    public DbEntity executeSqlParams(String sql, Object params) {
        DbParam dbParam = new DbParam(params);
        return this.executeSqlParams(sql, dbParam.getParams());
    }

    public JSONObject save(String table, String pkey, Map<String, Object> params) {
        System.out.println(params.toString());
        String text = dbService.save(table, pkey, params);
        JSONObject jsonObject = JSONObject.parseObject(text);
        return jsonObject;
    }

    public JSONObject save(String table, String pkey,Object params) {
        DbParam dbParam = new DbParam(params);
        return this.save(table, pkey, dbParam.getParams());
    }

    public DbEntity findBySQL(String sql, Map<String, Object> params) {
        logger.info("executeSql::::"+sql);
        String text = dbService.findBySQL(sql, params);
        JSONObject jsonObject = JSONObject.parseObject(text);
        DbEntity dbEntity = jsonObject.toJavaObject(DbEntity.class);
        dbEntity.setRadata(text);
        dbEntity.setDsql();
        return dbEntity;
    }

    public DbEntity findBySQL(String sql, Object params) {
        DbParam dbParam = new DbParam(params);
        return this.findBySQL(sql, dbParam.getParams());
    }


}
