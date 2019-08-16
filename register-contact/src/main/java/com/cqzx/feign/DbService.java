package com.cqzx.feign;

import com.cqzx.feign.impl.DbServiceImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "COM-COMMOC" ,fallback = DbServiceImpl.class,path = "/db")
public interface DbService {
    /**
     * 功能描述：将字典数据初始化到前端js中
     * @return
     */
    @RequestMapping(value = "/executeSql",method = RequestMethod.POST)
    int executeSql(@RequestParam("sql") String sql);

    /**
     * 保存数据
     * @param table 需要保存数数据的表名
     * @param pkey 主键字段名称
     * @param params 字段对应参数值，如：：map<key,value> key对应数据库字段名称，value为字段值
     * @return 主键字段对应值
     * @throws Exception
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    String save(@RequestParam("table") String table, @RequestParam("pkey") String pkey,
                @RequestBody Map<String, Object> params);


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


    @RequestMapping(value = "/get", method = RequestMethod.POST)
    Map<String, Object> get(@RequestParam("table") String table,
                            @RequestParam("pkey") String pkey,@RequestParam("value") String value);
}
