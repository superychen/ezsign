package com.cqzx.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Perosoninfo implements Serializable {
    /**
     * 个人用户ID,
     */
    private String personinfoId;

    /**
     * 姓名,
     */
    private String personinfoName;

    /**
     * 身份证号码,
     */
    private String personinfoIdcard;

    /**
     * 用户联系人电话,
     */
    private String personinfoTelephone;

    /**
     * 用户状态,0未认证;1已提交认证未审核;2认证通过;5冻结;6注销
     */
    private Integer personinfoState;

    /**
     * 用户身份认证方式,采用六位二进制表示，;第一位表示人脸识别;第二位表示手机号认证;第三位表示银行卡四要素;第四位表示身份证;第五位表示微信绑定;第六位表示QQ绑定
     */
    private Integer personinfoAuthtype;

    /**
     * 个人用户对应的套餐id,
     */
    private String personinfoMealid;

    /**
     * 个人邮箱,
     */
    private String personinfoEmail;

    /**
     * 个人银行卡四要素认证流水id,
     */
    private String personinfoAuthprocessid;

    /**
     * 身份证正面扫描件存储id,
     */
    private String personinfoIdcardfrontid;

    /**
     * 身份证反面扫描件存储id,
     */
    private String personinfoIdcardbackid;

    /**
     * 用户微信认证id,
     */
    private String personinfoWechat;

    /**
     * 用户QQ认证id,
     */
    private String personinfoQq;

    /**
     * 二次校验密码,（加密）
     */
    private String personinfoSignpassword;

    /**
     * 签约通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    private Integer personinfoConfig1;

    /**
     * 审批通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    private Integer personinfoConfig2;

    /**
     * 抄送通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    private Integer personinfoConfig3;

    /**
     * 审批通过（不通过）通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    private Integer personinfoConfig4;

    /**
     * 签约完成通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    private Integer personinfoConfig5;

    /**
     * 拒签通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    private Integer personinfoConfig6;

    /**
     * 用户提醒,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    private Integer personinfoConfig7;

    /**
     * 自动保存联系人,1 勾选保存;2 不保存
     */
    private Integer personinfoCheckbox8;

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