package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "remindnote")
public class Remindnote {
    /**
     * 通知信息id,
     */
    @Id
    @Column(name = "remindnote_id")
    @GeneratedValue(generator = "JDBC")
    private String remindnoteId;

    /**
     * ,0 系统通知;1 起始时间通知;2 到期通知;3 审批通知;4 强通知
     */
    @Column(name = "remindnote_type")
    private Integer remindnoteType;

    /**
     * 通知关联id,
     */
    @Column(name = "remindnote_linkerid")
    private String remindnoteLinkerid;

    /**
     * 创建者,
     */
    @Column(name = "remindnote_creater")
    private String remindnoteCreater;

    /**
     * 创建时间,
     */
    @Column(name = "remindnote_creattime")
    private Date remindnoteCreattime;

    /**
     * 通知用户id,
     */
    @Column(name = "remindnote_reminduserid")
    private String remindnoteReminduserid;

    /**
     * 提醒开始时间,
     */
    @Column(name = "remindnote_fromtime")
    private Date remindnoteFromtime;

    /**
     * 提醒结束时间,
     */
    @Column(name = "remindnote_totime")
    private Date remindnoteTotime;

    /**
     * 通知状态,1 通知发起;2 已通知;3 已停止;4 已结束
     */
    @Column(name = "remindnote_status")
    private Integer remindnoteStatus;

    /**
     * 通知信息内容,
     */
    @Column(name = "remindnote_info")
    private String remindnoteInfo;

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
     * 获取通知信息id,
     *
     * @return remindnote_id - 通知信息id,
     */
    public String getRemindnoteId() {
        return remindnoteId;
    }

    /**
     * 设置通知信息id,
     *
     * @param remindnoteId 通知信息id,
     */
    public void setRemindnoteId(String remindnoteId) {
        this.remindnoteId = remindnoteId;
    }

    /**
     * 获取,0 系统通知;1 起始时间通知;2 到期通知;3 审批通知;4 强通知
     *
     * @return remindnote_type - ,0 系统通知;1 起始时间通知;2 到期通知;3 审批通知;4 强通知
     */
    public Integer getRemindnoteType() {
        return remindnoteType;
    }

    /**
     * 设置,0 系统通知;1 起始时间通知;2 到期通知;3 审批通知;4 强通知
     *
     * @param remindnoteType ,0 系统通知;1 起始时间通知;2 到期通知;3 审批通知;4 强通知
     */
    public void setRemindnoteType(Integer remindnoteType) {
        this.remindnoteType = remindnoteType;
    }

    /**
     * 获取通知关联id,
     *
     * @return remindnote_linkerid - 通知关联id,
     */
    public String getRemindnoteLinkerid() {
        return remindnoteLinkerid;
    }

    /**
     * 设置通知关联id,
     *
     * @param remindnoteLinkerid 通知关联id,
     */
    public void setRemindnoteLinkerid(String remindnoteLinkerid) {
        this.remindnoteLinkerid = remindnoteLinkerid;
    }

    /**
     * 获取创建者,
     *
     * @return remindnote_creater - 创建者,
     */
    public String getRemindnoteCreater() {
        return remindnoteCreater;
    }

    /**
     * 设置创建者,
     *
     * @param remindnoteCreater 创建者,
     */
    public void setRemindnoteCreater(String remindnoteCreater) {
        this.remindnoteCreater = remindnoteCreater;
    }

    /**
     * 获取创建时间,
     *
     * @return remindnote_creattime - 创建时间,
     */
    public Date getRemindnoteCreattime() {
        return remindnoteCreattime;
    }

    /**
     * 设置创建时间,
     *
     * @param remindnoteCreattime 创建时间,
     */
    public void setRemindnoteCreattime(Date remindnoteCreattime) {
        this.remindnoteCreattime = remindnoteCreattime;
    }

    /**
     * 获取通知用户id,
     *
     * @return remindnote_reminduserid - 通知用户id,
     */
    public String getRemindnoteReminduserid() {
        return remindnoteReminduserid;
    }

    /**
     * 设置通知用户id,
     *
     * @param remindnoteReminduserid 通知用户id,
     */
    public void setRemindnoteReminduserid(String remindnoteReminduserid) {
        this.remindnoteReminduserid = remindnoteReminduserid;
    }

    /**
     * 获取提醒开始时间,
     *
     * @return remindnote_fromtime - 提醒开始时间,
     */
    public Date getRemindnoteFromtime() {
        return remindnoteFromtime;
    }

    /**
     * 设置提醒开始时间,
     *
     * @param remindnoteFromtime 提醒开始时间,
     */
    public void setRemindnoteFromtime(Date remindnoteFromtime) {
        this.remindnoteFromtime = remindnoteFromtime;
    }

    /**
     * 获取提醒结束时间,
     *
     * @return remindnote_totime - 提醒结束时间,
     */
    public Date getRemindnoteTotime() {
        return remindnoteTotime;
    }

    /**
     * 设置提醒结束时间,
     *
     * @param remindnoteTotime 提醒结束时间,
     */
    public void setRemindnoteTotime(Date remindnoteTotime) {
        this.remindnoteTotime = remindnoteTotime;
    }

    /**
     * 获取通知状态,1 通知发起;2 已通知;3 已停止;4 已结束
     *
     * @return remindnote_status - 通知状态,1 通知发起;2 已通知;3 已停止;4 已结束
     */
    public Integer getRemindnoteStatus() {
        return remindnoteStatus;
    }

    /**
     * 设置通知状态,1 通知发起;2 已通知;3 已停止;4 已结束
     *
     * @param remindnoteStatus 通知状态,1 通知发起;2 已通知;3 已停止;4 已结束
     */
    public void setRemindnoteStatus(Integer remindnoteStatus) {
        this.remindnoteStatus = remindnoteStatus;
    }

    /**
     * 获取通知信息内容,
     *
     * @return remindnote_info - 通知信息内容,
     */
    public String getRemindnoteInfo() {
        return remindnoteInfo;
    }

    /**
     * 设置通知信息内容,
     *
     * @param remindnoteInfo 通知信息内容,
     */
    public void setRemindnoteInfo(String remindnoteInfo) {
        this.remindnoteInfo = remindnoteInfo;
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