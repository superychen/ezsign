package com.ezcsign.feign;

import com.ezcsign.feign.impl.CommServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "EZCA-COMMON",fallback = CommServiceImpl.class,path = "/db")
public interface CommService {
    @RequestMapping(value = "/executeSqlParams",method = RequestMethod.POST)
    Map<String, Object> executeSqlByParams(@RequestParam("sql") String sql,
                                           @RequestBody Map<String, Object> params);


    @RequestMapping(value = "/executeSql",method = RequestMethod.POST)
    Map<String, Object> executeSql(@RequestParam("sql") String sql);

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
     * 得到一条数据
     * @param table 表
     * @param pkey 字段
     * @param value 值
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    Map<String, Object> get(@RequestParam("table") String table,
                            @RequestParam("pkey") String pkey,
                            @RequestParam("value") String value);

    /**
     * 获取Redis中的字符串数据
     * @param sKey   对应redis中的key值
     * @return
     */
    @RequestMapping(value = "/getRedisString", method = RequestMethod.POST)
    Map<String, Object> getString(@RequestParam("sKey") String sKey);


    @RequestMapping(value = "/findBySQL", method = RequestMethod.POST)
    Map<String, Object> findBySQL(@RequestParam("sql") String sql, @RequestBody Map<String, Object> params);

    /**
     * 保存字符串数据到Redis,并设置超时时间
     */
    @RequestMapping(value = "/putRedisStringAndTimeOut", method = RequestMethod.POST)
    Map<String, Object> putRedisStringAndTimeOut(@RequestParam("sKey") String sKey,
                                                 @RequestParam("sValue") String sValue,
                                                 @RequestParam("timeout") int timeout);

    /**
     * 分页查询
     */
    @RequestMapping(value = "/findBySQLPage",method = RequestMethod.POST)
    Map<String,Object> findBySQLPage(@RequestParam("sql") String sql,
                                     @RequestBody Map<String, Object> params,
                                     @RequestParam("page") int page,
                                     @RequestParam("rows") int rows);

}
