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
public class Memberinfo implements Serializable {
    /**
     * 用户成员节点id,
     */
    private String memberinfoId;

    /**
     * 节点类型,0   根节点公司;1   子公司账户;2   子部门（部门账户）;3   子部门（不对应账户）;4   部门成员（成员账户）
     */
    private Integer memberinfoType;

    /**
     * 企业名(企业信息表)
     */
    private String memberinfoCompanyname;

    /**
     * 节点所在企业的id,
     */
    private String memberinfoCompanyid;

    /**
     * 节点关联的账户id,
     */
    private String memberinfoCustomerid;

    /**
     * 成员名称,
     */
    private String memberinfoName;

    /**
     * 节点状态,1 有效;2 冻结;3 注销;4已提交认证未审核
     */
    private Integer memberinfoStatus;

    /**
     * 创建时间,
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date memberinfoCreattime;

    /**
     * 注销时间,
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date memberinfoDeletetime;

    /**
     * 企业角色,0 成员;1 超级管理员;2 审批管理员;3 档案管理员;4 系统管理员
     */
    private Integer memberinfoRole;

    /**
     * 用户所在项目id,memberinfo_type如果为3则此项为空
     */
    private String memberinfoProjectid;

    /**
     * 用户套餐,
     */
    private String memberinfoMealid;

    /**
     * 右编码,
     */
    private String memberinfoRightcode;

    /**
     * 左编码,
     */
    private String memberinfoLeftcode;

    /**
     * 层级,
     */
    private String memberinfoLevel;

    /**
     * 联系电话,
     */
    private String memberinfoTelephone;

    /**
     * 邮箱,
     */
    private String memberinfoEmail;

    /**
     * 1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知,
     */
    private Integer memberinfoConfig1;

    /**
     * 1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知,
     */
    private Integer memberinfoConfig2;

    /**
     * 1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知,
     */
    private Integer memberinfoConfig3;

    /**
     * 1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知,
     */
    private Integer memberinfoConfig4;

    /**
     * 1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知,
     */
    private Integer memberinfoConfig5;

    /**
     * 1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知,
     */
    private Integer memberinfoConfig6;

    /**
     * 1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知,
     */
    private Integer memberinfoConfig7;

    /**
     * 1 勾选保存;2 不保存,
     */
    private Integer memberinfoCheckbox8;

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
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String updateId;

    /**
     * 修改者姓名
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
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