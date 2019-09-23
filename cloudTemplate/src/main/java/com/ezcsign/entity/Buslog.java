package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "buslog")
public class Buslog {
    /**
     * 日志id,
     */
    @Id
    @Column(name = "buslog_id")
    @GeneratedValue(generator = "JDBC")
    private String buslogId;

    /**
     * 日志事件,0 注册;1 找回用户名;2 找回密码;3 登录;4 个人认证
     */
    @Column(name = "buslog_event")
    private Integer buslogEvent;

    /**
     * 时间,
     */
    @Column(name = "buslog_time")
    private Date buslogTime;

    /**
     * 操作人id,
     */
    @Column(name = "buslog_opratorid")
    private String buslogOpratorid;

    /**
     * 操作人类型,1 个人;2 企业成员
     */
    @Column(name = "buslog_opratortype")
    private Integer buslogOpratortype;

    /**
     * 对应企业的id,
     */
    @Column(name = "buslog_companyid")
    private String buslogCompanyid;

    /**
     * 操作人名称,
     */
    @Column(name = "buslog_opratorname")
    private String buslogOpratorname;

    /**
     * 状态,1表示成功;2表示失败
     */
    @Column(name = "buslog_state")
    private Integer buslogState;

    /**
     * 说明,
     */
    @Column(name = "buslog_explain")
    private String buslogExplain;

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
     * 获取日志id,
     *
     * @return buslog_id - 日志id,
     */
    public String getBuslogId() {
        return buslogId;
    }

    /**
     * 设置日志id,
     *
     * @param buslogId 日志id,
     */
    public void setBuslogId(String buslogId) {
        this.buslogId = buslogId;
    }

    /**
     * 获取日志事件,0 注册;1 找回用户名;2 找回密码;3 登录;4 个人认证
     *
     * @return buslog_event - 日志事件,0 注册;1 找回用户名;2 找回密码;3 登录;4 个人认证
     */
    public Integer getBuslogEvent() {
        return buslogEvent;
    }

    /**
     * 设置日志事件,0 注册;1 找回用户名;2 找回密码;3 登录;4 个人认证
     *
     * @param buslogEvent 日志事件,0 注册;1 找回用户名;2 找回密码;3 登录;4 个人认证
     */
    public void setBuslogEvent(Integer buslogEvent) {
        this.buslogEvent = buslogEvent;
    }

    /**
     * 获取时间,
     *
     * @return buslog_time - 时间,
     */
    public Date getBuslogTime() {
        return buslogTime;
    }

    /**
     * 设置时间,
     *
     * @param buslogTime 时间,
     */
    public void setBuslogTime(Date buslogTime) {
        this.buslogTime = buslogTime;
    }

    /**
     * 获取操作人id,
     *
     * @return buslog_opratorid - 操作人id,
     */
    public String getBuslogOpratorid() {
        return buslogOpratorid;
    }

    /**
     * 设置操作人id,
     *
     * @param buslogOpratorid 操作人id,
     */
    public void setBuslogOpratorid(String buslogOpratorid) {
        this.buslogOpratorid = buslogOpratorid;
    }

    /**
     * 获取操作人类型,1 个人;2 企业成员
     *
     * @return buslog_opratortype - 操作人类型,1 个人;2 企业成员
     */
    public Integer getBuslogOpratortype() {
        return buslogOpratortype;
    }

    /**
     * 设置操作人类型,1 个人;2 企业成员
     *
     * @param buslogOpratortype 操作人类型,1 个人;2 企业成员
     */
    public void setBuslogOpratortype(Integer buslogOpratortype) {
        this.buslogOpratortype = buslogOpratortype;
    }

    /**
     * 获取对应企业的id,
     *
     * @return buslog_companyid - 对应企业的id,
     */
    public String getBuslogCompanyid() {
        return buslogCompanyid;
    }

    /**
     * 设置对应企业的id,
     *
     * @param buslogCompanyid 对应企业的id,
     */
    public void setBuslogCompanyid(String buslogCompanyid) {
        this.buslogCompanyid = buslogCompanyid;
    }

    /**
     * 获取操作人名称,
     *
     * @return buslog_opratorname - 操作人名称,
     */
    public String getBuslogOpratorname() {
        return buslogOpratorname;
    }

    /**
     * 设置操作人名称,
     *
     * @param buslogOpratorname 操作人名称,
     */
    public void setBuslogOpratorname(String buslogOpratorname) {
        this.buslogOpratorname = buslogOpratorname;
    }

    /**
     * 获取状态,1表示成功;2表示失败
     *
     * @return buslog_state - 状态,1表示成功;2表示失败
     */
    public Integer getBuslogState() {
        return buslogState;
    }

    /**
     * 设置状态,1表示成功;2表示失败
     *
     * @param buslogState 状态,1表示成功;2表示失败
     */
    public void setBuslogState(Integer buslogState) {
        this.buslogState = buslogState;
    }

    /**
     * 获取说明,
     *
     * @return buslog_explain - 说明,
     */
    public String getBuslogExplain() {
        return buslogExplain;
    }

    /**
     * 设置说明,
     *
     * @param buslogExplain 说明,
     */
    public void setBuslogExplain(String buslogExplain) {
        this.buslogExplain = buslogExplain;
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