package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "comfiles")
public class Comfiles {
    /**
     * 档案id,
     */
    @Id
    @Column(name = "comfiles_id")
    @GeneratedValue(generator = "JDBC")
    private String comfilesId;

    /**
     * 档案拥有者id,（companyinfo的id）
     */
    @Column(name = "comfiles_companyid")
    private String comfilesCompanyid;

    /**
     * 档案名称,
     */
    @Column(name = "comfiles_name")
    private String comfilesName;

    /**
     * 档案创建人id,
     */
    @Column(name = "comfiles_createrid")
    private String comfilesCreaterid;

    /**
     * 档案创建人名称,
     */
    @Column(name = "comfiles_creatername")
    private String comfilesCreatername;

    /**
     * 档案创建时间,
     */
    @Column(name = "comfiles_creattime")
    private Date comfilesCreattime;

    /**
     * 节点类型,1 文件夹;2 文件
     */
    @Column(name = "comfiles_type")
    private Integer comfilesType;

    /**
     * 文件类型,1 pdf;2 word;3 ofd;4 Excel;5 图片
     */
    @Column(name = "comfiles_filetype")
    private Integer comfilesFiletype;

    /**
     * 对应签约流水id,
     */
    @Column(name = "comfiles_signrequestid")
    private String comfilesSignrequestid;

    /**
     * 文件存储id,
     */
    @Column(name = "comfiles_fileid")
    private String comfilesFileid;

    /**
     * 生效时间,
     */
    @Column(name = "comfiles_executetime")
    private Date comfilesExecutetime;

    /**
     * 截止时间,
     */
    @Column(name = "comfiles_expiretime")
    private Date comfilesExpiretime;

    /**
     * 状态,1 可查阅;2  封存;3 未归档文件;  4  销毁
     */
    @Column(name = "comfiles_status")
    private Integer comfilesStatus;

    /**
     * 提醒id,
     */
    @Column(name = "comfiles_remindnoteid")
    private String comfilesRemindnoteid;

    /**
     * 上级id,
     */
    @Column(name = "comfiles_pid")
    private String comfilesPid;

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
     * 获取档案id,
     *
     * @return comfiles_id - 档案id,
     */
    public String getComfilesId() {
        return comfilesId;
    }

    /**
     * 设置档案id,
     *
     * @param comfilesId 档案id,
     */
    public void setComfilesId(String comfilesId) {
        this.comfilesId = comfilesId;
    }

    /**
     * 获取档案拥有者id,（companyinfo的id）
     *
     * @return comfiles_companyid - 档案拥有者id,（companyinfo的id）
     */
    public String getComfilesCompanyid() {
        return comfilesCompanyid;
    }

    /**
     * 设置档案拥有者id,（companyinfo的id）
     *
     * @param comfilesCompanyid 档案拥有者id,（companyinfo的id）
     */
    public void setComfilesCompanyid(String comfilesCompanyid) {
        this.comfilesCompanyid = comfilesCompanyid;
    }

    /**
     * 获取档案名称,
     *
     * @return comfiles_name - 档案名称,
     */
    public String getComfilesName() {
        return comfilesName;
    }

    /**
     * 设置档案名称,
     *
     * @param comfilesName 档案名称,
     */
    public void setComfilesName(String comfilesName) {
        this.comfilesName = comfilesName;
    }

    /**
     * 获取档案创建人id,
     *
     * @return comfiles_createrid - 档案创建人id,
     */
    public String getComfilesCreaterid() {
        return comfilesCreaterid;
    }

    /**
     * 设置档案创建人id,
     *
     * @param comfilesCreaterid 档案创建人id,
     */
    public void setComfilesCreaterid(String comfilesCreaterid) {
        this.comfilesCreaterid = comfilesCreaterid;
    }

    /**
     * 获取档案创建人名称,
     *
     * @return comfiles_creatername - 档案创建人名称,
     */
    public String getComfilesCreatername() {
        return comfilesCreatername;
    }

    /**
     * 设置档案创建人名称,
     *
     * @param comfilesCreatername 档案创建人名称,
     */
    public void setComfilesCreatername(String comfilesCreatername) {
        this.comfilesCreatername = comfilesCreatername;
    }

    /**
     * 获取档案创建时间,
     *
     * @return comfiles_creattime - 档案创建时间,
     */
    public Date getComfilesCreattime() {
        return comfilesCreattime;
    }

    /**
     * 设置档案创建时间,
     *
     * @param comfilesCreattime 档案创建时间,
     */
    public void setComfilesCreattime(Date comfilesCreattime) {
        this.comfilesCreattime = comfilesCreattime;
    }

    /**
     * 获取节点类型,1 文件夹;2 文件
     *
     * @return comfiles_type - 节点类型,1 文件夹;2 文件
     */
    public Integer getComfilesType() {
        return comfilesType;
    }

    /**
     * 设置节点类型,1 文件夹;2 文件
     *
     * @param comfilesType 节点类型,1 文件夹;2 文件
     */
    public void setComfilesType(Integer comfilesType) {
        this.comfilesType = comfilesType;
    }

    /**
     * 获取文件类型,1 pdf;2 word;3 ofd;4 Excel;5 图片
     *
     * @return comfiles_filetype - 文件类型,1 pdf;2 word;3 ofd;4 Excel;5 图片
     */
    public Integer getComfilesFiletype() {
        return comfilesFiletype;
    }

    /**
     * 设置文件类型,1 pdf;2 word;3 ofd;4 Excel;5 图片
     *
     * @param comfilesFiletype 文件类型,1 pdf;2 word;3 ofd;4 Excel;5 图片
     */
    public void setComfilesFiletype(Integer comfilesFiletype) {
        this.comfilesFiletype = comfilesFiletype;
    }

    /**
     * 获取对应签约流水id,
     *
     * @return comfiles_signrequestid - 对应签约流水id,
     */
    public String getComfilesSignrequestid() {
        return comfilesSignrequestid;
    }

    /**
     * 设置对应签约流水id,
     *
     * @param comfilesSignrequestid 对应签约流水id,
     */
    public void setComfilesSignrequestid(String comfilesSignrequestid) {
        this.comfilesSignrequestid = comfilesSignrequestid;
    }

    /**
     * 获取文件存储id,
     *
     * @return comfiles_fileid - 文件存储id,
     */
    public String getComfilesFileid() {
        return comfilesFileid;
    }

    /**
     * 设置文件存储id,
     *
     * @param comfilesFileid 文件存储id,
     */
    public void setComfilesFileid(String comfilesFileid) {
        this.comfilesFileid = comfilesFileid;
    }

    /**
     * 获取生效时间,
     *
     * @return comfiles_executetime - 生效时间,
     */
    public Date getComfilesExecutetime() {
        return comfilesExecutetime;
    }

    /**
     * 设置生效时间,
     *
     * @param comfilesExecutetime 生效时间,
     */
    public void setComfilesExecutetime(Date comfilesExecutetime) {
        this.comfilesExecutetime = comfilesExecutetime;
    }

    /**
     * 获取截止时间,
     *
     * @return comfiles_expiretime - 截止时间,
     */
    public Date getComfilesExpiretime() {
        return comfilesExpiretime;
    }

    /**
     * 设置截止时间,
     *
     * @param comfilesExpiretime 截止时间,
     */
    public void setComfilesExpiretime(Date comfilesExpiretime) {
        this.comfilesExpiretime = comfilesExpiretime;
    }

    /**
     * 获取状态,1 可查阅;2  封存;3 未归档文件;  4  销毁
     *
     * @return comfiles_status - 状态,1 可查阅;2  封存;3 未归档文件;  4  销毁
     */
    public Integer getComfilesStatus() {
        return comfilesStatus;
    }

    /**
     * 设置状态,1 可查阅;2  封存;3 未归档文件;  4  销毁
     *
     * @param comfilesStatus 状态,1 可查阅;2  封存;3 未归档文件;  4  销毁
     */
    public void setComfilesStatus(Integer comfilesStatus) {
        this.comfilesStatus = comfilesStatus;
    }

    /**
     * 获取提醒id,
     *
     * @return comfiles_remindnoteid - 提醒id,
     */
    public String getComfilesRemindnoteid() {
        return comfilesRemindnoteid;
    }

    /**
     * 设置提醒id,
     *
     * @param comfilesRemindnoteid 提醒id,
     */
    public void setComfilesRemindnoteid(String comfilesRemindnoteid) {
        this.comfilesRemindnoteid = comfilesRemindnoteid;
    }

    /**
     * 获取上级id,
     *
     * @return comfiles_pid - 上级id,
     */
    public String getComfilesPid() {
        return comfilesPid;
    }

    /**
     * 设置上级id,
     *
     * @param comfilesPid 上级id,
     */
    public void setComfilesPid(String comfilesPid) {
        this.comfilesPid = comfilesPid;
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