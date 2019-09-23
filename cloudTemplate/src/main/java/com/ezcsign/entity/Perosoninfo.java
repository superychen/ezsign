package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "perosoninfo")
public class Perosoninfo {
    /**
     * 个人用户ID,
     */
    @Id
    @Column(name = "personinfo_id")
    private String personinfoId;

    /**
     * 姓名,
     */
    @Column(name = "personinfo_name")
    private String personinfoName;

    /**
     * 身份证号码,
     */
    @Column(name = "personinfo_idcard")
    private String personinfoIdcard;

    /**
     * 用户联系人电话,
     */
    @Column(name = "personinfo_telephone")
    private String personinfoTelephone;

    /**
     * 用户状态,0未认证;1已提交认证未审核;2认证通过;5冻结;6注销
     */
    @Column(name = "personinfo_state")
    private Integer personinfoState;

    /**
     * 用户身份认证方式,采用六位二进制表示，;第一位表示人脸识别;第二位表示手机号认证;第三位表示银行卡四要素;第四位表示身份证;第五位表示微信绑定;第六位表示QQ绑定
     */
    @Column(name = "personinfo_authtype")
    private Integer personinfoAuthtype;

    /**
     * 个人用户对应的套餐id,
     */
    @Column(name = "personinfo_mealid")
    private String personinfoMealid;

    /**
     * 个人邮箱,
     */
    @Column(name = "personinfo_email")
    private String personinfoEmail;

    /**
     * 个人银行卡四要素认证流水id,
     */
    @Column(name = "personinfo_authprocessid")
    private String personinfoAuthprocessid;

    /**
     * 身份证正面扫描件存储id,
     */
    @Column(name = "personinfo_idcardfrontid")
    private String personinfoIdcardfrontid;

    /**
     * 身份证反面扫描件存储id,
     */
    @Column(name = "personinfo_idcardbackid")
    private String personinfoIdcardbackid;

    /**
     * 用户微信认证id,
     */
    @Column(name = "personinfo_wechat")
    private String personinfoWechat;

    /**
     * 用户QQ认证id,
     */
    @Column(name = "personinfo_qq")
    private String personinfoQq;

    /**
     * 二次校验密码,（加密）
     */
    @Column(name = "personinfo_signpassword")
    private String personinfoSignpassword;

    /**
     * 签约通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    @Column(name = "personinfo_config1")
    private Integer personinfoConfig1;

    /**
     * 审批通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    @Column(name = "personinfo_config2")
    private Integer personinfoConfig2;

    /**
     * 抄送通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    @Column(name = "personinfo_config3")
    private Integer personinfoConfig3;

    /**
     * 审批通过（不通过）通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    @Column(name = "personinfo_config4")
    private Integer personinfoConfig4;

    /**
     * 签约完成通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    @Column(name = "personinfo_config5")
    private Integer personinfoConfig5;

    /**
     * 拒签通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    @Column(name = "personinfo_config6")
    private Integer personinfoConfig6;

    /**
     * 用户提醒,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    @Column(name = "personinfo_config7")
    private Integer personinfoConfig7;

    /**
     * 自动保存联系人,1 勾选保存;2 不保存
     */
    @Column(name = "personinfo_checkbox8")
    private Integer personinfoCheckbox8;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 创建者id
     */
    @Column(name = "create_id")
    private String createId;

    /**
     * 创建者姓名
     */
    @Column(name = "create_name")
    private String createName;

    /**
     * 修改者id
     */
    @Column(name = "update_id")
    private String updateId;

    /**
     * 修改者姓名
     */
    @Column(name = "update_name")
    private String updateName;

    /**
     * 修改者时间
     */
    @Column(name = "update_datetime")
    private Date updateDatetime;

    /**
     * 数据状态:0=正常数据;1=逻辑删除
     */
    private Integer status;

    /**
     * 获取个人用户ID,
     *
     * @return personinfo_id - 个人用户ID,
     */
    public String getPersoninfoId() {
        return personinfoId;
    }

    /**
     * 设置个人用户ID,
     *
     * @param personinfoId 个人用户ID,
     */
    public void setPersoninfoId(String personinfoId) {
        this.personinfoId = personinfoId;
    }

    /**
     * 获取姓名,
     *
     * @return personinfo_name - 姓名,
     */
    public String getPersoninfoName() {
        return personinfoName;
    }

    /**
     * 设置姓名,
     *
     * @param personinfoName 姓名,
     */
    public void setPersoninfoName(String personinfoName) {
        this.personinfoName = personinfoName;
    }

    /**
     * 获取身份证号码,
     *
     * @return personinfo_idcard - 身份证号码,
     */
    public String getPersoninfoIdcard() {
        return personinfoIdcard;
    }

    /**
     * 设置身份证号码,
     *
     * @param personinfoIdcard 身份证号码,
     */
    public void setPersoninfoIdcard(String personinfoIdcard) {
        this.personinfoIdcard = personinfoIdcard;
    }

    /**
     * 获取用户联系人电话,
     *
     * @return personinfo_telephone - 用户联系人电话,
     */
    public String getPersoninfoTelephone() {
        return personinfoTelephone;
    }

    /**
     * 设置用户联系人电话,
     *
     * @param personinfoTelephone 用户联系人电话,
     */
    public void setPersoninfoTelephone(String personinfoTelephone) {
        this.personinfoTelephone = personinfoTelephone;
    }

    /**
     * 获取用户状态,0未认证;1已提交认证未审核;2认证通过;5冻结;6注销
     *
     * @return personinfo_state - 用户状态,0未认证;1已提交认证未审核;2认证通过;5冻结;6注销
     */
    public Integer getPersoninfoState() {
        return personinfoState;
    }

    /**
     * 设置用户状态,0未认证;1已提交认证未审核;2认证通过;5冻结;6注销
     *
     * @param personinfoState 用户状态,0未认证;1已提交认证未审核;2认证通过;5冻结;6注销
     */
    public void setPersoninfoState(Integer personinfoState) {
        this.personinfoState = personinfoState;
    }

    /**
     * 获取用户身份认证方式,采用六位二进制表示，;第一位表示人脸识别;第二位表示手机号认证;第三位表示银行卡四要素;第四位表示身份证;第五位表示微信绑定;第六位表示QQ绑定
     *
     * @return personinfo_authtype - 用户身份认证方式,采用六位二进制表示，;第一位表示人脸识别;第二位表示手机号认证;第三位表示银行卡四要素;第四位表示身份证;第五位表示微信绑定;第六位表示QQ绑定
     */
    public Integer getPersoninfoAuthtype() {
        return personinfoAuthtype;
    }

    /**
     * 设置用户身份认证方式,采用六位二进制表示，;第一位表示人脸识别;第二位表示手机号认证;第三位表示银行卡四要素;第四位表示身份证;第五位表示微信绑定;第六位表示QQ绑定
     *
     * @param personinfoAuthtype 用户身份认证方式,采用六位二进制表示，;第一位表示人脸识别;第二位表示手机号认证;第三位表示银行卡四要素;第四位表示身份证;第五位表示微信绑定;第六位表示QQ绑定
     */
    public void setPersoninfoAuthtype(Integer personinfoAuthtype) {
        this.personinfoAuthtype = personinfoAuthtype;
    }

    /**
     * 获取个人用户对应的套餐id,
     *
     * @return personinfo_mealid - 个人用户对应的套餐id,
     */
    public String getPersoninfoMealid() {
        return personinfoMealid;
    }

    /**
     * 设置个人用户对应的套餐id,
     *
     * @param personinfoMealid 个人用户对应的套餐id,
     */
    public void setPersoninfoMealid(String personinfoMealid) {
        this.personinfoMealid = personinfoMealid;
    }

    /**
     * 获取个人邮箱,
     *
     * @return personinfo_email - 个人邮箱,
     */
    public String getPersoninfoEmail() {
        return personinfoEmail;
    }

    /**
     * 设置个人邮箱,
     *
     * @param personinfoEmail 个人邮箱,
     */
    public void setPersoninfoEmail(String personinfoEmail) {
        this.personinfoEmail = personinfoEmail;
    }

    /**
     * 获取个人银行卡四要素认证流水id,
     *
     * @return personinfo_authprocessid - 个人银行卡四要素认证流水id,
     */
    public String getPersoninfoAuthprocessid() {
        return personinfoAuthprocessid;
    }

    /**
     * 设置个人银行卡四要素认证流水id,
     *
     * @param personinfoAuthprocessid 个人银行卡四要素认证流水id,
     */
    public void setPersoninfoAuthprocessid(String personinfoAuthprocessid) {
        this.personinfoAuthprocessid = personinfoAuthprocessid;
    }

    /**
     * 获取身份证正面扫描件存储id,
     *
     * @return personinfo_idcardfrontid - 身份证正面扫描件存储id,
     */
    public String getPersoninfoIdcardfrontid() {
        return personinfoIdcardfrontid;
    }

    /**
     * 设置身份证正面扫描件存储id,
     *
     * @param personinfoIdcardfrontid 身份证正面扫描件存储id,
     */
    public void setPersoninfoIdcardfrontid(String personinfoIdcardfrontid) {
        this.personinfoIdcardfrontid = personinfoIdcardfrontid;
    }

    /**
     * 获取身份证反面扫描件存储id,
     *
     * @return personinfo_idcardbackid - 身份证反面扫描件存储id,
     */
    public String getPersoninfoIdcardbackid() {
        return personinfoIdcardbackid;
    }

    /**
     * 设置身份证反面扫描件存储id,
     *
     * @param personinfoIdcardbackid 身份证反面扫描件存储id,
     */
    public void setPersoninfoIdcardbackid(String personinfoIdcardbackid) {
        this.personinfoIdcardbackid = personinfoIdcardbackid;
    }

    /**
     * 获取用户微信认证id,
     *
     * @return personinfo_wechat - 用户微信认证id,
     */
    public String getPersoninfoWechat() {
        return personinfoWechat;
    }

    /**
     * 设置用户微信认证id,
     *
     * @param personinfoWechat 用户微信认证id,
     */
    public void setPersoninfoWechat(String personinfoWechat) {
        this.personinfoWechat = personinfoWechat;
    }

    /**
     * 获取用户QQ认证id,
     *
     * @return personinfo_qq - 用户QQ认证id,
     */
    public String getPersoninfoQq() {
        return personinfoQq;
    }

    /**
     * 设置用户QQ认证id,
     *
     * @param personinfoQq 用户QQ认证id,
     */
    public void setPersoninfoQq(String personinfoQq) {
        this.personinfoQq = personinfoQq;
    }

    /**
     * 获取二次校验密码,（加密）
     *
     * @return personinfo_signpassword - 二次校验密码,（加密）
     */
    public String getPersoninfoSignpassword() {
        return personinfoSignpassword;
    }

    /**
     * 设置二次校验密码,（加密）
     *
     * @param personinfoSignpassword 二次校验密码,（加密）
     */
    public void setPersoninfoSignpassword(String personinfoSignpassword) {
        this.personinfoSignpassword = personinfoSignpassword;
    }

    /**
     * 获取签约通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     *
     * @return personinfo_config1 - 签约通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    public Integer getPersoninfoConfig1() {
        return personinfoConfig1;
    }

    /**
     * 设置签约通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     *
     * @param personinfoConfig1 签约通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    public void setPersoninfoConfig1(Integer personinfoConfig1) {
        this.personinfoConfig1 = personinfoConfig1;
    }

    /**
     * 获取审批通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     *
     * @return personinfo_config2 - 审批通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    public Integer getPersoninfoConfig2() {
        return personinfoConfig2;
    }

    /**
     * 设置审批通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     *
     * @param personinfoConfig2 审批通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    public void setPersoninfoConfig2(Integer personinfoConfig2) {
        this.personinfoConfig2 = personinfoConfig2;
    }

    /**
     * 获取抄送通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     *
     * @return personinfo_config3 - 抄送通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    public Integer getPersoninfoConfig3() {
        return personinfoConfig3;
    }

    /**
     * 设置抄送通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     *
     * @param personinfoConfig3 抄送通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    public void setPersoninfoConfig3(Integer personinfoConfig3) {
        this.personinfoConfig3 = personinfoConfig3;
    }

    /**
     * 获取审批通过（不通过）通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     *
     * @return personinfo_config4 - 审批通过（不通过）通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    public Integer getPersoninfoConfig4() {
        return personinfoConfig4;
    }

    /**
     * 设置审批通过（不通过）通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     *
     * @param personinfoConfig4 审批通过（不通过）通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    public void setPersoninfoConfig4(Integer personinfoConfig4) {
        this.personinfoConfig4 = personinfoConfig4;
    }

    /**
     * 获取签约完成通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     *
     * @return personinfo_config5 - 签约完成通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    public Integer getPersoninfoConfig5() {
        return personinfoConfig5;
    }

    /**
     * 设置签约完成通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     *
     * @param personinfoConfig5 签约完成通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    public void setPersoninfoConfig5(Integer personinfoConfig5) {
        this.personinfoConfig5 = personinfoConfig5;
    }

    /**
     * 获取拒签通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     *
     * @return personinfo_config6 - 拒签通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    public Integer getPersoninfoConfig6() {
        return personinfoConfig6;
    }

    /**
     * 设置拒签通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     *
     * @param personinfoConfig6 拒签通知,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    public void setPersoninfoConfig6(Integer personinfoConfig6) {
        this.personinfoConfig6 = personinfoConfig6;
    }

    /**
     * 获取用户提醒,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     *
     * @return personinfo_config7 - 用户提醒,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    public Integer getPersoninfoConfig7() {
        return personinfoConfig7;
    }

    /**
     * 设置用户提醒,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     *
     * @param personinfoConfig7 用户提醒,1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知
     */
    public void setPersoninfoConfig7(Integer personinfoConfig7) {
        this.personinfoConfig7 = personinfoConfig7;
    }

    /**
     * 获取自动保存联系人,1 勾选保存;2 不保存
     *
     * @return personinfo_checkbox8 - 自动保存联系人,1 勾选保存;2 不保存
     */
    public Integer getPersoninfoCheckbox8() {
        return personinfoCheckbox8;
    }

    /**
     * 设置自动保存联系人,1 勾选保存;2 不保存
     *
     * @param personinfoCheckbox8 自动保存联系人,1 勾选保存;2 不保存
     */
    public void setPersoninfoCheckbox8(Integer personinfoCheckbox8) {
        this.personinfoCheckbox8 = personinfoCheckbox8;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取创建者id
     *
     * @return create_id - 创建者id
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 设置创建者id
     *
     * @param createId 创建者id
     */
    public void setCreateId(String createId) {
        this.createId = createId;
    }

    /**
     * 获取创建者姓名
     *
     * @return create_name - 创建者姓名
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * 设置创建者姓名
     *
     * @param createName 创建者姓名
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     * 获取修改者id
     *
     * @return update_id - 修改者id
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * 设置修改者id
     *
     * @param updateId 修改者id
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    /**
     * 获取修改者姓名
     *
     * @return update_name - 修改者姓名
     */
    public String getUpdateName() {
        return updateName;
    }

    /**
     * 设置修改者姓名
     *
     * @param updateName 修改者姓名
     */
    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    /**
     * 获取修改者时间
     *
     * @return update_datetime - 修改者时间
     */
    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    /**
     * 设置修改者时间
     *
     * @param updateDatetime 修改者时间
     */
    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    /**
     * 获取数据状态:0=正常数据;1=逻辑删除
     *
     * @return status - 数据状态:0=正常数据;1=逻辑删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置数据状态:0=正常数据;1=逻辑删除
     *
     * @param status 数据状态:0=正常数据;1=逻辑删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}