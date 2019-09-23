package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "signgrant")
public class Signgrant {
    @Id
    @Column(name = "signgrant_id")
    @GeneratedValue(generator = "JDBC")
    private String signgrantId;

    @Column(name = "signgrant_signrequestid")
    private String signgrantSignrequestid;

    @Column(name = "signgrant_memberid")
    private String signgrantMemberid;

    /**
     * 1 个人 personinfo的id 2 企业memberinfo的id
     */
    @Column(name = "signgrant_membertype")
    private Integer signgrantMembertype;

    /**
     * ,1 发起人授权;2 签署人授权;3 审批人授权;4 抄送人授权;5 管理员授权
     */
    @Column(name = "signgrant_granttype")
    private Integer signgrantGranttype;

    @Column(name = "signgrant_from")
    private Date signgrantFrom;

    @Column(name = "signgrant_to")
    private Date signgrantTo;

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
     * @return signgrant_id
     */
    public String getSigngrantId() {
        return signgrantId;
    }

    /**
     * @param signgrantId
     */
    public void setSigngrantId(String signgrantId) {
        this.signgrantId = signgrantId;
    }

    /**
     * @return signgrant_signrequestid
     */
    public String getSigngrantSignrequestid() {
        return signgrantSignrequestid;
    }

    /**
     * @param signgrantSignrequestid
     */
    public void setSigngrantSignrequestid(String signgrantSignrequestid) {
        this.signgrantSignrequestid = signgrantSignrequestid;
    }

    /**
     * @return signgrant_memberid
     */
    public String getSigngrantMemberid() {
        return signgrantMemberid;
    }

    /**
     * @param signgrantMemberid
     */
    public void setSigngrantMemberid(String signgrantMemberid) {
        this.signgrantMemberid = signgrantMemberid;
    }

    /**
     * 获取1 个人 personinfo的id 2 企业memberinfo的id
     *
     * @return signgrant_membertype - 1 个人 personinfo的id 2 企业memberinfo的id
     */
    public Integer getSigngrantMembertype() {
        return signgrantMembertype;
    }

    /**
     * 设置1 个人 personinfo的id 2 企业memberinfo的id
     *
     * @param signgrantMembertype 1 个人 personinfo的id 2 企业memberinfo的id
     */
    public void setSigngrantMembertype(Integer signgrantMembertype) {
        this.signgrantMembertype = signgrantMembertype;
    }

    /**
     * 获取,1 发起人授权;2 签署人授权;3 审批人授权;4 抄送人授权;5 管理员授权
     *
     * @return signgrant_granttype - ,1 发起人授权;2 签署人授权;3 审批人授权;4 抄送人授权;5 管理员授权
     */
    public Integer getSigngrantGranttype() {
        return signgrantGranttype;
    }

    /**
     * 设置,1 发起人授权;2 签署人授权;3 审批人授权;4 抄送人授权;5 管理员授权
     *
     * @param signgrantGranttype ,1 发起人授权;2 签署人授权;3 审批人授权;4 抄送人授权;5 管理员授权
     */
    public void setSigngrantGranttype(Integer signgrantGranttype) {
        this.signgrantGranttype = signgrantGranttype;
    }

    /**
     * @return signgrant_from
     */
    public Date getSigngrantFrom() {
        return signgrantFrom;
    }

    /**
     * @param signgrantFrom
     */
    public void setSigngrantFrom(Date signgrantFrom) {
        this.signgrantFrom = signgrantFrom;
    }

    /**
     * @return signgrant_to
     */
    public Date getSigngrantTo() {
        return signgrantTo;
    }

    /**
     * @param signgrantTo
     */
    public void setSigngrantTo(Date signgrantTo) {
        this.signgrantTo = signgrantTo;
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