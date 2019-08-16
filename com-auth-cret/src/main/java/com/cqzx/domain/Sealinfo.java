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
public class Sealinfo implements Serializable {
    /**
     * 印章信息ID
     */
    private String sealinfoId;

    /**
     * 印章类型:0=个人印章;1=企业印章
     */
    private Integer sealinfoType;

    /**
     * 使用方ID:印章类型=0时为person_info_id,否则为company_info_id
     */
    private String sealinfoUserid;

    /**
     * 印章名称
     */
    private String sealinfoName;

    /**
     * 印章编码
     */
    private Integer sealinfoCode;

    /**
     * 印章图片文件格式
     */
    private String sealinfoFiletype;

    /**
     * 印章图片高
     */
    private Integer sealinfoWidth;

    /**
     * 印章图片宽
     */
    private Integer sealinfoHeight;

    /**
     * 印章图片存储ID
     */
    private String sealinfoImageid;

    /**
     * 印章对应证书信息ID
     */
    private String sealinfoCertid;

    /**
     * 印章创建ID  对应personinfo或者memberinfo的id
     */
    private String sealinfoCreaterid;

    /**
     * 1 对应personinfo  2 对应memberinfo
     */
    private Integer sealinfoCreatertype;

    /**
     * 印章创建人名称
     */
    private String sealinfoCreatername;

    /**
     * 印章创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date sealinfoCreatetime;

    /**
     * 印章状态:0=有效;1=失效
     */
    private Integer sealinfoState;

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
}