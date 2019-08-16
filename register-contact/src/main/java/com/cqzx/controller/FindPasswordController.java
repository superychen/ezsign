package com.cqzx.controller;

import com.cqzx.service.FindPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description: 找回密码控制层
 * @Author: cqyc
 * @Date: 2019-8-15
 */
@RestController
@RequestMapping("/find")
public class FindPasswordController {

    @Autowired
    private FindPasswordService findPasswordService;

    /**
     * 找回用户名
     * @param name 姓名
     * @param idCart 身份证号
     * @param telephone 电话号码
     */
    @PostMapping("/name")
    public Map<String,String> findLoginname(@RequestParam("name") String name,
                                            @RequestParam("idCart") String idCart,
                                            @RequestParam("telephone") String telephone){

        return findPasswordService.findLoginname(name,idCart,telephone);
    }
}
