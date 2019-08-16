package com.cqzx.feign.impl;

import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.feign.GetService;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: 登录服务不可用，快速返回失败
 * @Author:
 * @Date:
 */
@Component
public class GetServiceImpl implements GetService {

    @Override
    public Map<String, Object> get(String table, String pkey, String value) {
        //查询数据失败
        throw new ZxException(ExceptionEnums.EXECUTE_SQL_SELECT_ERROR);
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
}
