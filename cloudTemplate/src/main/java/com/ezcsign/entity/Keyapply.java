package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "keyapply")
public class Keyapply {
    /**
     * 申请id,
     */
    @Id
    @Column(name = "keyapply_id")
    @GeneratedValue(generator = "JDBC")
    private String keyapplyId;

    /**
     * 对应用户id,
     */
    @Column(name = "keyapply_memberid")
    private String keyapplyMemberid;

    /**
     * ,1 个人;2 企业
     */
    @Column(name = "keyapply_membertype")
    private Integer keyapplyMembertype;

    /**
     * 申请时间,
     */
    @Column(name = "keyapply_applytime")
    private Date keyapplyApplytime;

    /**
     * 申请地址,
     */
    @Column(name = "keyapply_address")
    private String keyapplyAddress;

    /**
     * 联系人,
     */
    @Column(name = "keyapply_contactor")
    private String keyapplyContactor;

    /**
     * 联系电话,
     */
    @Column(name = "keyapply_telephone")
    private String keyapplyTelephone;

    /**
     * 状态,
     */
    @Column(name = "keyapply_status")
    private Integer keyapplyStatus;

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
     * 获取申请id,
     *
     * @return keyapply_id - 申请id,
     */
    public String getKeyapplyId() {
        return keyapplyId;
    }

    /**
     * 设置申请id,
     *
     * @param keyapplyId 申请id,
     */
    public void setKeyapplyId(String keyapplyId) {
        this.keyapplyId = keyapplyId;
    }

    /**
     * 获取对应用户id,
     *
     * @return keyapply_memberid - 对应用户id,
     */
    public String getKeyapplyMemberid() {
        return keyapplyMemberid;
    }

    /**
     * 设置对应用户id,
     *
     * @param keyapplyMemberid 对应用户id,
     */
    public void setKeyapplyMemberid(String keyapplyMemberid) {
        this.keyapplyMemberid = keyapplyMemberid;
    }

    /**
     * 获取,1 个人;2 企业
     *
     * @return keyapply_membertype - ,1 个人;2 企业
     */
    public Integer getKeyapplyMembertype() {
        return keyapplyMembertype;
    }

    /**
     * 设置,1 个人;2 企业
     *
     * @param keyapplyMembertype ,1 个人;2 企业
     */
    public void setKeyapplyMembertype(Integer keyapplyMembertype) {
        this.keyapplyMembertype = keyapplyMembertype;
    }

    /**
     * 获取申请时间,
     *
     * @return keyapply_applytime - 申请时间,
     */
    public Date getKeyapplyApplytime() {
        return keyapplyApplytime;
    }

    /**
     * 设置申请时间,
     *
     * @param keyapplyApplytime 申请时间,
     */
    public void setKeyapplyApplytime(Date keyapplyApplytime) {
        this.keyapplyApplytime = keyapplyApplytime;
    }

    /**
     * 获取申请地址,
     *
     * @return keyapply_address - 申请地址,
     */
    public String getKeyapplyAddress() {
        return keyapplyAddress;
    }

    /**
     * 设置申请地址,
     *
     * @param keyapplyAddress 申请地址,
     */
    public void setKeyapplyAddress(String keyapplyAddress) {
        this.keyapplyAddress = keyapplyAddress;
    }

    /**
     * 获取联系人,
     *
     * @return keyapply_contactor - 联系人,
     */
    public String getKeyapplyContactor() {
        return keyapplyContactor;
    }

    /**
     * 设置联系人,
     *
     * @param keyapplyContactor 联系人,
     */
    public void setKeyapplyContactor(String keyapplyContactor) {
        this.keyapplyContactor = keyapplyContactor;
    }

    /**
     * 获取联系电话,
     *
     * @return keyapply_telephone - 联系电话,
     */
    public String getKeyapplyTelephone() {
        return keyapplyTelephone;
    }

    /**
     * 设置联系电话,
     *
     * @param keyapplyTelephone 联系电话,
     */
    public void setKeyapplyTelephone(String keyapplyTelephone) {
        this.keyapplyTelephone = keyapplyTelephone;
    }

    /**
     * 获取状态,
     *
     * @return keyapply_status - 状态,
     */
    public Integer getKeyapplyStatus() {
        return keyapplyStatus;
    }

    /**
     * 设置状态,
     *
     * @param keyapplyStatus 状态,
     */
    public void setKeyapplyStatus(Integer keyapplyStatus) {
        this.keyapplyStatus = keyapplyStatus;
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