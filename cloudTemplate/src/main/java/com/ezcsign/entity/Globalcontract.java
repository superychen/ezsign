package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "globalcontract")
public class Globalcontract {
    /**
     * 模板id,
     */
    @Id
    @Column(name = "globalcontract_id")
    @GeneratedValue(generator = "JDBC")
    private String globalcontractId;

    /**
     * 模板名称,
     */
    @Column(name = "globalcontract_name")
    private String globalcontractName;

    /**
     * 创建时间,
     */
    @Column(name = "globalcontract_createtime")
    private Date globalcontractCreatetime;

    /**
     * 模板类型,1 个人;2 企业
     */
    @Column(name = "globalcontract_type")
    private Integer globalcontractType;

    /**
     * 模板文件类型,1 pdf;2 word;3 ofd;4 jpg
     */
    @Column(name = "globalcontract_filetype")
    private Integer globalcontractFiletype;

    /**
     * 模板状态,1可用;2 不可用
     */
    @Column(name = "globalcontract_status")
    private Integer globalcontractStatus;

    /**
     * 文件存储id,
     */
    @Column(name = "globalcontract_fileid")
    private String globalcontractFileid;

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
     * 获取模板id,
     *
     * @return globalcontract_id - 模板id,
     */
    public String getGlobalcontractId() {
        return globalcontractId;
    }

    /**
     * 设置模板id,
     *
     * @param globalcontractId 模板id,
     */
    public void setGlobalcontractId(String globalcontractId) {
        this.globalcontractId = globalcontractId;
    }

    /**
     * 获取模板名称,
     *
     * @return globalcontract_name - 模板名称,
     */
    public String getGlobalcontractName() {
        return globalcontractName;
    }

    /**
     * 设置模板名称,
     *
     * @param globalcontractName 模板名称,
     */
    public void setGlobalcontractName(String globalcontractName) {
        this.globalcontractName = globalcontractName;
    }

    /**
     * 获取创建时间,
     *
     * @return globalcontract_createtime - 创建时间,
     */
    public Date getGlobalcontractCreatetime() {
        return globalcontractCreatetime;
    }

    /**
     * 设置创建时间,
     *
     * @param globalcontractCreatetime 创建时间,
     */
    public void setGlobalcontractCreatetime(Date globalcontractCreatetime) {
        this.globalcontractCreatetime = globalcontractCreatetime;
    }

    /**
     * 获取模板类型,1 个人;2 企业
     *
     * @return globalcontract_type - 模板类型,1 个人;2 企业
     */
    public Integer getGlobalcontractType() {
        return globalcontractType;
    }

    /**
     * 设置模板类型,1 个人;2 企业
     *
     * @param globalcontractType 模板类型,1 个人;2 企业
     */
    public void setGlobalcontractType(Integer globalcontractType) {
        this.globalcontractType = globalcontractType;
    }

    /**
     * 获取模板文件类型,1 pdf;2 word;3 ofd;4 jpg
     *
     * @return globalcontract_filetype - 模板文件类型,1 pdf;2 word;3 ofd;4 jpg
     */
    public Integer getGlobalcontractFiletype() {
        return globalcontractFiletype;
    }

    /**
     * 设置模板文件类型,1 pdf;2 word;3 ofd;4 jpg
     *
     * @param globalcontractFiletype 模板文件类型,1 pdf;2 word;3 ofd;4 jpg
     */
    public void setGlobalcontractFiletype(Integer globalcontractFiletype) {
        this.globalcontractFiletype = globalcontractFiletype;
    }

    /**
     * 获取模板状态,1可用;2 不可用
     *
     * @return globalcontract_status - 模板状态,1可用;2 不可用
     */
    public Integer getGlobalcontractStatus() {
        return globalcontractStatus;
    }

    /**
     * 设置模板状态,1可用;2 不可用
     *
     * @param globalcontractStatus 模板状态,1可用;2 不可用
     */
    public void setGlobalcontractStatus(Integer globalcontractStatus) {
        this.globalcontractStatus = globalcontractStatus;
    }

    /**
     * 获取文件存储id,
     *
     * @return globalcontract_fileid - 文件存储id,
     */
    public String getGlobalcontractFileid() {
        return globalcontractFileid;
    }

    /**
     * 设置文件存储id,
     *
     * @param globalcontractFileid 文件存储id,
     */
    public void setGlobalcontractFileid(String globalcontractFileid) {
        this.globalcontractFileid = globalcontractFileid;
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