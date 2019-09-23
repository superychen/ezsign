//package com.ezcsign.controller;
//
//import com.ezcsign.aop.Required;
//import com.ezcsign.db.MydbService;
//import com.ezcsign.entity.Buslog;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
///**
// * describe:
// *
// * @author donting
// * @date 2019/07/24
// */
//@RestController
//public class Testaa {
//
//    @Autowired
//    MydbService dbService;
//
//
//    /**
//     * 功能描述：执行insert、update、delete的Sql语句，返回所影响的行数
//     * @return 影响的行数
//     * @throws Exception
//     */
//    @Required(value = {2})
//    @RequestMapping(value = "/executeSql",method = RequestMethod.POST)
//    @ApiOperation(value = "executeSql",notes = "执行insert、update、delete的Sql语句，返回所影响的行数")
//    @ApiImplicitParams(value = {
//            @ApiImplicitParam(name = "table",value = "",paramType = "query"),
//            @ApiImplicitParam(name = "pkey",value = "句",paramType = "query")
//    })
//    public String executeSql(String table, String pkey, String value)  {
//
//        Buslog buslog=new Buslog();
//        buslog.setBuslogCompanyid("111aa11");
//        buslog.setBuslogId("111adaa11");
//        buslog.setCreateId("11111");
//        buslog.setCreateDate(new Date());
//        return dbService.save(buslog);
//
//
//    }
//
//}
