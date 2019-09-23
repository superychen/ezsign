package com.ezcsign.feign.impl;

import com.ezcsign.comm.exception.ExceptionEnums;
import com.ezcsign.comm.exception.ZxException;
import com.ezcsign.feign.CommService;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: 调用数据库的服务失败处理
 * @Author: cqyc
 * @Date:  2019-8-1
 */
@Component
public class CommServiceImpl implements CommService {

    @Override
    public Map<String, Object> executeSqlByParams(String sql, Map<String, Object> params) {
        throw new ZxException(ExceptionEnums.EXECUTE_SQL_UPDATE_ERROR);
    }

    @Override
    public Map<String, Object> executeSql(String sql) {
        throw new ZxException(ExceptionEnums.EXECUTE_SQL_UPDATE_ERROR);
    }

    @Override
    public String save(String table, String pkey, Map<String, Object> params) {
        throw new ZxException(ExceptionEnums.EXECUTE_SQL_INSERT_ERROR);
    }

    @Override
    public Map<String, Object> get(String table, String pkey, String value) {
        throw new ZxException(ExceptionEnums.EXECUTE_SQL_SELECT_ERROR);
    }

    @Override
    public Map<String, Object> getString(String sKey) {
        throw new ZxException(ExceptionEnums.REDIS_QUERY_ERROR);
    }

    @Override
    public Map<String, Object> findBySQL(String sql, Map<String, Object> params) {
        throw new ZxException(ExceptionEnums.EXECUTE_SQL_SELECT_ERROR);
    }

    @Override
    public Map<String, Object> putRedisStringAndTimeOut(String sKey, String sValue, int timeout) {
        throw new ZxException(ExceptionEnums.EXECUTE_SQL_INSERT_ERROR);
    }

    @Override
    public Map<String, Object> findBySQLPage(String sql, Map<String, Object> params, int page, int rows) {
        throw new ZxException(ExceptionEnums.EXECUTE_SQL_SELECT_ERROR);
    }
}
