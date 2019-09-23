package com.ezcsign.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    private String customerId;

    /**
     * 登录名
     */
    private String customerLoginname;

    /**
     * ,（加密）
     */
    private String customerPassword;

    /**
     * 注册时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date customerRegisttime;

    /**
     * 对应个人信息表id
     */
    private String customerPersonid;

    /**
     * ,1 正常使用;2 初始账户，需要改密码;3 冻结;4 注销
     */
    private Integer customerState;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date customerLastlogtime;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 创建者id
     */
    private String createId;

    /**
     * 创建者姓名
     */
    private String createName;

    /**
     * 修改者id
     */
    private String updateId;

    /**
     * 修改者姓名
     */
    private String updateName;

    /**
     * 修改者时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDatetime;

    /**
     * 数据状态:0=正常数据;1=逻辑删除
     */
    private Integer status;

    /**
     * 用户类型，1 表示进入个人签约，2 表示进入企业签约
     */
    private Integer userType;
}