package com.ezcsign.service.PayService.tenpay.weixin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/3 0003.
 */
@SuppressWarnings({ "unused" })
public class MyProducts implements Products
{
    ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:application.xml");
    public Map<String,Object> getProductInfoByid(String product_id) throws Exception
    {
        /*JdbcTemplate jdbc =(JdbcTemplate)ac.getBean("jdbcTemplate");

        List<Map<String,Object>> list = jdbc.queryForList("SELECT * FROM products WHERE product_id ="+product_id);

        if (list.size()!=1) {
            throw new Exception("查询记录出错！");
        }



        return list.get(0);*/
        return null;
    }

}
