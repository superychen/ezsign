package com.cqzx.feign;

import com.cqzx.feign.impl.GetServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

//失败则回滚到GetServiceImpl
@FeignClient(value = "EZCA-COMMON",fallback = GetServiceImpl.class,path = "/db")
public interface GetService {

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    Map<String, Object> get(@RequestParam("table") String table, @RequestParam("pkey") String pkey,@RequestParam("value") String value);


    /**
     * 向redis存值
     * @param sKey
     * @param sValue
     * @return
     */
    @RequestMapping(value = "/putRedisString", method = RequestMethod.POST)
    Map<String, Object> putRedisString(@RequestParam("sKey") String sKey, @RequestParam("sValue") String sValue);


    /**
     * 保存字符串数据到Redis,并设置超时时间
     */
    @RequestMapping(value = "/putRedisStringAndTimeOut", method = RequestMethod.POST)
    Map<String, Object> putRedisStringAndTimeOut(@RequestParam("sKey") String sKey,
                                                  @RequestParam("sValue") String sValue,
                                                  @RequestParam("timeout") int timeout);

    /**
     * 删除Redis中的值
     */
    @RequestMapping(value = "/removeRedis", method = RequestMethod.POST)
    Map<String, Object> remove(@RequestParam("sKey") String sKey);

    /**
     * 获取Redis中的字符串数据
     */
    @RequestMapping(value = "/getRedisString", method = RequestMethod.POST)
    Map<String, Object> getString(@RequestParam("sKey") String sKey);

    /**
     * 查询sql语句
     */
    @RequestMapping(value = "/findBySQL", method = RequestMethod.POST)
    Map<String, Object> findBySQL(@RequestParam("sql") String sql, @RequestBody Map<String, Object> params);
}
