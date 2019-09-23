package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "filerelation")
public class Filerelation {
    /**
     * 档案查询权限表id
     */
    @Id
    @Column(name = "filerelation_id")
    @GeneratedValue(generator = "JDBC")
    private String filerelationId;

    /**
     * 企业档案表id
     */
    @Column(name = "filerelation_comfileid")
    private String filerelationComfileid;

    /**
     * 成员节点表id
     */
    @Column(name = "filerelation_memberid")
    private String filerelationMemberid;

    /**
     * 1.个人2.企业
     */
    @Column(name = "filerelation_membertype")
    private Integer filerelationMembertype;

    /**
     * 授权起始时间
     */
    @Column(name = "filerelation_from")
    private Date filerelationFrom;

    /**
     * 授权结束时间
     */
    @Column(name = "filerelation_to")
    private Date filerelationTo;

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
     * 获取档案查询权限表id
     *
     * @return filerelation_id - 档案查询权限表id
     */
    public String getFilerelationId() {
        return filerelationId;
    }

    /**
     * 设置档案查询权限表id
     *
     * @param filerelationId 档案查询权限表id
     */
    public void setFilerelationId(String filerelationId) {
        this.filerelationId = filerelationId;
    }

    /**
     * 获取企业档案表id
     *
     * @return filerelation_comfileid - 企业档案表id
     */
    public String getFilerelationComfileid() {
        return filerelationComfileid;
    }

    /**
     * 设置企业档案表id
     *
     * @param filerelationComfileid 企业档案表id
     */
    public void setFilerelationComfileid(String filerelationComfileid) {
        this.filerelationComfileid = filerelationComfileid;
    }

    /**
     * 获取成员节点表id
     *
     * @return filerelation_memberid - 成员节点表id
     */
    public String getFilerelationMemberid() {
        return filerelationMemberid;
    }

    /**
     * 设置成员节点表id
     *
     * @param filerelationMemberid 成员节点表id
     */
    public void setFilerelationMemberid(String filerelationMemberid) {
        this.filerelationMemberid = filerelationMemberid;
    }

    /**
     * 获取1.个人2.企业
     *
     * @return filerelation_membertype - 1.个人2.企业
     */
    public Integer getFilerelationMembertype() {
        return filerelationMembertype;
    }

    /**
     * 设置1.个人2.企业
     *
     * @param filerelationMembertype 1.个人2.企业
     */
    public void setFilerelationMembertype(Integer filerelationMembertype) {
        this.filerelationMembertype = filerelationMembertype;
    }

    /**
     * 获取授权起始时间
     *
     * @return filerelation_from - 授权起始时间
     */
    public Date getFilerelationFrom() {
        return filerelationFrom;
    }

    /**
     * 设置授权起始时间
     *
     * @param filerelationFrom 授权起始时间
     */
    public void setFilerelationFrom(Date filerelationFrom) {
        this.filerelationFrom = filerelationFrom;
    }

    /**
     * 获取授权结束时间
     *
     * @return filerelation_to - 授权结束时间
     */
    public Date getFilerelationTo() {
        return filerelationTo;
    }

    /**
     * 设置授权结束时间
     *
     * @param filerelationTo 授权结束时间
     */
    public void setFilerelationTo(Date filerelationTo) {
        this.filerelationTo = filerelationTo;
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