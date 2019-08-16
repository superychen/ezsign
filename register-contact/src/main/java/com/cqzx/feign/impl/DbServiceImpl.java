package com.cqzx.feign.impl;

import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.feign.DbService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Component
public class DbServiceImpl implements DbService {
    @Override
    public int executeSql(String sql) {
        if(StringUtils.startsWith(sql,"insert")){
            throw new ZxException(ExceptionEnums.EXECUTE_SQL_INSERT_ERROR);
        }else if(StringUtils.startsWith(sql,"update")){
            throw new ZxException(ExceptionEnums.EXECUTE_SQL_UPDATE_ERROR);
        }else if(StringUtils.startsWith(sql,"delete")){
            throw new ZxException(ExceptionEnums.EXECUTE_SQL_DELETE_ERROR);
        }else {
            throw new ZxException(ExceptionEnums.EXECUTE_SQL_SELECT_ERROR);
        }
    }

    @Override
    public String save(String table, String pkey, Map<String, Object> params) {
        //注册失败
        throw new ZxException(ExceptionEnums.EXECUTE_SQL_INSERT_ERROR);
    }


    @Override
    public Map<String, Object> putRedisString(String sKey, String sValue) {
        //redis存值失败
        throw new ZxException(ExceptionEnums.EXECUTE_REDIS_PUT_ERROR);
    }

    @Override
    public Map<String, Object> putRedisStringAndTimeOut(String sKey, String sValue, int timeout) {
        //redis存值失败
        throw new ZxException(ExceptionEnums.EXECUTE_REDIS_PUT_ERROR);
    }

    @Override
    public Map<String, Object> remove(String sKey) {
        //redis删除值失败
        throw new ZxException(ExceptionEnums.EXECUTE_REDIS_DELETE_ERROR);
    }

    @Override
    public Map<String, Object> getString(String sKey) {
        //redis获取值失败
        throw new ZxException(ExceptionEnums.EXECUTE_REDIS_GET_ERROR);
    }

    @Override
    public Map<String, Object> get(String table, String pkey, String value) {
        throw new ZxException(ExceptionEnums.EXECUTE_SQL_SELECT_ERROR);
    }
}
