package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "usertempletfield")
public class Usertempletfield {
    /**
     * 字段id,
     */
    @Id
    @Column(name = "usertempletfield_id")
    @GeneratedValue(generator = "JDBC")
    private String usertempletfieldId;

    /**
     * 所关联的企业模板id,
     */
    @Column(name = "usertempletfield_templetid")
    private String usertempletfieldTempletid;

    /**
     * 对应模板域名,
     */
    @Column(name = "usertempletfield_fieldname")
    private String usertempletfieldFieldname;

    /**
     * 字段名称,
     */
    @Column(name = "usertempletfield_name")
    private String usertempletfieldName;

    /**
     * 在模板中的字段编号,
     */
    @Column(name = "usertempletfield_code")
    private Integer usertempletfieldCode;

    /**
     * 字段类型,所有类型全部为字符串类型
     */
    @Column(name = "usertempletfield_type")
    private Integer usertempletfieldType;

    /**
     * 字段说明,
     */
    @Column(name = "usertempletfield_explain")
    private String usertempletfieldExplain;

    /**
     * 是否为必填项,1 是必须填;2 非必填项
     */
    @Column(name = "usertempletfield_ismust")
    private Integer usertempletfieldIsmust;

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
     * @return usertempletfield_id - 字段id,
     */
    public String getUsertempletfieldId() {
        return usertempletfieldId;
    }

    /**
     * 设置字段id,
     *
     * @param usertempletfieldId 字段id,
     */
    public void setUsertempletfieldId(String usertempletfieldId) {
        this.usertempletfieldId = usertempletfieldId;
    }

    /**
     * 获取所关联的企业模板id,
     *
     * @return usertempletfield_templetid - 所关联的企业模板id,
     */
    public String getUsertempletfieldTempletid() {
        return usertempletfieldTempletid;
    }

    /**
     * 设置所关联的企业模板id,
     *
     * @param usertempletfieldTempletid 所关联的企业模板id,
     */
    public void setUsertempletfieldTempletid(String usertempletfieldTempletid) {
        this.usertempletfieldTempletid = usertempletfieldTempletid;
    }

    /**
     * 获取对应模板域名,
     *
     * @return usertempletfield_fieldname - 对应模板域名,
     */
    public String getUsertempletfieldFieldname() {
        return usertempletfieldFieldname;
    }

    /**
     * 设置对应模板域名,
     *
     * @param usertempletfieldFieldname 对应模板域名,
     */
    public void setUsertempletfieldFieldname(String usertempletfieldFieldname) {
        this.usertempletfieldFieldname = usertempletfieldFieldname;
    }

    /**
     * 获取字段名称,
     *
     * @return usertempletfield_name - 字段名称,
     */
    public String getUsertempletfieldName() {
        return usertempletfieldName;
    }

    /**
     * 设置字段名称,
     *
     * @param usertempletfieldName 字段名称,
     */
    public void setUsertempletfieldName(String usertempletfieldName) {
        this.usertempletfieldName = usertempletfieldName;
    }

    /**
     * 获取在模板中的字段编号,
     *
     * @return usertempletfield_code - 在模板中的字段编号,
     */
    public Integer getUsertempletfieldCode() {
        return usertempletfieldCode;
    }

    /**
     * 设置在模板中的字段编号,
     *
     * @param usertempletfieldCode 在模板中的字段编号,
     */
    public void setUsertempletfieldCode(Integer usertempletfieldCode) {
        this.usertempletfieldCode = usertempletfieldCode;
    }

    /**
     * 获取字段类型,所有类型全部为字符串类型
     *
     * @return usertempletfield_type - 字段类型,所有类型全部为字符串类型
     */
    public Integer getUsertempletfieldType() {
        return usertempletfieldType;
    }

    /**
     * 设置字段类型,所有类型全部为字符串类型
     *
     * @param usertempletfieldType 字段类型,所有类型全部为字符串类型
     */
    public void setUsertempletfieldType(Integer usertempletfieldType) {
        this.usertempletfieldType = usertempletfieldType;
    }

    /**
     * 获取字段说明,
     *
     * @return usertempletfield_explain - 字段说明,
     */
    public String getUsertempletfieldExplain() {
        return usertempletfieldExplain;
    }

    /**
     * 设置字段说明,
     *
     * @param usertempletfieldExplain 字段说明,
     */
    public void setUsertempletfieldExplain(String usertempletfieldExplain) {
        this.usertempletfieldExplain = usertempletfieldExplain;
    }

    /**
     * 获取是否为必填项,1 是必须填;2 非必填项
     *
     * @return usertempletfield_ismust - 是否为必填项,1 是必须填;2 非必填项
     */
    public Integer getUsertempletfieldIsmust() {
        return usertempletfieldIsmust;
    }

    /**
     * 设置是否为必填项,1 是必须填;2 非必填项
     *
     * @param usertempletfieldIsmust 是否为必填项,1 是必须填;2 非必填项
     */
    public void setUsertempletfieldIsmust(Integer usertempletfieldIsmust) {
        this.usertempletfieldIsmust = usertempletfieldIsmust;
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