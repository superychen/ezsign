package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "usertemplet")
public class Usertemplet {
    /**
     * 合同模板,
     */
    @Id
    @Column(name = "usertemplet_id")
    @GeneratedValue(generator = "JDBC")
    private String usertempletId;

    /**
     * 拥有者id,（对应personinfo或conpanyinfo的id）
     */
    @Column(name = "usertemplet_userid")
    private String usertempletUserid;

    /**
     * 1 个人;2 企业。用户id的类型
     */
    @Column(name = "usertemplet_usertype")
    private Integer usertempletUsertype;

    /**
     * 模板名称,
     */
    @Column(name = "usertemplet_name")
    private String usertempletName;

    /**
     * 创建时间,
     */
    @Column(name = "usertemplet_createtime")
    private Date usertempletCreatetime;

    /**
     * 模板类型,
     */
    @Column(name = "usertemplet_type")
    private Integer usertempletType;

    /**
     * 模板文件类型,
     */
    @Column(name = "usertemplet_filetype")
    private Integer usertempletFiletype;

    /**
     * 模板状态,0=无效;1=有效;2=未审核
     */
    @Column(name = "usertemplet_status")
    private Integer usertempletStatus;

    /**
     * ,1 个人;2 企业
     */
    @Column(name = "usertemplet_creatertype")
    private Integer usertempletCreatertype;

    /**
     * 创建人,对应personinfo或者memberinfo的id
     */
    @Column(name = "usertemplet_createrid")
    private String usertempletCreaterid;

    /**
     * 创建人姓名,
     */
    @Column(name = "usertemplet_creatername")
    private String usertempletCreatername;

    /**
     * 文件存储id,
     */
    @Column(name = "usertemplet_fileid")
    private String usertempletFileid;

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
     * 获取合同模板,
     *
     * @return usertemplet_id - 合同模板,
     */
    public String getUsertempletId() {
        return usertempletId;
    }

    /**
     * 设置合同模板,
     *
     * @param usertempletId 合同模板,
     */
    public void setUsertempletId(String usertempletId) {
        this.usertempletId = usertempletId;
    }

    /**
     * 获取拥有者id,（对应personinfo或conpanyinfo的id）
     *
     * @return usertemplet_userid - 拥有者id,（对应personinfo或conpanyinfo的id）
     */
    public String getUsertempletUserid() {
        return usertempletUserid;
    }

    /**
     * 设置拥有者id,（对应personinfo或conpanyinfo的id）
     *
     * @param usertempletUserid 拥有者id,（对应personinfo或conpanyinfo的id）
     */
    public void setUsertempletUserid(String usertempletUserid) {
        this.usertempletUserid = usertempletUserid;
    }

    /**
     * 获取1 个人;2 企业。用户id的类型
     *
     * @return usertemplet_usertype - 1 个人;2 企业。用户id的类型
     */
    public Integer getUsertempletUsertype() {
        return usertempletUsertype;
    }

    /**
     * 设置1 个人;2 企业。用户id的类型
     *
     * @param usertempletUsertype 1 个人;2 企业。用户id的类型
     */
    public void setUsertempletUsertype(Integer usertempletUsertype) {
        this.usertempletUsertype = usertempletUsertype;
    }

    /**
     * 获取模板名称,
     *
     * @return usertemplet_name - 模板名称,
     */
    public String getUsertempletName() {
        return usertempletName;
    }

    /**
     * 设置模板名称,
     *
     * @param usertempletName 模板名称,
     */
    public void setUsertempletName(String usertempletName) {
        this.usertempletName = usertempletName;
    }

    /**
     * 获取创建时间,
     *
     * @return usertemplet_createtime - 创建时间,
     */
    public Date getUsertempletCreatetime() {
        return usertempletCreatetime;
    }

    /**
     * 设置创建时间,
     *
     * @param usertempletCreatetime 创建时间,
     */
    public void setUsertempletCreatetime(Date usertempletCreatetime) {
        this.usertempletCreatetime = usertempletCreatetime;
    }

    /**
     * 获取模板类型,
     *
     * @return usertemplet_type - 模板类型,
     */
    public Integer getUsertempletType() {
        return usertempletType;
    }

    /**
     * 设置模板类型,
     *
     * @param usertempletType 模板类型,
     */
    public void setUsertempletType(Integer usertempletType) {
        this.usertempletType = usertempletType;
    }

    /**
     * 获取模板文件类型,
     *
     * @return usertemplet_filetype - 模板文件类型,
     */
    public Integer getUsertempletFiletype() {
        return usertempletFiletype;
    }

    /**
     * 设置模板文件类型,
     *
     * @param usertempletFiletype 模板文件类型,
     */
    public void setUsertempletFiletype(Integer usertempletFiletype) {
        this.usertempletFiletype = usertempletFiletype;
    }

    /**
     * 获取模板状态,0=无效;1=有效;2=未审核
     *
     * @return usertemplet_status - 模板状态,0=无效;1=有效;2=未审核
     */
    public Integer getUsertempletStatus() {
        return usertempletStatus;
    }

    /**
     * 设置模板状态,0=无效;1=有效;2=未审核
     *
     * @param usertempletStatus 模板状态,0=无效;1=有效;2=未审核
     */
    public void setUsertempletStatus(Integer usertempletStatus) {
        this.usertempletStatus = usertempletStatus;
    }

    /**
     * 获取,1 个人;2 企业
     *
     * @return usertemplet_creatertype - ,1 个人;2 企业
     */
    public Integer getUsertempletCreatertype() {
        return usertempletCreatertype;
    }

    /**
     * 设置,1 个人;2 企业
     *
     * @param usertempletCreatertype ,1 个人;2 企业
     */
    public void setUsertempletCreatertype(Integer usertempletCreatertype) {
        this.usertempletCreatertype = usertempletCreatertype;
    }

    /**
     * 获取创建人,对应personinfo或者memberinfo的id
     *
     * @return usertemplet_createrid - 创建人,对应personinfo或者memberinfo的id
     */
    public String getUsertempletCreaterid() {
        return usertempletCreaterid;
    }

    /**
     * 设置创建人,对应personinfo或者memberinfo的id
     *
     * @param usertempletCreaterid 创建人,对应personinfo或者memberinfo的id
     */
    public void setUsertempletCreaterid(String usertempletCreaterid) {
        this.usertempletCreaterid = usertempletCreaterid;
    }

    /**
     * 获取创建人姓名,
     *
     * @return usertemplet_creatername - 创建人姓名,
     */
    public String getUsertempletCreatername() {
        return usertempletCreatername;
    }

    /**
     * 设置创建人姓名,
     *
     * @param usertempletCreatername 创建人姓名,
     */
    public void setUsertempletCreatername(String usertempletCreatername) {
        this.usertempletCreatername = usertempletCreatername;
    }

    /**
     * 获取文件存储id,
     *
     * @return usertemplet_fileid - 文件存储id,
     */
    public String getUsertempletFileid() {
        return usertempletFileid;
    }

    /**
     * 设置文件存储id,
     *
     * @param usertempletFileid 文件存储id,
     */
    public void setUsertempletFileid(String usertempletFileid) {
        this.usertempletFileid = usertempletFileid;
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