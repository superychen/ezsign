package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "exception")
public class Exception {
    @Id
    @Column(name = "exception_id")
    @GeneratedValue(generator = "JDBC")
    private String exceptionId;

    /**
     * 操作人id,
     */
    @Column(name = "exception_operatorid")
    private String exceptionOperatorid;

    /**
     * 操作时间,
     */
    @Column(name = "exception_operattime")
    private Date exceptionOperattime;

    /**
     * 对应的api详情id,
     */
    @Column(name = "exception_apidetailid")
    private String exceptionApidetailid;

    /**
     * 错误代码,
     */
    @Column(name = "exception_errorcode")
    private Integer exceptionErrorcode;

    /**
     * 错误的参数,
     */
    @Column(name = "exception_errorparam")
    private String exceptionErrorparam;

    /**
     * 修改后的参数,
     */
    @Column(name = "exception_newparam")
    private String exceptionNewparam;

    /**
     * 处理方式,1重发
     */
    @Column(name = "exception_handletype")
    private Integer exceptionHandletype;

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
     * @return exception_id
     */
    public String getExceptionId() {
        return exceptionId;
    }

    /**
     * @param exceptionId
     */
    public void setExceptionId(String exceptionId) {
        this.exceptionId = exceptionId;
    }

    /**
     * 获取操作人id,
     *
     * @return exception_operatorid - 操作人id,
     */
    public String getExceptionOperatorid() {
        return exceptionOperatorid;
    }

    /**
     * 设置操作人id,
     *
     * @param exceptionOperatorid 操作人id,
     */
    public void setExceptionOperatorid(String exceptionOperatorid) {
        this.exceptionOperatorid = exceptionOperatorid;
    }

    /**
     * 获取操作时间,
     *
     * @return exception_operattime - 操作时间,
     */
    public Date getExceptionOperattime() {
        return exceptionOperattime;
    }

    /**
     * 设置操作时间,
     *
     * @param exceptionOperattime 操作时间,
     */
    public void setExceptionOperattime(Date exceptionOperattime) {
        this.exceptionOperattime = exceptionOperattime;
    }

    /**
     * 获取对应的api详情id,
     *
     * @return exception_apidetailid - 对应的api详情id,
     */
    public String getExceptionApidetailid() {
        return exceptionApidetailid;
    }

    /**
     * 设置对应的api详情id,
     *
     * @param exceptionApidetailid 对应的api详情id,
     */
    public void setExceptionApidetailid(String exceptionApidetailid) {
        this.exceptionApidetailid = exceptionApidetailid;
    }

    /**
     * 获取错误代码,
     *
     * @return exception_errorcode - 错误代码,
     */
    public Integer getExceptionErrorcode() {
        return exceptionErrorcode;
    }

    /**
     * 设置错误代码,
     *
     * @param exceptionErrorcode 错误代码,
     */
    public void setExceptionErrorcode(Integer exceptionErrorcode) {
        this.exceptionErrorcode = exceptionErrorcode;
    }

    /**
     * 获取错误的参数,
     *
     * @return exception_errorparam - 错误的参数,
     */
    public String getExceptionErrorparam() {
        return exceptionErrorparam;
    }

    /**
     * 设置错误的参数,
     *
     * @param exceptionErrorparam 错误的参数,
     */
    public void setExceptionErrorparam(String exceptionErrorparam) {
        this.exceptionErrorparam = exceptionErrorparam;
    }

    /**
     * 获取修改后的参数,
     *
     * @return exception_newparam - 修改后的参数,
     */
    public String getExceptionNewparam() {
        return exceptionNewparam;
    }

    /**
     * 设置修改后的参数,
     *
     * @param exceptionNewparam 修改后的参数,
     */
    public void setExceptionNewparam(String exceptionNewparam) {
        this.exceptionNewparam = exceptionNewparam;
    }

    /**
     * 获取处理方式,1重发
     *
     * @return exception_handletype - 处理方式,1重发
     */
    public Integer getExceptionHandletype() {
        return exceptionHandletype;
    }

    /**
     * 设置处理方式,1重发
     *
     * @param exceptionHandletype 处理方式,1重发
     */
    public void setExceptionHandletype(Integer exceptionHandletype) {
        this.exceptionHandletype = exceptionHandletype;
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