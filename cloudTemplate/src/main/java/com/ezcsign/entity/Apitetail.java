package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "apitetail")
public class Apitetail {
    @Id
    @Column(name = "apitetail_id")
    @GeneratedValue(generator = "JDBC")
    private String apitetailId;

    /**
     * 调用api用户的apiid,
     */
    @Column(name = "apitetail_apiinfoid")
    private String apitetailApiinfoid;

    /**
     * 调用的api代码,只记录 sign，notice，register 1 ping;2 getsealinfo;3 sign;4 getsignlink;
     */
    @Column(name = "apitetail_apicode")
    private Integer apitetailApicode;

    /**
     * 参数,文件传输单独走文件传输接口
     */
    @Column(name = "apitetail_param")
    private String apitetailParam;

    /**
     * 发起时间,
     */
    @Column(name = "apitetail_time")
    private Date apitetailTime;

    /**
     * 返回值,
     */
    @Column(name = "apitetail_returninfo")
    private String apitetailReturninfo;

    /**
     * 是否异常,1 正常;2 异常（参数有误，系统出现错误）
     */
    @Column(name = "apitetail_status")
    private Integer apitetailStatus;

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
     * @return apitetail_id
     */
    public String getApitetailId() {
        return apitetailId;
    }

    /**
     * @param apitetailId
     */
    public void setApitetailId(String apitetailId) {
        this.apitetailId = apitetailId;
    }

    /**
     * 获取调用api用户的apiid,
     *
     * @return apitetail_apiinfoid - 调用api用户的apiid,
     */
    public String getApitetailApiinfoid() {
        return apitetailApiinfoid;
    }

    /**
     * 设置调用api用户的apiid,
     *
     * @param apitetailApiinfoid 调用api用户的apiid,
     */
    public void setApitetailApiinfoid(String apitetailApiinfoid) {
        this.apitetailApiinfoid = apitetailApiinfoid;
    }

    /**
     * 获取调用的api代码,只记录 sign，notice，register 1 ping;2 getsealinfo;3 sign;4 getsignlink;
     *
     * @return apitetail_apicode - 调用的api代码,只记录 sign，notice，register 1 ping;2 getsealinfo;3 sign;4 getsignlink;
     */
    public Integer getApitetailApicode() {
        return apitetailApicode;
    }

    /**
     * 设置调用的api代码,只记录 sign，notice，register 1 ping;2 getsealinfo;3 sign;4 getsignlink;
     *
     * @param apitetailApicode 调用的api代码,只记录 sign，notice，register 1 ping;2 getsealinfo;3 sign;4 getsignlink;
     */
    public void setApitetailApicode(Integer apitetailApicode) {
        this.apitetailApicode = apitetailApicode;
    }

    /**
     * 获取参数,文件传输单独走文件传输接口
     *
     * @return apitetail_param - 参数,文件传输单独走文件传输接口
     */
    public String getApitetailParam() {
        return apitetailParam;
    }

    /**
     * 设置参数,文件传输单独走文件传输接口
     *
     * @param apitetailParam 参数,文件传输单独走文件传输接口
     */
    public void setApitetailParam(String apitetailParam) {
        this.apitetailParam = apitetailParam;
    }

    /**
     * 获取发起时间,
     *
     * @return apitetail_time - 发起时间,
     */
    public Date getApitetailTime() {
        return apitetailTime;
    }

    /**
     * 设置发起时间,
     *
     * @param apitetailTime 发起时间,
     */
    public void setApitetailTime(Date apitetailTime) {
        this.apitetailTime = apitetailTime;
    }

    /**
     * 获取返回值,
     *
     * @return apitetail_returninfo - 返回值,
     */
    public String getApitetailReturninfo() {
        return apitetailReturninfo;
    }

    /**
     * 设置返回值,
     *
     * @param apitetailReturninfo 返回值,
     */
    public void setApitetailReturninfo(String apitetailReturninfo) {
        this.apitetailReturninfo = apitetailReturninfo;
    }

    /**
     * 获取是否异常,1 正常;2 异常（参数有误，系统出现错误）
     *
     * @return apitetail_status - 是否异常,1 正常;2 异常（参数有误，系统出现错误）
     */
    public Integer getApitetailStatus() {
        return apitetailStatus;
    }

    /**
     * 设置是否异常,1 正常;2 异常（参数有误，系统出现错误）
     *
     * @param apitetailStatus 是否异常,1 正常;2 异常（参数有误，系统出现错误）
     */
    public void setApitetailStatus(Integer apitetailStatus) {
        this.apitetailStatus = apitetailStatus;
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