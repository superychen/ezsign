package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "draft")
public class Draft {
    /**
     * 合同草稿id,
     */
    @Id
    @Column(name = "draft_id")
    @GeneratedValue(generator = "JDBC")
    private String draftId;

    /**
     * 创建者id,对应member或者personinfo的id
     */
    @Column(name = "draft_createrid")
    private String draftCreaterid;

    /**
     * 1 个人;2 企业,
     */
    @Column(name = "draft_creatertype")
    private Integer draftCreatertype;

    /**
     * 创建时间,
     */
    @Column(name = "draft_creattime")
    private Date draftCreattime;

    /**
     * 草稿名称,
     */
    @Column(name = "draft_name")
    private String draftName;

    /**
     * 文件类型,1 pdf;2 word;3 ofd;4 图片
     */
    @Column(name = "draft_filetype")
    private Integer draftFiletype;

    /**
     * 对应模板id,
     */
    @Column(name = "draft_templetid")
    private String draftTempletid;

    /**
     * 状态,1 可用;2 不可用
     */
    @Column(name = "draft_status")
    private Integer draftStatus;

    /**
     * 存储文件的id,
     */
    @Column(name = "draft_fileid")
    private String draftFileid;

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
     * 对应档案库id,
     */
    @Column(name = "draft_comfileid")
    private String draftComfileid;

    /**
     * 对应流程id,
     */
    @Column(name = "draft_processid")
    private String draftProcessid;

    /**
     * 获取合同草稿id,
     *
     * @return draft_id - 合同草稿id,
     */
    public String getDraftId() {
        return draftId;
    }

    /**
     * 设置合同草稿id,
     *
     * @param draftId 合同草稿id,
     */
    public void setDraftId(String draftId) {
        this.draftId = draftId;
    }

    /**
     * 获取创建者id,对应member或者personinfo的id
     *
     * @return draft_createrid - 创建者id,对应member或者personinfo的id
     */
    public String getDraftCreaterid() {
        return draftCreaterid;
    }

    /**
     * 设置创建者id,对应member或者personinfo的id
     *
     * @param draftCreaterid 创建者id,对应member或者personinfo的id
     */
    public void setDraftCreaterid(String draftCreaterid) {
        this.draftCreaterid = draftCreaterid;
    }

    /**
     * 获取1 个人;2 企业,
     *
     * @return draft_creatertype - 1 个人;2 企业,
     */
    public Integer getDraftCreatertype() {
        return draftCreatertype;
    }

    /**
     * 设置1 个人;2 企业,
     *
     * @param draftCreatertype 1 个人;2 企业,
     */
    public void setDraftCreatertype(Integer draftCreatertype) {
        this.draftCreatertype = draftCreatertype;
    }

    /**
     * 获取创建时间,
     *
     * @return draft_creattime - 创建时间,
     */
    public Date getDraftCreattime() {
        return draftCreattime;
    }

    /**
     * 设置创建时间,
     *
     * @param draftCreattime 创建时间,
     */
    public void setDraftCreattime(Date draftCreattime) {
        this.draftCreattime = draftCreattime;
    }

    /**
     * 获取草稿名称,
     *
     * @return draft_name - 草稿名称,
     */
    public String getDraftName() {
        return draftName;
    }

    /**
     * 设置草稿名称,
     *
     * @param draftName 草稿名称,
     */
    public void setDraftName(String draftName) {
        this.draftName = draftName;
    }

    /**
     * 获取文件类型,1 pdf;2 word;3 ofd;4 图片
     *
     * @return draft_filetype - 文件类型,1 pdf;2 word;3 ofd;4 图片
     */
    public Integer getDraftFiletype() {
        return draftFiletype;
    }

    /**
     * 设置文件类型,1 pdf;2 word;3 ofd;4 图片
     *
     * @param draftFiletype 文件类型,1 pdf;2 word;3 ofd;4 图片
     */
    public void setDraftFiletype(Integer draftFiletype) {
        this.draftFiletype = draftFiletype;
    }

    /**
     * 获取对应模板id,
     *
     * @return draft_templetid - 对应模板id,
     */
    public String getDraftTempletid() {
        return draftTempletid;
    }

    /**
     * 设置对应模板id,
     *
     * @param draftTempletid 对应模板id,
     */
    public void setDraftTempletid(String draftTempletid) {
        this.draftTempletid = draftTempletid;
    }

    /**
     * 获取状态,1 可用;2 不可用
     *
     * @return draft_status - 状态,1 可用;2 不可用
     */
    public Integer getDraftStatus() {
        return draftStatus;
    }

    /**
     * 设置状态,1 可用;2 不可用
     *
     * @param draftStatus 状态,1 可用;2 不可用
     */
    public void setDraftStatus(Integer draftStatus) {
        this.draftStatus = draftStatus;
    }

    /**
     * 获取存储文件的id,
     *
     * @return draft_fileid - 存储文件的id,
     */
    public String getDraftFileid() {
        return draftFileid;
    }

    /**
     * 设置存储文件的id,
     *
     * @param draftFileid 存储文件的id,
     */
    public void setDraftFileid(String draftFileid) {
        this.draftFileid = draftFileid;
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

    /**
     * 获取对应档案库id,
     *
     * @return draft_comfileid - 对应档案库id,
     */
    public String getDraftComfileid() {
        return draftComfileid;
    }

    /**
     * 设置对应档案库id,
     *
     * @param draftComfileid 对应档案库id,
     */
    public void setDraftComfileid(String draftComfileid) {
        this.draftComfileid = draftComfileid;
    }

    /**
     * 获取对应流程id,
     *
     * @return draft_processid - 对应流程id,
     */
    public String getDraftProcessid() {
        return draftProcessid;
    }

    /**
     * 设置对应流程id,
     *
     * @param draftProcessid 对应流程id,
     */
    public void setDraftProcessid(String draftProcessid) {
        this.draftProcessid = draftProcessid;
    }
}