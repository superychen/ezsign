package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "companyinfo")
public class Companyinfo {
    /**
     * 企业id,
     */
    @Id
    @Column(name = "companyinfo_id")
    private String companyinfoId;

    /**
     * 企业名称,
     */
    @Column(name = "companyinfo_name")
    private String companyinfoName;

    /**
     * 社会信用代码,
     */
    @Column(name = "companyinfo_creditcode")
    private String companyinfoCreditcode;

    /**
     * 法人姓名,
     */
    @Column(name = "companyinfo_legalperson")
    private String companyinfoLegalperson;

    /**
     * 企业状态,0未认证;1已提交认证未审核;2认证通过未开通组织服务;3开通组织服务;5冻结;6注销
     */
    @Column(name = "companyinfo_state")
    private Integer companyinfoState;

    /**
     * 企业认证方式:1 对公打款;2 法人银行卡四要素;3 授权书认证
     */
    @Column(name = "companyinfo_authtype")
    private Integer companyinfoAuthtype;

    /**
     * 企业联系手机号,
     */
    @Column(name = "companyinfo_telephone")
    private String companyinfoTelephone;

    /**
     * 企业邮箱,
     */
    @Column(name = "companyinfo_email")
    private String companyinfoEmail;

    /**
     * 企业法人身份证号码,
     */
    @Column(name = "companyinfo_legalpersonid")
    private String companyinfoLegalpersonid;

    /**
     * 企业法人银行卡四要素认证流水id,
     */
    @Column(name = "companyinfo_legalpersonprono")
    private String companyinfoLegalpersonprono;

    /**
     * 企业法人手机号,
     */
    @Column(name = "companyinfo_legalpersontel")
    private String companyinfoLegalpersontel;

    /**
     * 银行打款金额,
     */
    @Column(name = "companyinfo_amount")
    private String companyinfoAmount;

    /**
     * 银行打款验证码,
     */
    @Column(name = "companyinfo_verifycode")
    private String companyinfoVerifycode;

    /**
     * 企业授权人,
     */
    @Column(name = "companyinfo_agent")
    private String companyinfoAgent;

    /**
     * 企业授权人电话,
     */
    @Column(name = "companyinfo_agenttele")
    private String companyinfoAgenttele;

    /**
     * 授权书id,
     */
    @Column(name = "companyinfo_authfileid")
    private String companyinfoAuthfileid;

    /**
     * 组织关系类型,定义组织结构成员数量，层级等相关配置的类别
     */
    @Column(name = "companyinfo_type")
    private Integer companyinfoType;

    /**
     * logo图片id,
     */
    @Column(name = "companyinfo_logoid")
    private String companyinfoLogoid;

    /**
     * 企业说明文字（170字以内）,
     */
    @Column(name = "companyinfo_instruction")
    private String companyinfoInstruction;

    /**
     * 企业档案库权限1（允许手动归档）,
     */
    @Column(name = "companyinfo_filejurisdiction1")
    private Integer companyinfoFilejurisdiction1;

    /**
     * 企业档案库权限2（允许上传文件归档）,
     */
    @Column(name = "companyinfo_filejurisdiction2")
    private Integer companyinfoFilejurisdiction2;

    /**
     * 企业签约权限3（允许不使用流程）,
     */
    @Column(name = "companyinfo_signjurisdiction3")
    private Integer companyinfoSignjurisdiction3;

    /**
     * 企业签约权限4（允许不使用模板）,
     */
    @Column(name = "companyinfo_signjurisdiction4")
    private Integer companyinfoSignjurisdiction4;

    /**
     * 企业权限:3 不允许查看企业联系人;2 允许查看本部门联系人;1 允许查看企业内所有联系人
     */
    @Column(name = "companyinfo_contactjurisdiction5")
    private Integer companyinfoContactjurisdiction5;

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
     * 获取企业id,
     *
     * @return companyinfo_id - 企业id,
     */
    public String getCompanyinfoId() {
        return companyinfoId;
    }

    /**
     * 设置企业id,
     *
     * @param companyinfoId 企业id,
     */
    public void setCompanyinfoId(String companyinfoId) {
        this.companyinfoId = companyinfoId;
    }

    /**
     * 获取企业名称,
     *
     * @return companyinfo_name - 企业名称,
     */
    public String getCompanyinfoName() {
        return companyinfoName;
    }

    /**
     * 设置企业名称,
     *
     * @param companyinfoName 企业名称,
     */
    public void setCompanyinfoName(String companyinfoName) {
        this.companyinfoName = companyinfoName;
    }

    /**
     * 获取社会信用代码,
     *
     * @return companyinfo_creditcode - 社会信用代码,
     */
    public String getCompanyinfoCreditcode() {
        return companyinfoCreditcode;
    }

    /**
     * 设置社会信用代码,
     *
     * @param companyinfoCreditcode 社会信用代码,
     */
    public void setCompanyinfoCreditcode(String companyinfoCreditcode) {
        this.companyinfoCreditcode = companyinfoCreditcode;
    }

    /**
     * 获取法人姓名,
     *
     * @return companyinfo_legalperson - 法人姓名,
     */
    public String getCompanyinfoLegalperson() {
        return companyinfoLegalperson;
    }

    /**
     * 设置法人姓名,
     *
     * @param companyinfoLegalperson 法人姓名,
     */
    public void setCompanyinfoLegalperson(String companyinfoLegalperson) {
        this.companyinfoLegalperson = companyinfoLegalperson;
    }

    /**
     * 获取企业状态,0未认证;1已提交认证未审核;2认证通过未开通组织服务;3开通组织服务;5冻结;6注销
     *
     * @return companyinfo_state - 企业状态,0未认证;1已提交认证未审核;2认证通过未开通组织服务;3开通组织服务;5冻结;6注销
     */
    public Integer getCompanyinfoState() {
        return companyinfoState;
    }

    /**
     * 设置企业状态,0未认证;1已提交认证未审核;2认证通过未开通组织服务;3开通组织服务;5冻结;6注销
     *
     * @param companyinfoState 企业状态,0未认证;1已提交认证未审核;2认证通过未开通组织服务;3开通组织服务;5冻结;6注销
     */
    public void setCompanyinfoState(Integer companyinfoState) {
        this.companyinfoState = companyinfoState;
    }

    /**
     * 获取企业认证方式:1 对公打款;2 法人银行卡四要素;3 授权书认证
     *
     * @return companyinfo_authtype - 企业认证方式:1 对公打款;2 法人银行卡四要素;3 授权书认证
     */
    public Integer getCompanyinfoAuthtype() {
        return companyinfoAuthtype;
    }

    /**
     * 设置企业认证方式:1 对公打款;2 法人银行卡四要素;3 授权书认证
     *
     * @param companyinfoAuthtype 企业认证方式:1 对公打款;2 法人银行卡四要素;3 授权书认证
     */
    public void setCompanyinfoAuthtype(Integer companyinfoAuthtype) {
        this.companyinfoAuthtype = companyinfoAuthtype;
    }

    /**
     * 获取企业联系手机号,
     *
     * @return companyinfo_telephone - 企业联系手机号,
     */
    public String getCompanyinfoTelephone() {
        return companyinfoTelephone;
    }

    /**
     * 设置企业联系手机号,
     *
     * @param companyinfoTelephone 企业联系手机号,
     */
    public void setCompanyinfoTelephone(String companyinfoTelephone) {
        this.companyinfoTelephone = companyinfoTelephone;
    }

    /**
     * 获取企业邮箱,
     *
     * @return companyinfo_email - 企业邮箱,
     */
    public String getCompanyinfoEmail() {
        return companyinfoEmail;
    }

    /**
     * 设置企业邮箱,
     *
     * @param companyinfoEmail 企业邮箱,
     */
    public void setCompanyinfoEmail(String companyinfoEmail) {
        this.companyinfoEmail = companyinfoEmail;
    }

    /**
     * 获取企业法人身份证号码,
     *
     * @return companyinfo_legalpersonid - 企业法人身份证号码,
     */
    public String getCompanyinfoLegalpersonid() {
        return companyinfoLegalpersonid;
    }

    /**
     * 设置企业法人身份证号码,
     *
     * @param companyinfoLegalpersonid 企业法人身份证号码,
     */
    public void setCompanyinfoLegalpersonid(String companyinfoLegalpersonid) {
        this.companyinfoLegalpersonid = companyinfoLegalpersonid;
    }

    /**
     * 获取企业法人银行卡四要素认证流水id,
     *
     * @return companyinfo_legalpersonprono - 企业法人银行卡四要素认证流水id,
     */
    public String getCompanyinfoLegalpersonprono() {
        return companyinfoLegalpersonprono;
    }

    /**
     * 设置企业法人银行卡四要素认证流水id,
     *
     * @param companyinfoLegalpersonprono 企业法人银行卡四要素认证流水id,
     */
    public void setCompanyinfoLegalpersonprono(String companyinfoLegalpersonprono) {
        this.companyinfoLegalpersonprono = companyinfoLegalpersonprono;
    }

    /**
     * 获取企业法人手机号,
     *
     * @return companyinfo_legalpersontel - 企业法人手机号,
     */
    public String getCompanyinfoLegalpersontel() {
        return companyinfoLegalpersontel;
    }

    /**
     * 设置企业法人手机号,
     *
     * @param companyinfoLegalpersontel 企业法人手机号,
     */
    public void setCompanyinfoLegalpersontel(String companyinfoLegalpersontel) {
        this.companyinfoLegalpersontel = companyinfoLegalpersontel;
    }

    /**
     * 获取银行打款金额,
     *
     * @return companyinfo_amount - 银行打款金额,
     */
    public String getCompanyinfoAmount() {
        return companyinfoAmount;
    }

    /**
     * 设置银行打款金额,
     *
     * @param companyinfoAmount 银行打款金额,
     */
    public void setCompanyinfoAmount(String companyinfoAmount) {
        this.companyinfoAmount = companyinfoAmount;
    }

    /**
     * 获取银行打款验证码,
     *
     * @return companyinfo_verifycode - 银行打款验证码,
     */
    public String getCompanyinfoVerifycode() {
        return companyinfoVerifycode;
    }

    /**
     * 设置银行打款验证码,
     *
     * @param companyinfoVerifycode 银行打款验证码,
     */
    public void setCompanyinfoVerifycode(String companyinfoVerifycode) {
        this.companyinfoVerifycode = companyinfoVerifycode;
    }

    /**
     * 获取企业授权人,
     *
     * @return companyinfo_agent - 企业授权人,
     */
    public String getCompanyinfoAgent() {
        return companyinfoAgent;
    }

    /**
     * 设置企业授权人,
     *
     * @param companyinfoAgent 企业授权人,
     */
    public void setCompanyinfoAgent(String companyinfoAgent) {
        this.companyinfoAgent = companyinfoAgent;
    }

    /**
     * 获取企业授权人电话,
     *
     * @return companyinfo_agenttele - 企业授权人电话,
     */
    public String getCompanyinfoAgenttele() {
        return companyinfoAgenttele;
    }

    /**
     * 设置企业授权人电话,
     *
     * @param companyinfoAgenttele 企业授权人电话,
     */
    public void setCompanyinfoAgenttele(String companyinfoAgenttele) {
        this.companyinfoAgenttele = companyinfoAgenttele;
    }

    /**
     * 获取授权书id,
     *
     * @return companyinfo_authfileid - 授权书id,
     */
    public String getCompanyinfoAuthfileid() {
        return companyinfoAuthfileid;
    }

    /**
     * 设置授权书id,
     *
     * @param companyinfoAuthfileid 授权书id,
     */
    public void setCompanyinfoAuthfileid(String companyinfoAuthfileid) {
        this.companyinfoAuthfileid = companyinfoAuthfileid;
    }

    /**
     * 获取组织关系类型,定义组织结构成员数量，层级等相关配置的类别
     *
     * @return companyinfo_type - 组织关系类型,定义组织结构成员数量，层级等相关配置的类别
     */
    public Integer getCompanyinfoType() {
        return companyinfoType;
    }

    /**
     * 设置组织关系类型,定义组织结构成员数量，层级等相关配置的类别
     *
     * @param companyinfoType 组织关系类型,定义组织结构成员数量，层级等相关配置的类别
     */
    public void setCompanyinfoType(Integer companyinfoType) {
        this.companyinfoType = companyinfoType;
    }

    /**
     * 获取logo图片id,
     *
     * @return companyinfo_logoid - logo图片id,
     */
    public String getCompanyinfoLogoid() {
        return companyinfoLogoid;
    }

    /**
     * 设置logo图片id,
     *
     * @param companyinfoLogoid logo图片id,
     */
    public void setCompanyinfoLogoid(String companyinfoLogoid) {
        this.companyinfoLogoid = companyinfoLogoid;
    }

    /**
     * 获取企业说明文字（170字以内）,
     *
     * @return companyinfo_instruction - 企业说明文字（170字以内）,
     */
    public String getCompanyinfoInstruction() {
        return companyinfoInstruction;
    }

    /**
     * 设置企业说明文字（170字以内）,
     *
     * @param companyinfoInstruction 企业说明文字（170字以内）,
     */
    public void setCompanyinfoInstruction(String companyinfoInstruction) {
        this.companyinfoInstruction = companyinfoInstruction;
    }

    /**
     * 获取企业档案库权限1（允许手动归档）,
     *
     * @return companyinfo_filejurisdiction1 - 企业档案库权限1（允许手动归档）,
     */
    public Integer getCompanyinfoFilejurisdiction1() {
        return companyinfoFilejurisdiction1;
    }

    /**
     * 设置企业档案库权限1（允许手动归档）,
     *
     * @param companyinfoFilejurisdiction1 企业档案库权限1（允许手动归档）,
     */
    public void setCompanyinfoFilejurisdiction1(Integer companyinfoFilejurisdiction1) {
        this.companyinfoFilejurisdiction1 = companyinfoFilejurisdiction1;
    }

    /**
     * 获取企业档案库权限2（允许上传文件归档）,
     *
     * @return companyinfo_filejurisdiction2 - 企业档案库权限2（允许上传文件归档）,
     */
    public Integer getCompanyinfoFilejurisdiction2() {
        return companyinfoFilejurisdiction2;
    }

    /**
     * 设置企业档案库权限2（允许上传文件归档）,
     *
     * @param companyinfoFilejurisdiction2 企业档案库权限2（允许上传文件归档）,
     */
    public void setCompanyinfoFilejurisdiction2(Integer companyinfoFilejurisdiction2) {
        this.companyinfoFilejurisdiction2 = companyinfoFilejurisdiction2;
    }

    /**
     * 获取企业签约权限3（允许不使用流程）,
     *
     * @return companyinfo_signjurisdiction3 - 企业签约权限3（允许不使用流程）,
     */
    public Integer getCompanyinfoSignjurisdiction3() {
        return companyinfoSignjurisdiction3;
    }

    /**
     * 设置企业签约权限3（允许不使用流程）,
     *
     * @param companyinfoSignjurisdiction3 企业签约权限3（允许不使用流程）,
     */
    public void setCompanyinfoSignjurisdiction3(Integer companyinfoSignjurisdiction3) {
        this.companyinfoSignjurisdiction3 = companyinfoSignjurisdiction3;
    }

    /**
     * 获取企业签约权限4（允许不使用模板）,
     *
     * @return companyinfo_signjurisdiction4 - 企业签约权限4（允许不使用模板）,
     */
    public Integer getCompanyinfoSignjurisdiction4() {
        return companyinfoSignjurisdiction4;
    }

    /**
     * 设置企业签约权限4（允许不使用模板）,
     *
     * @param companyinfoSignjurisdiction4 企业签约权限4（允许不使用模板）,
     */
    public void setCompanyinfoSignjurisdiction4(Integer companyinfoSignjurisdiction4) {
        this.companyinfoSignjurisdiction4 = companyinfoSignjurisdiction4;
    }

    /**
     * 获取企业权限:3 不允许查看企业联系人;2 允许查看本部门联系人;1 允许查看企业内所有联系人
     *
     * @return companyinfo_contactjurisdiction5 - 企业权限:3 不允许查看企业联系人;2 允许查看本部门联系人;1 允许查看企业内所有联系人
     */
    public Integer getCompanyinfoContactjurisdiction5() {
        return companyinfoContactjurisdiction5;
    }

    /**
     * 设置企业权限:3 不允许查看企业联系人;2 允许查看本部门联系人;1 允许查看企业内所有联系人
     *
     * @param companyinfoContactjurisdiction5 企业权限:3 不允许查看企业联系人;2 允许查看本部门联系人;1 允许查看企业内所有联系人
     */
    public void setCompanyinfoContactjurisdiction5(Integer companyinfoContactjurisdiction5) {
        this.companyinfoContactjurisdiction5 = companyinfoContactjurisdiction5;
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