package com.cqzx.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Companyinfo implements Serializable {
    /**
     * 企业id,
     */
    private String companyinfoId;

    /**
     * 企业名称,
     */
    private String companyinfoName;

    /**
     * 社会信用代码,
     */
    private String companyinfoCreditcode;


    /**
     * 企业营业执照
     */
    private String companyinfoFile;

    /**
     * 法人姓名,
     */
    private String companyinfoLegalperson;

    /**
     * 企业状态,0未认证;1已提交认证未审核;2认证通过未开通组织服务;3开通组织服务;5冻结;6注销
     */
    private Integer companyinfoState;

    /**
     * 企业认证方式:1 对公打款;2 法人银行卡四要素;3 授权书认证
     */
    private Integer companyinfoAuthtype;

    /**
     * 企业联系手机号,
     */
    private String companyinfoTelephone;

    /**
     * 企业邮箱,
     */
    private String companyinfoEmail;

    /**
     * 企业法人身份证号码,
     */
    private String companyinfoLegalpersonid;

    /**
     * 企业法人银行卡四要素认证流水id,
     */
    private String companyinfoLegalpersonprono;

    /**
     * 企业法人手机号,
     */
    private String companyinfoLegalpersontel;

    /**
     * 银行打款金额,
     */
    private String companyinfoAmount;

    /**
     * 银行打款验证码,
     */
    private String companyinfoVerifycode;

    /**
     * 企业授权人,
     */
    private String companyinfoAgent;

    /**
     * 企业授权人电话,
     */
    private String companyinfoAgenttele;

    /**
     * 授权书id,
     */
    private String companyinfoAuthfileid;

    /**
     * 组织关系类型,定义组织结构成员数量，层级等相关配置的类别
     */
    private Integer companyinfoType;

    /**
     * logo图片id,
     */
    private String companyinfoLogoid;

    /**
     * 企业说明文字（170字以内）,
     */
    private String companyinfoInstruction;

    /**
     * 企业档案库权限1（允许手动归档）,
     */
    private Integer companyinfoFilejurisdiction1;

    /**
     * 企业档案库权限2（允许上传文件归档）,
     */
    private Integer companyinfoFilejurisdiction2;

    /**
     * 企业签约权限3（允许不使用流程）,
     */
    private Integer companyinfoSignjurisdiction3;

    /**
     * 企业签约权限4（允许不使用模板）,
     */
    private Integer companyinfoSignjurisdiction4;

    /**
     * 企业权限:3 不允许查看企业联系人;2 允许查看本部门联系人;1 允许查看企业内所有联系人
     */
    private Integer companyinfoContactjurisdiction5;

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