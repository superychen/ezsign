package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "authinfo")
public class Authinfo {
    /**
     * 用户认证流水id,
     */
    @Id
    @Column(name = "authinfo_id")
    @GeneratedValue(generator = "JDBC")
    private String authinfoId;

    /**
     * 认证类型,1 银行卡三要素  ;2 银行卡四要素
     */
    @Column(name = "authinfo_type")
    private Integer authinfoType;

    /**
     * 姓名,
     */
    @Column(name = "authinfo_name")
    private String authinfoName;

    /**
     * 身份证号码,
     */
    @Column(name = "authinfo_idcard")
    private String authinfoIdcard;

    /**
     * 银行卡号码,
     */
    @Column(name = "authinfo_bankcard")
    private String authinfoBankcard;

    /**
     * 认证手机号,
     */
    @Column(name = "authinfo_telephone")
    private String authinfoTelephone;

    /**
     * 认证数据id,
     */
    @Column(name = "authinfo_dataid")
    private String authinfoDataid;

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
     * 获取用户认证流水id,
     *
     * @return authinfo_id - 用户认证流水id,
     */
    public String getAuthinfoId() {
        return authinfoId;
    }

    /**
     * 设置用户认证流水id,
     *
     * @param authinfoId 用户认证流水id,
     */
    public void setAuthinfoId(String authinfoId) {
        this.authinfoId = authinfoId;
    }

    /**
     * 获取认证类型,1 银行卡三要素  ;2 银行卡四要素
     *
     * @return authinfo_type - 认证类型,1 银行卡三要素  ;2 银行卡四要素
     */
    public Integer getAuthinfoType() {
        return authinfoType;
    }

    /**
     * 设置认证类型,1 银行卡三要素  ;2 银行卡四要素
     *
     * @param authinfoType 认证类型,1 银行卡三要素  ;2 银行卡四要素
     */
    public void setAuthinfoType(Integer authinfoType) {
        this.authinfoType = authinfoType;
    }

    /**
     * 获取姓名,
     *
     * @return authinfo_name - 姓名,
     */
    public String getAuthinfoName() {
        return authinfoName;
    }

    /**
     * 设置姓名,
     *
     * @param authinfoName 姓名,
     */
    public void setAuthinfoName(String authinfoName) {
        this.authinfoName = authinfoName;
    }

    /**
     * 获取身份证号码,
     *
     * @return authinfo_idcard - 身份证号码,
     */
    public String getAuthinfoIdcard() {
        return authinfoIdcard;
    }

    /**
     * 设置身份证号码,
     *
     * @param authinfoIdcard 身份证号码,
     */
    public void setAuthinfoIdcard(String authinfoIdcard) {
        this.authinfoIdcard = authinfoIdcard;
    }

    /**
     * 获取银行卡号码,
     *
     * @return authinfo_bankcard - 银行卡号码,
     */
    public String getAuthinfoBankcard() {
        return authinfoBankcard;
    }

    /**
     * 设置银行卡号码,
     *
     * @param authinfoBankcard 银行卡号码,
     */
    public void setAuthinfoBankcard(String authinfoBankcard) {
        this.authinfoBankcard = authinfoBankcard;
    }

    /**
     * 获取认证手机号,
     *
     * @return authinfo_telephone - 认证手机号,
     */
    public String getAuthinfoTelephone() {
        return authinfoTelephone;
    }

    /**
     * 设置认证手机号,
     *
     * @param authinfoTelephone 认证手机号,
     */
    public void setAuthinfoTelephone(String authinfoTelephone) {
        this.authinfoTelephone = authinfoTelephone;
    }

    /**
     * 获取认证数据id,
     *
     * @return authinfo_dataid - 认证数据id,
     */
    public String getAuthinfoDataid() {
        return authinfoDataid;
    }

    /**
     * 设置认证数据id,
     *
     * @param authinfoDataid 认证数据id,
     */
    public void setAuthinfoDataid(String authinfoDataid) {
        this.authinfoDataid = authinfoDataid;
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