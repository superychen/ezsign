package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "servicenotice")
public class Servicenotice {
    /**
     * 提醒id,
     */
    @Id
    @Column(name = "servicenotice_id")
    @GeneratedValue(generator = "JDBC")
    private String servicenoticeId;

    /**
     * 服务代码,
     */
    @Column(name = "servicenotice_servicecode")
    private String servicenoticeServicecode;

    /**
     * 对应服务id,
     */
    @Column(name = "servicenotice_serviceid")
    private String servicenoticeServiceid;

    /**
     * 服务名称,
     */
    @Column(name = "servicenotice_name")
    private String servicenoticeName;

    /**
     * 余额,
     */
    @Column(name = "servicenotice_balance")
    private Integer servicenoticeBalance;

    /**
     * 对应用户,
     */
    @Column(name = "servicenotice_memberid")
    private String servicenoticeMemberid;

    /**
     * ,1 个人;2 企业
     */
    @Column(name = "servicenotice_membertype")
    private Integer servicenoticeMembertype;

    /**
     * 用户配置数量,
     */
    @Column(name = "servicenotice_servicecount")
    private Integer servicenoticeServicecount;

    /**
     * 提醒生成时间,
     */
    @Column(name = "servicenotice_time")
    private Date servicenoticeTime;

    /**
     * 状态,1 提醒;2 已读
     */
    @Column(name = "servicenotice_status")
    private Integer servicenoticeStatus;

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
     * 获取提醒id,
     *
     * @return servicenotice_id - 提醒id,
     */
    public String getServicenoticeId() {
        return servicenoticeId;
    }

    /**
     * 设置提醒id,
     *
     * @param servicenoticeId 提醒id,
     */
    public void setServicenoticeId(String servicenoticeId) {
        this.servicenoticeId = servicenoticeId;
    }

    /**
     * 获取服务代码,
     *
     * @return servicenotice_servicecode - 服务代码,
     */
    public String getServicenoticeServicecode() {
        return servicenoticeServicecode;
    }

    /**
     * 设置服务代码,
     *
     * @param servicenoticeServicecode 服务代码,
     */
    public void setServicenoticeServicecode(String servicenoticeServicecode) {
        this.servicenoticeServicecode = servicenoticeServicecode;
    }

    /**
     * 获取对应服务id,
     *
     * @return servicenotice_serviceid - 对应服务id,
     */
    public String getServicenoticeServiceid() {
        return servicenoticeServiceid;
    }

    /**
     * 设置对应服务id,
     *
     * @param servicenoticeServiceid 对应服务id,
     */
    public void setServicenoticeServiceid(String servicenoticeServiceid) {
        this.servicenoticeServiceid = servicenoticeServiceid;
    }

    /**
     * 获取服务名称,
     *
     * @return servicenotice_name - 服务名称,
     */
    public String getServicenoticeName() {
        return servicenoticeName;
    }

    /**
     * 设置服务名称,
     *
     * @param servicenoticeName 服务名称,
     */
    public void setServicenoticeName(String servicenoticeName) {
        this.servicenoticeName = servicenoticeName;
    }

    /**
     * 获取余额,
     *
     * @return servicenotice_balance - 余额,
     */
    public Integer getServicenoticeBalance() {
        return servicenoticeBalance;
    }

    /**
     * 设置余额,
     *
     * @param servicenoticeBalance 余额,
     */
    public void setServicenoticeBalance(Integer servicenoticeBalance) {
        this.servicenoticeBalance = servicenoticeBalance;
    }

    /**
     * 获取对应用户,
     *
     * @return servicenotice_memberid - 对应用户,
     */
    public String getServicenoticeMemberid() {
        return servicenoticeMemberid;
    }

    /**
     * 设置对应用户,
     *
     * @param servicenoticeMemberid 对应用户,
     */
    public void setServicenoticeMemberid(String servicenoticeMemberid) {
        this.servicenoticeMemberid = servicenoticeMemberid;
    }

    /**
     * 获取,1 个人;2 企业
     *
     * @return servicenotice_membertype - ,1 个人;2 企业
     */
    public Integer getServicenoticeMembertype() {
        return servicenoticeMembertype;
    }

    /**
     * 设置,1 个人;2 企业
     *
     * @param servicenoticeMembertype ,1 个人;2 企业
     */
    public void setServicenoticeMembertype(Integer servicenoticeMembertype) {
        this.servicenoticeMembertype = servicenoticeMembertype;
    }

    /**
     * 获取用户配置数量,
     *
     * @return servicenotice_servicecount - 用户配置数量,
     */
    public Integer getServicenoticeServicecount() {
        return servicenoticeServicecount;
    }

    /**
     * 设置用户配置数量,
     *
     * @param servicenoticeServicecount 用户配置数量,
     */
    public void setServicenoticeServicecount(Integer servicenoticeServicecount) {
        this.servicenoticeServicecount = servicenoticeServicecount;
    }

    /**
     * 获取提醒生成时间,
     *
     * @return servicenotice_time - 提醒生成时间,
     */
    public Date getServicenoticeTime() {
        return servicenoticeTime;
    }

    /**
     * 设置提醒生成时间,
     *
     * @param servicenoticeTime 提醒生成时间,
     */
    public void setServicenoticeTime(Date servicenoticeTime) {
        this.servicenoticeTime = servicenoticeTime;
    }

    /**
     * 获取状态,1 提醒;2 已读
     *
     * @return servicenotice_status - 状态,1 提醒;2 已读
     */
    public Integer getServicenoticeStatus() {
        return servicenoticeStatus;
    }

    /**
     * 设置状态,1 提醒;2 已读
     *
     * @param servicenoticeStatus 状态,1 提醒;2 已读
     */
    public void setServicenoticeStatus(Integer servicenoticeStatus) {
        this.servicenoticeStatus = servicenoticeStatus;
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