package com.ezcsign.db;

import com.ezcsign.config.FullLogConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * describe:
 *
 * @author donting
 * @date 2019/07/24
 */
//url = "http://192.168.228.10:4100"
    //微服务服务名
@FeignClient(value="COM-COMMOC",configuration = FullLogConfiguration.class,path = "/db")
public interface DbService {

    /**
     * 执行sql
     * @return
     */
    @RequestMapping(value = "/executeSql",method = RequestMethod.POST)
    String executeSql(@RequestParam("sql") String sql);

    /**
     * 执行sql
     * @param sql sql
     * @param params 参数 对应 ?
     * @return
     */
    @RequestMapping(value = "/executeSqlParams",method = RequestMethod.POST)
    String executeSqlParams(@RequestParam("sql") String sql, @RequestBody Map<String, Object> params);
    /**
     * 保存数据
     * @param table 需要保存数数据的表名
     * @param pkey 主键字段名称
     * @param params 字段对应参数值，如：：map<key,value> key对应数据库字段名称，value为字段值
     * @return 主键字段对应值
     * @throws Exception
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    String save(@RequestParam("table") String table, @RequestParam("pkey") String pkey, @RequestBody Map<String, Object> params);


    /**
     * 修改数据
     * @param table 待修改数据所对应的表名
     * @param pkey  修改主键字段名称
     * @param params  待修改的数据
     * @return 影响的行数
     * @throws Exception
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    String update(@RequestParam("table") String table, @RequestParam("pkey") String pkey, @RequestBody Map<String, Object> params);


    /**
     * 修改数据
     * @param table 待修改数据所对应的表名
     * @param pkey 修改主键字段名称
     * @param params 待修改的数据
     * @return 影响的行数
     * @throws Exception
     */
    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    String changeStatus(@RequestParam("table") String table, @RequestParam("pkey") String pkey, @RequestBody Map<String, Object> params);

    /**
     * 删除数据
     * @param table 待删除数据对应的表名
     * @param pkey 删除数据主键的字段名称
     * @param value 删除字段的值
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    String delete(@RequestParam("table") String table, @RequestParam("pkey") String pkey, @RequestParam("value") String value);
    /**
     * 获取数据
     * @param table 数据对应的表名
     * @param pkey  主键字段名称
     * @param value 字段值
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    String get(@RequestParam("table") String table, @RequestParam("pkey") String pkey, @RequestParam("value") String value);
    /**
     * 通过sql语句获取数据
     * @param sql 标准sql语句
     * @param params 参数值
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findBySQL", method = RequestMethod.POST)
    String findBySQL(@RequestParam("sql") String sql, @RequestBody Map<String, Object> params);

    /**
     * 分页查询
     * @param sql 分页查询sql
     * @param params 参数
     * @param page 页码
     * @param rows 行数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findBySQLPage", method = RequestMethod.POST)
    String findBySQLPage(@RequestParam("sql") String sql, @RequestBody Map<String, Object> params, @RequestParam("page") int page, @RequestParam("rows") int rows);
    /**
     * 从缓存中获取数据，缓存中没有就从数据库中获取,并将新数据添加到缓存中
     * @param table 待查表的表名
     * @param pkey  主键
     * @param params  参数条件
     * @param orderby 排序方式，如：order by xx ASC/DEC 不排序就传一个空字符串
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getRedisMapFromMysql", method = RequestMethod.POST)
    String getRedisMapFromMysql(@RequestParam("table") String table, @RequestParam("pkey") String pkey, @RequestBody Map<String, Object> params, @RequestParam("orderby") String orderby);
    /**
     * 修改数据库中的数据，并删除缓存中的数据
     * @param table 待查表的表名
     * @param pkey  主键
     * @param params  参数条件
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateMysqlAndDelRedis", method = RequestMethod.POST)
    String updateMysqlAndDelRedis(@RequestParam("table") String table, @RequestParam("pkey") String pkey, @RequestBody Map<String, Object> params);
    /**
     * 删除数据库中的数据，并删除缓存中的数据
     * @param table 待查表的表名
     * @param pkey  主键
     * @param param 参数值
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteMysqlAndRedis", method = RequestMethod.POST)
    String deleteMysqlAndRedis(@RequestParam("table") String table, @RequestParam("pkey") String pkey, @RequestParam("param") String param);

}
