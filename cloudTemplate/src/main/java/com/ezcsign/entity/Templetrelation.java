package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "templetrelation")
public class Templetrelation {
    /**
     * 模板关系,
     */
    @Id
    @Column(name = "templetrelation_id")
    @GeneratedValue(generator = "JDBC")
    private String templetrelationId;

    /**
     * 模板对应用户id,（对应personinfo或member的id）
     */
    @Column(name = "templetrelation_memberid")
    private String templetrelationMemberid;

    /**
     * 用户类型：1 个人;2 企业
     */
    @Column(name = "templetrelation_membertype")
    private Integer templetrelationMembertype;

    /**
     * 对应模板的id
     */
    @Column(name = "templetrelation_usertempletid")
    private String templetrelationUsertempletid;

    /**
     * 起始时间
     */
    @Column(name = "templetrelation_from")
    private Date templetrelationFrom;

    /**
     * 截止时间,
     */
    @Column(name = "templetrelation_to")
    private Date templetrelationTo;

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
     * 获取模板关系,
     *
     * @return templetrelation_id - 模板关系,
     */
    public String getTempletrelationId() {
        return templetrelationId;
    }

    /**
     * 设置模板关系,
     *
     * @param templetrelationId 模板关系,
     */
    public void setTempletrelationId(String templetrelationId) {
        this.templetrelationId = templetrelationId;
    }

    /**
     * 获取模板对应用户id,（对应personinfo或member的id）
     *
     * @return templetrelation_memberid - 模板对应用户id,（对应personinfo或member的id）
     */
    public String getTempletrelationMemberid() {
        return templetrelationMemberid;
    }

    /**
     * 设置模板对应用户id,（对应personinfo或member的id）
     *
     * @param templetrelationMemberid 模板对应用户id,（对应personinfo或member的id）
     */
    public void setTempletrelationMemberid(String templetrelationMemberid) {
        this.templetrelationMemberid = templetrelationMemberid;
    }

    /**
     * 获取用户类型：1 个人;2 企业
     *
     * @return templetrelation_membertype - 用户类型：1 个人;2 企业
     */
    public Integer getTempletrelationMembertype() {
        return templetrelationMembertype;
    }

    /**
     * 设置用户类型：1 个人;2 企业
     *
     * @param templetrelationMembertype 用户类型：1 个人;2 企业
     */
    public void setTempletrelationMembertype(Integer templetrelationMembertype) {
        this.templetrelationMembertype = templetrelationMembertype;
    }

    /**
     * 获取对应模板的id
     *
     * @return templetrelation_usertempletid - 对应模板的id
     */
    public String getTempletrelationUsertempletid() {
        return templetrelationUsertempletid;
    }

    /**
     * 设置对应模板的id
     *
     * @param templetrelationUsertempletid 对应模板的id
     */
    public void setTempletrelationUsertempletid(String templetrelationUsertempletid) {
        this.templetrelationUsertempletid = templetrelationUsertempletid;
    }

    /**
     * 获取起始时间
     *
     * @return templetrelation_from - 起始时间
     */
    public Date getTempletrelationFrom() {
        return templetrelationFrom;
    }

    /**
     * 设置起始时间
     *
     * @param templetrelationFrom 起始时间
     */
    public void setTempletrelationFrom(Date templetrelationFrom) {
        this.templetrelationFrom = templetrelationFrom;
    }

    /**
     * 获取截止时间,
     *
     * @return templetrelation_to - 截止时间,
     */
    public Date getTempletrelationTo() {
        return templetrelationTo;
    }

    /**
     * 设置截止时间,
     *
     * @param templetrelationTo 截止时间,
     */
    public void setTempletrelationTo(Date templetrelationTo) {
        this.templetrelationTo = templetrelationTo;
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