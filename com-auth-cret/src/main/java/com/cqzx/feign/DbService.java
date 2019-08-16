package com.cqzx.feign;

import com.cqzx.feign.impl.DbServiceImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "COM-COMMOC",fallback = DbServiceImpl.class,path = "/db")
public interface DbService {
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
}
