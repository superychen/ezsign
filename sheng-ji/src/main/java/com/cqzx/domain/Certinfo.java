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
public class Certinfo implements Serializable {
    /**
     * 证书信息ID
     */
    private String certinfoId;

    /**
     * 证书类型:0=个人证书;1=企业证书
     */
    private Integer certinfoType;

    /**
     * 使用方ID:证书类型=0时为person_info_id,否则为company_info_id
     */
    private String certinfoUserid;

    /**
     * 证书序列号
     */
    private String certinfoSn;

    /**
     * 证书DN
     */
    private String certinfoDn;

    /**
     * 证书算法
     */
    private String certinfoAlg;

    /**
     * 证书生效日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date certinfoFromtime;

    /**
     * 证书失效日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date certinfoEndtime;

    /**
     * 证书颁发者
     */
    private String certinfoIssuer;

    /**
     * 证书状态:0=有效;1=失效
     */
    private Integer certinfoState;

    /**
     * 证书DN
     */
    private String certinfoPwd;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 创建者ID
     */
    private String createId;

    /**
     * 创建者姓名
     */
    private String createName;

    /**
     * 修改者ID
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
    private Date updateDate;

    /**
     * 数据状态:0=正常数据;1=逻辑删除
     */
    private Integer status;

    /**
     * 证书PFX,二进制文件
     */
    private String certinfoPfx;

    /**
     * 证书CER文件,二进制文件
     */
    private String certinfoCer;


}