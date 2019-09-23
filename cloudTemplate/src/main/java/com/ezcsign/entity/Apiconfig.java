package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "apiconfig")
public class Apiconfig {
    @Id
    @Column(name = "apiconfig_id")
    @GeneratedValue(generator = "JDBC")
    private String apiconfigId;

    /**
     * api信息Id
     */
    @Column(name = "apiconfig_apiinfoid")
    private String apiconfigApiinfoid;

    /**
     * 对应api方法的代码:1 ping;2 getsealinfo;3 sign;4 getsignlink;
     */
    @Column(name = "apiconfig_apicode")
    private Integer apiconfigApicode;

    /**
     * 配置次数,
     */
    @Column(name = "apiconfig_configtimes")
    private Integer apiconfigConfigtimes;

    /**
     * 调用计数,
     */
    @Column(name = "apiconfig_usecount")
    private Integer apiconfigUsecount;

    /**
     * 是否允许超额使用:1 允许;2 不允许
     */
    @Column(name = "apiconfig_allowexcess")
    private Integer apiconfigAllowexcess;

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
     * @return apiconfig_id
     */
    public String getApiconfigId() {
        return apiconfigId;
    }

    /**
     * @param apiconfigId
     */
    public void setApiconfigId(String apiconfigId) {
        this.apiconfigId = apiconfigId;
    }

    /**
     * 获取api信息Id
     *
     * @return apiconfig_apiinfoid - api信息Id
     */
    public String getApiconfigApiinfoid() {
        return apiconfigApiinfoid;
    }

    /**
     * 设置api信息Id
     *
     * @param apiconfigApiinfoid api信息Id
     */
    public void setApiconfigApiinfoid(String apiconfigApiinfoid) {
        this.apiconfigApiinfoid = apiconfigApiinfoid;
    }

    /**
     * 获取对应api方法的代码:1 ping;2 getsealinfo;3 sign;4 getsignlink;
     *
     * @return apiconfig_apicode - 对应api方法的代码:1 ping;2 getsealinfo;3 sign;4 getsignlink;
     */
    public Integer getApiconfigApicode() {
        return apiconfigApicode;
    }

    /**
     * 设置对应api方法的代码:1 ping;2 getsealinfo;3 sign;4 getsignlink;
     *
     * @param apiconfigApicode 对应api方法的代码:1 ping;2 getsealinfo;3 sign;4 getsignlink;
     */
    public void setApiconfigApicode(Integer apiconfigApicode) {
        this.apiconfigApicode = apiconfigApicode;
    }

    /**
     * 获取配置次数,
     *
     * @return apiconfig_configtimes - 配置次数,
     */
    public Integer getApiconfigConfigtimes() {
        return apiconfigConfigtimes;
    }

    /**
     * 设置配置次数,
     *
     * @param apiconfigConfigtimes 配置次数,
     */
    public void setApiconfigConfigtimes(Integer apiconfigConfigtimes) {
        this.apiconfigConfigtimes = apiconfigConfigtimes;
    }

    /**
     * 获取调用计数,
     *
     * @return apiconfig_usecount - 调用计数,
     */
    public Integer getApiconfigUsecount() {
        return apiconfigUsecount;
    }

    /**
     * 设置调用计数,
     *
     * @param apiconfigUsecount 调用计数,
     */
    public void setApiconfigUsecount(Integer apiconfigUsecount) {
        this.apiconfigUsecount = apiconfigUsecount;
    }

    /**
     * 获取是否允许超额使用:1 允许;2 不允许
     *
     * @return apiconfig_allowexcess - 是否允许超额使用:1 允许;2 不允许
     */
    public Integer getApiconfigAllowexcess() {
        return apiconfigAllowexcess;
    }

    /**
     * 设置是否允许超额使用:1 允许;2 不允许
     *
     * @param apiconfigAllowexcess 是否允许超额使用:1 允许;2 不允许
     */
    public void setApiconfigAllowexcess(Integer apiconfigAllowexcess) {
        this.apiconfigAllowexcess = apiconfigAllowexcess;
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