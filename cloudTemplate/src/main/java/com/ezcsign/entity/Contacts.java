package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "contacts")
public class Contacts {
    @Id
    @Column(name = "contacts_id")
    @GeneratedValue(generator = "JDBC")
    private String contactsId;

    /**
     * 分组Id
     */
    @Column(name = "contacts_contactsgroupid")
    private String contactsContactsgroupid;

    /**
     * 联系人的id,
     */
    @Column(name = "contacts_memberid")
    private String contactsMemberid;

    /**
     * 联系类型:1 个人;2 企业
     */
    @Column(name = "contacts_membertype")
    private Integer contactsMembertype;

    /**
     * 备注信息,
     */
    @Column(name = "contacts_explain")
    private String contactsExplain;

    /**
     * 来源,1 企业组织;2 自动保存;3 分享
     */
    @Column(name = "contacts_fromtype")
    private Integer contactsFromtype;

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
     * @return contacts_id
     */
    public String getContactsId() {
        return contactsId;
    }

    /**
     * @param contactsId
     */
    public void setContactsId(String contactsId) {
        this.contactsId = contactsId;
    }

    /**
     * 获取分组Id
     *
     * @return contacts_contactsgroupid - 分组Id
     */
    public String getContactsContactsgroupid() {
        return contactsContactsgroupid;
    }

    /**
     * 设置分组Id
     *
     * @param contactsContactsgroupid 分组Id
     */
    public void setContactsContactsgroupid(String contactsContactsgroupid) {
        this.contactsContactsgroupid = contactsContactsgroupid;
    }

    /**
     * 获取联系人的id,
     *
     * @return contacts_memberid - 联系人的id,
     */
    public String getContactsMemberid() {
        return contactsMemberid;
    }

    /**
     * 设置联系人的id,
     *
     * @param contactsMemberid 联系人的id,
     */
    public void setContactsMemberid(String contactsMemberid) {
        this.contactsMemberid = contactsMemberid;
    }

    /**
     * 获取联系类型:1 个人;2 企业
     *
     * @return contacts_membertype - 联系类型:1 个人;2 企业
     */
    public Integer getContactsMembertype() {
        return contactsMembertype;
    }

    /**
     * 设置联系类型:1 个人;2 企业
     *
     * @param contactsMembertype 联系类型:1 个人;2 企业
     */
    public void setContactsMembertype(Integer contactsMembertype) {
        this.contactsMembertype = contactsMembertype;
    }

    /**
     * 获取备注信息,
     *
     * @return contacts_explain - 备注信息,
     */
    public String getContactsExplain() {
        return contactsExplain;
    }

    /**
     * 设置备注信息,
     *
     * @param contactsExplain 备注信息,
     */
    public void setContactsExplain(String contactsExplain) {
        this.contactsExplain = contactsExplain;
    }

    /**
     * 获取来源,1 企业组织;2 自动保存;3 分享
     *
     * @return contacts_fromtype - 来源,1 企业组织;2 自动保存;3 分享
     */
    public Integer getContactsFromtype() {
        return contactsFromtype;
    }

    /**
     * 设置来源,1 企业组织;2 自动保存;3 分享
     *
     * @param contactsFromtype 来源,1 企业组织;2 自动保存;3 分享
     */
    public void setContactsFromtype(Integer contactsFromtype) {
        this.contactsFromtype = contactsFromtype;
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