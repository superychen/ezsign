package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "templetgrant")
public class Templetgrant {
    /**
     * 模板关系,
     */
    @Id
    @Column(name = "templetgrant_id")
    @GeneratedValue(generator = "JDBC")
    private String templetgrantId;

    /**
     * 模板对应用户id,（对应personinfo或member的id）
     */
    @Column(name = "templetgrant_memberid")
    private String templetgrantMemberid;

    /**
     * 用户类型：1 个人;2 企业
     */
    @Column(name = "templetgrant_membertype")
    private Integer templetgrantMembertype;

    /**
     * 对应模板的id
     */
    @Column(name = "templetgrant_usertempletid")
    private String templetgrantUsertempletid;

    /**
     * 起始时间
     */
    @Column(name = "templetgrant_from")
    private Date templetgrantFrom;

    /**
     * 截止时间,
     */
    @Column(name = "templetgrant_to")
    private Date templetgrantTo;

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
     * @return templetgrant_id - 模板关系,
     */
    public String getTempletgrantId() {
        return templetgrantId;
    }

    /**
     * 设置模板关系,
     *
     * @param templetgrantId 模板关系,
     */
    public void setTempletgrantId(String templetgrantId) {
        this.templetgrantId = templetgrantId;
    }

    /**
     * 获取模板对应用户id,（对应personinfo或member的id）
     *
     * @return templetgrant_memberid - 模板对应用户id,（对应personinfo或member的id）
     */
    public String getTempletgrantMemberid() {
        return templetgrantMemberid;
    }

    /**
     * 设置模板对应用户id,（对应personinfo或member的id）
     *
     * @param templetgrantMemberid 模板对应用户id,（对应personinfo或member的id）
     */
    public void setTempletgrantMemberid(String templetgrantMemberid) {
        this.templetgrantMemberid = templetgrantMemberid;
    }

    /**
     * 获取用户类型：1 个人;2 企业
     *
     * @return templetgrant_membertype - 用户类型：1 个人;2 企业
     */
    public Integer getTempletgrantMembertype() {
        return templetgrantMembertype;
    }

    /**
     * 设置用户类型：1 个人;2 企业
     *
     * @param templetgrantMembertype 用户类型：1 个人;2 企业
     */
    public void setTempletgrantMembertype(Integer templetgrantMembertype) {
        this.templetgrantMembertype = templetgrantMembertype;
    }

    /**
     * 获取对应模板的id
     *
     * @return templetgrant_usertempletid - 对应模板的id
     */
    public String getTempletgrantUsertempletid() {
        return templetgrantUsertempletid;
    }

    /**
     * 设置对应模板的id
     *
     * @param templetgrantUsertempletid 对应模板的id
     */
    public void setTempletgrantUsertempletid(String templetgrantUsertempletid) {
        this.templetgrantUsertempletid = templetgrantUsertempletid;
    }

    /**
     * 获取起始时间
     *
     * @return templetgrant_from - 起始时间
     */
    public Date getTempletgrantFrom() {
        return templetgrantFrom;
    }

    /**
     * 设置起始时间
     *
     * @param templetgrantFrom 起始时间
     */
    public void setTempletgrantFrom(Date templetgrantFrom) {
        this.templetgrantFrom = templetgrantFrom;
    }

    /**
     * 获取截止时间,
     *
     * @return templetgrant_to - 截止时间,
     */
    public Date getTempletgrantTo() {
        return templetgrantTo;
    }

    /**
     * 设置截止时间,
     *
     * @param templetgrantTo 截止时间,
     */
    public void setTempletgrantTo(Date templetgrantTo) {
        this.templetgrantTo = templetgrantTo;
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