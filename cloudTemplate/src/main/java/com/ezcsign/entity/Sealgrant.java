package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sealgrant")
public class Sealgrant {
    /**
     * 印章使用授权id,
     */
    @Id
    @Column(name = "sealgrant_id")
    @GeneratedValue(generator = "JDBC")
    private String sealgrantId;

    /**
     * 授权节点id,
     */
    @Column(name = "sealgrant_memberid")
    private String sealgrantMemberid;

    /**
     * 1 个人;2 企业,
     */
    @Column(name = "sealgrant_membertype")
    private Integer sealgrantMembertype;

    /**
     * 授权印章id,
     */
    @Column(name = "sealgrant_sealid")
    private String sealgrantSealid;

    /**
     * 授权开始时间,
     */
    @Column(name = "sealgrant_fromtime")
    private Date sealgrantFromtime;

    /**
     * 授权结束时间,
     */
    @Column(name = "sealgrant_totime")
    private Date sealgrantTotime;

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
     * 获取印章使用授权id,
     *
     * @return sealgrant_id - 印章使用授权id,
     */
    public String getSealgrantId() {
        return sealgrantId;
    }

    /**
     * 设置印章使用授权id,
     *
     * @param sealgrantId 印章使用授权id,
     */
    public void setSealgrantId(String sealgrantId) {
        this.sealgrantId = sealgrantId;
    }

    /**
     * 获取授权节点id,
     *
     * @return sealgrant_memberid - 授权节点id,
     */
    public String getSealgrantMemberid() {
        return sealgrantMemberid;
    }

    /**
     * 设置授权节点id,
     *
     * @param sealgrantMemberid 授权节点id,
     */
    public void setSealgrantMemberid(String sealgrantMemberid) {
        this.sealgrantMemberid = sealgrantMemberid;
    }

    /**
     * 获取1 个人;2 企业,
     *
     * @return sealgrant_membertype - 1 个人;2 企业,
     */
    public Integer getSealgrantMembertype() {
        return sealgrantMembertype;
    }

    /**
     * 设置1 个人;2 企业,
     *
     * @param sealgrantMembertype 1 个人;2 企业,
     */
    public void setSealgrantMembertype(Integer sealgrantMembertype) {
        this.sealgrantMembertype = sealgrantMembertype;
    }

    /**
     * 获取授权印章id,
     *
     * @return sealgrant_sealid - 授权印章id,
     */
    public String getSealgrantSealid() {
        return sealgrantSealid;
    }

    /**
     * 设置授权印章id,
     *
     * @param sealgrantSealid 授权印章id,
     */
    public void setSealgrantSealid(String sealgrantSealid) {
        this.sealgrantSealid = sealgrantSealid;
    }

    /**
     * 获取授权开始时间,
     *
     * @return sealgrant_fromtime - 授权开始时间,
     */
    public Date getSealgrantFromtime() {
        return sealgrantFromtime;
    }

    /**
     * 设置授权开始时间,
     *
     * @param sealgrantFromtime 授权开始时间,
     */
    public void setSealgrantFromtime(Date sealgrantFromtime) {
        this.sealgrantFromtime = sealgrantFromtime;
    }

    /**
     * 获取授权结束时间,
     *
     * @return sealgrant_totime - 授权结束时间,
     */
    public Date getSealgrantTotime() {
        return sealgrantTotime;
    }

    /**
     * 设置授权结束时间,
     *
     * @param sealgrantTotime 授权结束时间,
     */
    public void setSealgrantTotime(Date sealgrantTotime) {
        this.sealgrantTotime = sealgrantTotime;
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