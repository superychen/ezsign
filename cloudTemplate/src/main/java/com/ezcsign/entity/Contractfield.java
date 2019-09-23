package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "contractfield")
public class Contractfield {
    /**
     * 字段id,
     */
    @Id
    @Column(name = "contractfield_id")
    @GeneratedValue(generator = "JDBC")
    private String contractfieldId;

    /**
     * 对应模板id,
     */
    @Column(name = "contractfield_contractid")
    private String contractfieldContractid;

    /**
     * 对应模板域名,
     */
    @Column(name = "contractfield_fieldname")
    private String contractfieldFieldname;

    /**
     * 字段名称,
     */
    @Column(name = "contractfield_name")
    private String contractfieldName;

    /**
     * 在模板中的字段编号,
     */
    @Column(name = "contractfield_code")
    private Integer contractfieldCode;

    /**
     * 字段说明,
     */
    @Column(name = "contractfield_explain")
    private String contractfieldExplain;

    /**
     * 是否为必填项,1 是必须填;2 非必填项
     */
    @Column(name = "contractfield_ismust")
    private Integer contractfieldIsmust;

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
     * 获取字段id,
     *
     * @return contractfield_id - 字段id,
     */
    public String getContractfieldId() {
        return contractfieldId;
    }

    /**
     * 设置字段id,
     *
     * @param contractfieldId 字段id,
     */
    public void setContractfieldId(String contractfieldId) {
        this.contractfieldId = contractfieldId;
    }

    /**
     * 获取对应模板id,
     *
     * @return contractfield_contractid - 对应模板id,
     */
    public String getContractfieldContractid() {
        return contractfieldContractid;
    }

    /**
     * 设置对应模板id,
     *
     * @param contractfieldContractid 对应模板id,
     */
    public void setContractfieldContractid(String contractfieldContractid) {
        this.contractfieldContractid = contractfieldContractid;
    }

    /**
     * 获取对应模板域名,
     *
     * @return contractfield_fieldname - 对应模板域名,
     */
    public String getContractfieldFieldname() {
        return contractfieldFieldname;
    }

    /**
     * 设置对应模板域名,
     *
     * @param contractfieldFieldname 对应模板域名,
     */
    public void setContractfieldFieldname(String contractfieldFieldname) {
        this.contractfieldFieldname = contractfieldFieldname;
    }

    /**
     * 获取字段名称,
     *
     * @return contractfield_name - 字段名称,
     */
    public String getContractfieldName() {
        return contractfieldName;
    }

    /**
     * 设置字段名称,
     *
     * @param contractfieldName 字段名称,
     */
    public void setContractfieldName(String contractfieldName) {
        this.contractfieldName = contractfieldName;
    }

    /**
     * 获取在模板中的字段编号,
     *
     * @return contractfield_code - 在模板中的字段编号,
     */
    public Integer getContractfieldCode() {
        return contractfieldCode;
    }

    /**
     * 设置在模板中的字段编号,
     *
     * @param contractfieldCode 在模板中的字段编号,
     */
    public void setContractfieldCode(Integer contractfieldCode) {
        this.contractfieldCode = contractfieldCode;
    }

    /**
     * 获取字段说明,
     *
     * @return contractfield_explain - 字段说明,
     */
    public String getContractfieldExplain() {
        return contractfieldExplain;
    }

    /**
     * 设置字段说明,
     *
     * @param contractfieldExplain 字段说明,
     */
    public void setContractfieldExplain(String contractfieldExplain) {
        this.contractfieldExplain = contractfieldExplain;
    }

    /**
     * 获取是否为必填项,1 是必须填;2 非必填项
     *
     * @return contractfield_ismust - 是否为必填项,1 是必须填;2 非必填项
     */
    public Integer getContractfieldIsmust() {
        return contractfieldIsmust;
    }

    /**
     * 设置是否为必填项,1 是必须填;2 非必填项
     *
     * @param contractfieldIsmust 是否为必填项,1 是必须填;2 非必填项
     */
    public void setContractfieldIsmust(Integer contractfieldIsmust) {
        this.contractfieldIsmust = contractfieldIsmust;
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