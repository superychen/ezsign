package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "signcount")
public class Signcount {
    /**
     * 用户计数id,
     */
    @Id
    @Column(name = "signcount_id")
    @GeneratedValue(generator = "JDBC")
    private String signcountId;

    /**
     * 对应用户的id,
     */
    @Column(name = "signcount_memberid")
    private String signcountMemberid;

    /**
     * 用户账号类型,
     */
    @Column(name = "signcount_membertype")
    private Integer signcountMembertype;

    /**
     * 用户发起的签约数量,
     */
    @Column(name = "signcount_requestcount")
    private Integer signcountRequestcount;

    /**
     * 收到的总数量,
     */
    @Column(name = "signcount_signerinfocount")
    private Integer signcountSignerinfocount;

    /**
     * 草稿数量,
     */
    @Column(name = "signcount_draftcount")
    private Integer signcountDraftcount;

    /**
     * 待我签署的数量,
     */
    @Column(name = "signcount_unsigncount")
    private Integer signcountUnsigncount;

    /**
     * 待我审批的数量,
     */
    @Column(name = "signcount_unapprovedcount")
    private Integer signcountUnapprovedcount;

    /**
     * 待他人操作的数量（request中未完成[未审批、未签署、部分签署]）,
     */
    @Column(name = "signcount_unfinishedcount")
    private Integer signcountUnfinishedcount;

    /**
     * 已过期数量统计,
     */
    @Column(name = "signcount_expirecount")
    private Integer signcountExpirecount;

    /**
     * 审批未通过,
     */
    @Column(name = "signcount_rejectcount")
    private Integer signcountRejectcount;

    /**
     * 拒签数量,
     */
    @Column(name = "signcount_refusecount")
    private Integer signcountRefusecount;

    /**
     * 审批完成数量,
     */
    @Column(name = "signcount_approvalcount")
    private Integer signcountApprovalcount;

    /**
     * 抄送文件数量,
     */
    @Column(name = "signcount_copycount")
    private Integer signcountCopycount;

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
     * 获取用户计数id,
     *
     * @return signcount_id - 用户计数id,
     */
    public String getSigncountId() {
        return signcountId;
    }

    /**
     * 设置用户计数id,
     *
     * @param signcountId 用户计数id,
     */
    public void setSigncountId(String signcountId) {
        this.signcountId = signcountId;
    }

    /**
     * 获取对应用户的id,
     *
     * @return signcount_memberid - 对应用户的id,
     */
    public String getSigncountMemberid() {
        return signcountMemberid;
    }

    /**
     * 设置对应用户的id,
     *
     * @param signcountMemberid 对应用户的id,
     */
    public void setSigncountMemberid(String signcountMemberid) {
        this.signcountMemberid = signcountMemberid;
    }

    /**
     * 获取用户账号类型,
     *
     * @return signcount_membertype - 用户账号类型,
     */
    public Integer getSigncountMembertype() {
        return signcountMembertype;
    }

    /**
     * 设置用户账号类型,
     *
     * @param signcountMembertype 用户账号类型,
     */
    public void setSigncountMembertype(Integer signcountMembertype) {
        this.signcountMembertype = signcountMembertype;
    }

    /**
     * 获取用户发起的签约数量,
     *
     * @return signcount_requestcount - 用户发起的签约数量,
     */
    public Integer getSigncountRequestcount() {
        return signcountRequestcount;
    }

    /**
     * 设置用户发起的签约数量,
     *
     * @param signcountRequestcount 用户发起的签约数量,
     */
    public void setSigncountRequestcount(Integer signcountRequestcount) {
        this.signcountRequestcount = signcountRequestcount;
    }

    /**
     * 获取收到的总数量,
     *
     * @return signcount_signerinfocount - 收到的总数量,
     */
    public Integer getSigncountSignerinfocount() {
        return signcountSignerinfocount;
    }

    /**
     * 设置收到的总数量,
     *
     * @param signcountSignerinfocount 收到的总数量,
     */
    public void setSigncountSignerinfocount(Integer signcountSignerinfocount) {
        this.signcountSignerinfocount = signcountSignerinfocount;
    }

    /**
     * 获取草稿数量,
     *
     * @return signcount_draftcount - 草稿数量,
     */
    public Integer getSigncountDraftcount() {
        return signcountDraftcount;
    }

    /**
     * 设置草稿数量,
     *
     * @param signcountDraftcount 草稿数量,
     */
    public void setSigncountDraftcount(Integer signcountDraftcount) {
        this.signcountDraftcount = signcountDraftcount;
    }

    /**
     * 获取待我签署的数量,
     *
     * @return signcount_unsigncount - 待我签署的数量,
     */
    public Integer getSigncountUnsigncount() {
        return signcountUnsigncount;
    }

    /**
     * 设置待我签署的数量,
     *
     * @param signcountUnsigncount 待我签署的数量,
     */
    public void setSigncountUnsigncount(Integer signcountUnsigncount) {
        this.signcountUnsigncount = signcountUnsigncount;
    }

    /**
     * 获取待我审批的数量,
     *
     * @return signcount_unapprovedcount - 待我审批的数量,
     */
    public Integer getSigncountUnapprovedcount() {
        return signcountUnapprovedcount;
    }

    /**
     * 设置待我审批的数量,
     *
     * @param signcountUnapprovedcount 待我审批的数量,
     */
    public void setSigncountUnapprovedcount(Integer signcountUnapprovedcount) {
        this.signcountUnapprovedcount = signcountUnapprovedcount;
    }

    /**
     * 获取待他人操作的数量（request中未完成[未审批、未签署、部分签署]）,
     *
     * @return signcount_unfinishedcount - 待他人操作的数量（request中未完成[未审批、未签署、部分签署]）,
     */
    public Integer getSigncountUnfinishedcount() {
        return signcountUnfinishedcount;
    }

    /**
     * 设置待他人操作的数量（request中未完成[未审批、未签署、部分签署]）,
     *
     * @param signcountUnfinishedcount 待他人操作的数量（request中未完成[未审批、未签署、部分签署]）,
     */
    public void setSigncountUnfinishedcount(Integer signcountUnfinishedcount) {
        this.signcountUnfinishedcount = signcountUnfinishedcount;
    }

    /**
     * 获取已过期数量统计,
     *
     * @return signcount_expirecount - 已过期数量统计,
     */
    public Integer getSigncountExpirecount() {
        return signcountExpirecount;
    }

    /**
     * 设置已过期数量统计,
     *
     * @param signcountExpirecount 已过期数量统计,
     */
    public void setSigncountExpirecount(Integer signcountExpirecount) {
        this.signcountExpirecount = signcountExpirecount;
    }

    /**
     * 获取审批未通过,
     *
     * @return signcount_rejectcount - 审批未通过,
     */
    public Integer getSigncountRejectcount() {
        return signcountRejectcount;
    }

    /**
     * 设置审批未通过,
     *
     * @param signcountRejectcount 审批未通过,
     */
    public void setSigncountRejectcount(Integer signcountRejectcount) {
        this.signcountRejectcount = signcountRejectcount;
    }

    /**
     * 获取拒签数量,
     *
     * @return signcount_refusecount - 拒签数量,
     */
    public Integer getSigncountRefusecount() {
        return signcountRefusecount;
    }

    /**
     * 设置拒签数量,
     *
     * @param signcountRefusecount 拒签数量,
     */
    public void setSigncountRefusecount(Integer signcountRefusecount) {
        this.signcountRefusecount = signcountRefusecount;
    }

    /**
     * 获取审批完成数量,
     *
     * @return signcount_approvalcount - 审批完成数量,
     */
    public Integer getSigncountApprovalcount() {
        return signcountApprovalcount;
    }

    /**
     * 设置审批完成数量,
     *
     * @param signcountApprovalcount 审批完成数量,
     */
    public void setSigncountApprovalcount(Integer signcountApprovalcount) {
        this.signcountApprovalcount = signcountApprovalcount;
    }

    /**
     * 获取抄送文件数量,
     *
     * @return signcount_copycount - 抄送文件数量,
     */
    public Integer getSigncountCopycount() {
        return signcountCopycount;
    }

    /**
     * 设置抄送文件数量,
     *
     * @param signcountCopycount 抄送文件数量,
     */
    public void setSigncountCopycount(Integer signcountCopycount) {
        this.signcountCopycount = signcountCopycount;
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