package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "apiinfo")
public class Apiinfo {
    /**
     * 用户api信息id,
     */
    @Id
    @Column(name = "apiinfo_id")
    @GeneratedValue(generator = "JDBC")
    private String apiinfoId;

    /**
     * 用户名称,
     */
    @Column(name = "apiinfo_membername")
    private String apiinfoMembername;

    /**
     * 对应用户id,
     */
    @Column(name = "apiinfo_memberid")
    private String apiinfoMemberid;

    /**
     * 用户类型:1 个人;2 企业
     */
    @Column(name = "apiinfo_membertype")
    private Integer apiinfoMembertype;

    @Column(name = "apiinfo_appkey")
    private String apiinfoAppkey;

    @Column(name = "apiinfo_appcode")
    private String apiinfoAppcode;

    @Column(name = "apiinfo_appsecrete")
    private String apiinfoAppsecrete;

    /**
     * 开通api的时间,
     */
    @Column(name = "apiinfo_createtime")
    private Date apiinfoCreatetime;

    /**
     * 回调地址,
     */
    @Column(name = "apiinfo_callback")
    private String apiinfoCallback;

    /**
     * 用户参数,
     */
    @Column(name = "apiinfo_userparam")
    private String apiinfoUserparam;

    /**
     * 用户api配置项:采用二进制表示，每一位对应一个配置项,详细内容待定
     */
    @Column(name = "apiinfo_userconfig")
    private Integer apiinfoUserconfig;

    /**
     * 套餐名称,
     */
    @Column(name = "apiinfo_mealname")
    private String apiinfoMealname;

    /**
     * 用户套餐,
     */
    @Column(name = "apiinfo_mealid")
    private String apiinfoMealid;

    /**
     * api说明（20字）,
     */
    @Column(name = "apiinfo_explain")
    private String apiinfoExplain;

    /**
     * api状态,1 启用;2 停用;3 注销
     */
    @Column(name = "apiinfo_status")
    private Integer apiinfoStatus;

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
     * 获取用户api信息id,
     *
     * @return apiinfo_id - 用户api信息id,
     */
    public String getApiinfoId() {
        return apiinfoId;
    }

    /**
     * 设置用户api信息id,
     *
     * @param apiinfoId 用户api信息id,
     */
    public void setApiinfoId(String apiinfoId) {
        this.apiinfoId = apiinfoId;
    }

    /**
     * 获取用户名称,
     *
     * @return apiinfo_membername - 用户名称,
     */
    public String getApiinfoMembername() {
        return apiinfoMembername;
    }

    /**
     * 设置用户名称,
     *
     * @param apiinfoMembername 用户名称,
     */
    public void setApiinfoMembername(String apiinfoMembername) {
        this.apiinfoMembername = apiinfoMembername;
    }

    /**
     * 获取对应用户id,
     *
     * @return apiinfo_memberid - 对应用户id,
     */
    public String getApiinfoMemberid() {
        return apiinfoMemberid;
    }

    /**
     * 设置对应用户id,
     *
     * @param apiinfoMemberid 对应用户id,
     */
    public void setApiinfoMemberid(String apiinfoMemberid) {
        this.apiinfoMemberid = apiinfoMemberid;
    }

    /**
     * 获取用户类型:1 个人;2 企业
     *
     * @return apiinfo_membertype - 用户类型:1 个人;2 企业
     */
    public Integer getApiinfoMembertype() {
        return apiinfoMembertype;
    }

    /**
     * 设置用户类型:1 个人;2 企业
     *
     * @param apiinfoMembertype 用户类型:1 个人;2 企业
     */
    public void setApiinfoMembertype(Integer apiinfoMembertype) {
        this.apiinfoMembertype = apiinfoMembertype;
    }

    /**
     * @return apiinfo_appkey
     */
    public String getApiinfoAppkey() {
        return apiinfoAppkey;
    }

    /**
     * @param apiinfoAppkey
     */
    public void setApiinfoAppkey(String apiinfoAppkey) {
        this.apiinfoAppkey = apiinfoAppkey;
    }

    /**
     * @return apiinfo_appcode
     */
    public String getApiinfoAppcode() {
        return apiinfoAppcode;
    }

    /**
     * @param apiinfoAppcode
     */
    public void setApiinfoAppcode(String apiinfoAppcode) {
        this.apiinfoAppcode = apiinfoAppcode;
    }

    /**
     * @return apiinfo_appsecrete
     */
    public String getApiinfoAppsecrete() {
        return apiinfoAppsecrete;
    }

    /**
     * @param apiinfoAppsecrete
     */
    public void setApiinfoAppsecrete(String apiinfoAppsecrete) {
        this.apiinfoAppsecrete = apiinfoAppsecrete;
    }

    /**
     * 获取开通api的时间,
     *
     * @return apiinfo_createtime - 开通api的时间,
     */
    public Date getApiinfoCreatetime() {
        return apiinfoCreatetime;
    }

    /**
     * 设置开通api的时间,
     *
     * @param apiinfoCreatetime 开通api的时间,
     */
    public void setApiinfoCreatetime(Date apiinfoCreatetime) {
        this.apiinfoCreatetime = apiinfoCreatetime;
    }

    /**
     * 获取回调地址,
     *
     * @return apiinfo_callback - 回调地址,
     */
    public String getApiinfoCallback() {
        return apiinfoCallback;
    }

    /**
     * 设置回调地址,
     *
     * @param apiinfoCallback 回调地址,
     */
    public void setApiinfoCallback(String apiinfoCallback) {
        this.apiinfoCallback = apiinfoCallback;
    }

    /**
     * 获取用户参数,
     *
     * @return apiinfo_userparam - 用户参数,
     */
    public String getApiinfoUserparam() {
        return apiinfoUserparam;
    }

    /**
     * 设置用户参数,
     *
     * @param apiinfoUserparam 用户参数,
     */
    public void setApiinfoUserparam(String apiinfoUserparam) {
        this.apiinfoUserparam = apiinfoUserparam;
    }

    /**
     * 获取用户api配置项:采用二进制表示，每一位对应一个配置项,详细内容待定
     *
     * @return apiinfo_userconfig - 用户api配置项:采用二进制表示，每一位对应一个配置项,详细内容待定
     */
    public Integer getApiinfoUserconfig() {
        return apiinfoUserconfig;
    }

    /**
     * 设置用户api配置项:采用二进制表示，每一位对应一个配置项,详细内容待定
     *
     * @param apiinfoUserconfig 用户api配置项:采用二进制表示，每一位对应一个配置项,详细内容待定
     */
    public void setApiinfoUserconfig(Integer apiinfoUserconfig) {
        this.apiinfoUserconfig = apiinfoUserconfig;
    }

    /**
     * 获取套餐名称,
     *
     * @return apiinfo_mealname - 套餐名称,
     */
    public String getApiinfoMealname() {
        return apiinfoMealname;
    }

    /**
     * 设置套餐名称,
     *
     * @param apiinfoMealname 套餐名称,
     */
    public void setApiinfoMealname(String apiinfoMealname) {
        this.apiinfoMealname = apiinfoMealname;
    }

    /**
     * 获取用户套餐,
     *
     * @return apiinfo_mealid - 用户套餐,
     */
    public String getApiinfoMealid() {
        return apiinfoMealid;
    }

    /**
     * 设置用户套餐,
     *
     * @param apiinfoMealid 用户套餐,
     */
    public void setApiinfoMealid(String apiinfoMealid) {
        this.apiinfoMealid = apiinfoMealid;
    }

    /**
     * 获取api说明（20字）,
     *
     * @return apiinfo_explain - api说明（20字）,
     */
    public String getApiinfoExplain() {
        return apiinfoExplain;
    }

    /**
     * 设置api说明（20字）,
     *
     * @param apiinfoExplain api说明（20字）,
     */
    public void setApiinfoExplain(String apiinfoExplain) {
        this.apiinfoExplain = apiinfoExplain;
    }

    /**
     * 获取api状态,1 启用;2 停用;3 注销
     *
     * @return apiinfo_status - api状态,1 启用;2 停用;3 注销
     */
    public Integer getApiinfoStatus() {
        return apiinfoStatus;
    }

    /**
     * 设置api状态,1 启用;2 停用;3 注销
     *
     * @param apiinfoStatus api状态,1 启用;2 停用;3 注销
     */
    public void setApiinfoStatus(Integer apiinfoStatus) {
        this.apiinfoStatus = apiinfoStatus;
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