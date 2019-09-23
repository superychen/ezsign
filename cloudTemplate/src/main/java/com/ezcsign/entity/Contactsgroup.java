package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "contactsgroup")
public class Contactsgroup {
    /**
     * 分组id,
     */
    @Id
    @Column(name = "contactsgroup_id")
    @GeneratedValue(generator = "JDBC")
    private String contactsgroupId;

    /**
     * 对应的用户id,
     */
    @Column(name = "contactsgroup_memberid")
    private String contactsgroupMemberid;

    /**
     * ,1 个人;2 企业
     */
    @Column(name = "contactsgroup_membertype")
    private Integer contactsgroupMembertype;

    /**
     * 分组名称,
     */
    @Column(name = "contactsgroup_name")
    private String contactsgroupName;

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
     * 获取分组id,
     *
     * @return contactsgroup_id - 分组id,
     */
    public String getContactsgroupId() {
        return contactsgroupId;
    }

    /**
     * 设置分组id,
     *
     * @param contactsgroupId 分组id,
     */
    public void setContactsgroupId(String contactsgroupId) {
        this.contactsgroupId = contactsgroupId;
    }

    /**
     * 获取对应的用户id,
     *
     * @return contactsgroup_memberid - 对应的用户id,
     */
    public String getContactsgroupMemberid() {
        return contactsgroupMemberid;
    }

    /**
     * 设置对应的用户id,
     *
     * @param contactsgroupMemberid 对应的用户id,
     */
    public void setContactsgroupMemberid(String contactsgroupMemberid) {
        this.contactsgroupMemberid = contactsgroupMemberid;
    }

    /**
     * 获取,1 个人;2 企业
     *
     * @return contactsgroup_membertype - ,1 个人;2 企业
     */
    public Integer getContactsgroupMembertype() {
        return contactsgroupMembertype;
    }

    /**
     * 设置,1 个人;2 企业
     *
     * @param contactsgroupMembertype ,1 个人;2 企业
     */
    public void setContactsgroupMembertype(Integer contactsgroupMembertype) {
        this.contactsgroupMembertype = contactsgroupMembertype;
    }

    /**
     * 获取分组名称,
     *
     * @return contactsgroup_name - 分组名称,
     */
    public String getContactsgroupName() {
        return contactsgroupName;
    }

    /**
     * 设置分组名称,
     *
     * @param contactsgroupName 分组名称,
     */
    public void setContactsgroupName(String contactsgroupName) {
        this.contactsgroupName = contactsgroupName;
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