package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "contractparam")
public class Contractparam {
    /**
     * 字段id,
     */
    @Id
    @Column(name = "contractparam_id")
    @GeneratedValue(generator = "JDBC")
    private String contractparamId;

    /**
     * 所关联合同草稿或文件id,
     */
    @Column(name = "contractparam_contractid")
    private String contractparamContractid;

    /**
     * 对应模板域名,
     */
    @Column(name = "contractparam_fieldname")
    private String contractparamFieldname;

    /**
     * 填写参数内容,
     */
    @Column(name = "contractparam_data")
    private String contractparamData;

    /**
     * 字段名称,
     */
    @Column(name = "contractparam_name")
    private String contractparamName;

    /**
     * 在模板中的字段编号,
     */
    @Column(name = "contractparam_code")
    private Integer contractparamCode;

    /**
     * 字段说明,
     */
    @Column(name = "contractparam_explain")
    private String contractparamExplain;

    /**
     * 是否为必填项,1 是必须填;2 非必填项
     */
    @Column(name = "contractparam_ismust")
    private Integer contractparamIsmust;

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
     * @return contractparam_id - 字段id,
     */
    public String getContractparamId() {
        return contractparamId;
    }

    /**
     * 设置字段id,
     *
     * @param contractparamId 字段id,
     */
    public void setContractparamId(String contractparamId) {
        this.contractparamId = contractparamId;
    }

    /**
     * 获取所关联合同草稿或文件id,
     *
     * @return contractparam_contractid - 所关联合同草稿或文件id,
     */
    public String getContractparamContractid() {
        return contractparamContractid;
    }

    /**
     * 设置所关联合同草稿或文件id,
     *
     * @param contractparamContractid 所关联合同草稿或文件id,
     */
    public void setContractparamContractid(String contractparamContractid) {
        this.contractparamContractid = contractparamContractid;
    }

    /**
     * 获取对应模板域名,
     *
     * @return contractparam_fieldname - 对应模板域名,
     */
    public String getContractparamFieldname() {
        return contractparamFieldname;
    }

    /**
     * 设置对应模板域名,
     *
     * @param contractparamFieldname 对应模板域名,
     */
    public void setContractparamFieldname(String contractparamFieldname) {
        this.contractparamFieldname = contractparamFieldname;
    }

    /**
     * 获取填写参数内容,
     *
     * @return contractparam_data - 填写参数内容,
     */
    public String getContractparamData() {
        return contractparamData;
    }

    /**
     * 设置填写参数内容,
     *
     * @param contractparamData 填写参数内容,
     */
    public void setContractparamData(String contractparamData) {
        this.contractparamData = contractparamData;
    }

    /**
     * 获取字段名称,
     *
     * @return contractparam_name - 字段名称,
     */
    public String getContractparamName() {
        return contractparamName;
    }

    /**
     * 设置字段名称,
     *
     * @param contractparamName 字段名称,
     */
    public void setContractparamName(String contractparamName) {
        this.contractparamName = contractparamName;
    }

    /**
     * 获取在模板中的字段编号,
     *
     * @return contractparam_code - 在模板中的字段编号,
     */
    public Integer getContractparamCode() {
        return contractparamCode;
    }

    /**
     * 设置在模板中的字段编号,
     *
     * @param contractparamCode 在模板中的字段编号,
     */
    public void setContractparamCode(Integer contractparamCode) {
        this.contractparamCode = contractparamCode;
    }

    /**
     * 获取字段说明,
     *
     * @return contractparam_explain - 字段说明,
     */
    public String getContractparamExplain() {
        return contractparamExplain;
    }

    /**
     * 设置字段说明,
     *
     * @param contractparamExplain 字段说明,
     */
    public void setContractparamExplain(String contractparamExplain) {
        this.contractparamExplain = contractparamExplain;
    }

    /**
     * 获取是否为必填项,1 是必须填;2 非必填项
     *
     * @return contractparam_ismust - 是否为必填项,1 是必须填;2 非必填项
     */
    public Integer getContractparamIsmust() {
        return contractparamIsmust;
    }

    /**
     * 设置是否为必填项,1 是必须填;2 非必填项
     *
     * @param contractparamIsmust 是否为必填项,1 是必须填;2 非必填项
     */
    public void setContractparamIsmust(Integer contractparamIsmust) {
        this.contractparamIsmust = contractparamIsmust;
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