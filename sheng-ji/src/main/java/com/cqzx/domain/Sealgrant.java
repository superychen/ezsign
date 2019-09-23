package com.cqzx.domain;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sealgrant implements Serializable {

    /**
     * 印章使用授权id,
     */
    private String sealgrantId;

    /**
     * 授权节点id,
     */
    private String sealgrantMemberid;

    /**
     * 1 个人;2 企业,
     */
    private Integer sealgrantMembertype;

    /**
     * 授权印章id,
     */
    private String sealgrantSealid;

    /**
     * 授权开始时间,
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date sealgrantFromtime;

    /**
     * 授权结束时间,
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date sealgrantTotime;

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

}