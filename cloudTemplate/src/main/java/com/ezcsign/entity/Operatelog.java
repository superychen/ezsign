package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "operatelog")
public class Operatelog {
    /**
     * 日志id,
     */
    @Id
    @Column(name = "operatelog_id")
    @GeneratedValue(generator = "JDBC")
    private String operatelogId;

    /**
     * 日志事件,0 注册;1 找回用户名;2 找回密码;3 登录;4 个人认证
     */
    @Column(name = "operatelog_event")
    private Integer operatelogEvent;

    /**
     * 时间,
     */
    @Column(name = "operatelog_time")
    private Date operatelogTime;

    /**
     * 操作人id,
     */
    @Column(name = "operatelog_opratorid")
    private String operatelogOpratorid;

    /**
     * 操作人类型,1 个人;2 企业成员
     */
    @Column(name = "operatelog_opratortype")
    private Integer operatelogOpratortype;

    /**
     * 对应企业的id,
     */
    @Column(name = "operatelog_companyid")
    private String operatelogCompanyid;

    /**
     * 操作人名称,
     */
    @Column(name = "operatelog_opratorname")
    private String operatelogOpratorname;

    /**
     * 操作对象id,
     */
    @Column(name = "operatelog_opratobjectid")
    private String operatelogOpratobjectid;

    /**
     * 对象类型,1 成员、账号;2 印章;3 模板;4 档案;5 流程
     */
    @Column(name = "operatelog_objecttype")
    private Integer operatelogObjecttype;

    /**
     * 状态,1表示成功;2表示失败
     */
    @Column(name = "operatelog_state")
    private Integer operatelogState;

    /**
     * 说明,
     */
    @Column(name = "operatelog_explain")
    private String operatelogExplain;

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
     * @return operatelog_id - 日志id,
     */
    public String getOperatelogId() {
        return operatelogId;
    }

    /**
     * 设置日志id,
     *
     * @param operatelogId 日志id,
     */
    public void setOperatelogId(String operatelogId) {
        this.operatelogId = operatelogId;
    }

    /**
     * 获取日志事件,0 注册;1 找回用户名;2 找回密码;3 登录;4 个人认证
     *
     * @return operatelog_event - 日志事件,0 注册;1 找回用户名;2 找回密码;3 登录;4 个人认证
     */
    public Integer getOperatelogEvent() {
        return operatelogEvent;
    }

    /**
     * 设置日志事件,0 注册;1 找回用户名;2 找回密码;3 登录;4 个人认证
     *
     * @param operatelogEvent 日志事件,0 注册;1 找回用户名;2 找回密码;3 登录;4 个人认证
     */
    public void setOperatelogEvent(Integer operatelogEvent) {
        this.operatelogEvent = operatelogEvent;
    }

    /**
     * 获取时间,
     *
     * @return operatelog_time - 时间,
     */
    public Date getOperatelogTime() {
        return operatelogTime;
    }

    /**
     * 设置时间,
     *
     * @param operatelogTime 时间,
     */
    public void setOperatelogTime(Date operatelogTime) {
        this.operatelogTime = operatelogTime;
    }

    /**
     * 获取操作人id,
     *
     * @return operatelog_opratorid - 操作人id,
     */
    public String getOperatelogOpratorid() {
        return operatelogOpratorid;
    }

    /**
     * 设置操作人id,
     *
     * @param operatelogOpratorid 操作人id,
     */
    public void setOperatelogOpratorid(String operatelogOpratorid) {
        this.operatelogOpratorid = operatelogOpratorid;
    }

    /**
     * 获取操作人类型,1 个人;2 企业成员
     *
     * @return operatelog_opratortype - 操作人类型,1 个人;2 企业成员
     */
    public Integer getOperatelogOpratortype() {
        return operatelogOpratortype;
    }

    /**
     * 设置操作人类型,1 个人;2 企业成员
     *
     * @param operatelogOpratortype 操作人类型,1 个人;2 企业成员
     */
    public void setOperatelogOpratortype(Integer operatelogOpratortype) {
        this.operatelogOpratortype = operatelogOpratortype;
    }

    /**
     * 获取对应企业的id,
     *
     * @return operatelog_companyid - 对应企业的id,
     */
    public String getOperatelogCompanyid() {
        return operatelogCompanyid;
    }

    /**
     * 设置对应企业的id,
     *
     * @param operatelogCompanyid 对应企业的id,
     */
    public void setOperatelogCompanyid(String operatelogCompanyid) {
        this.operatelogCompanyid = operatelogCompanyid;
    }

    /**
     * 获取操作人名称,
     *
     * @return operatelog_opratorname - 操作人名称,
     */
    public String getOperatelogOpratorname() {
        return operatelogOpratorname;
    }

    /**
     * 设置操作人名称,
     *
     * @param operatelogOpratorname 操作人名称,
     */
    public void setOperatelogOpratorname(String operatelogOpratorname) {
        this.operatelogOpratorname = operatelogOpratorname;
    }

    /**
     * 获取操作对象id,
     *
     * @return operatelog_opratobjectid - 操作对象id,
     */
    public String getOperatelogOpratobjectid() {
        return operatelogOpratobjectid;
    }

    /**
     * 设置操作对象id,
     *
     * @param operatelogOpratobjectid 操作对象id,
     */
    public void setOperatelogOpratobjectid(String operatelogOpratobjectid) {
        this.operatelogOpratobjectid = operatelogOpratobjectid;
    }

    /**
     * 获取对象类型,1 成员、账号;2 印章;3 模板;4 档案;5 流程
     *
     * @return operatelog_objecttype - 对象类型,1 成员、账号;2 印章;3 模板;4 档案;5 流程
     */
    public Integer getOperatelogObjecttype() {
        return operatelogObjecttype;
    }

    /**
     * 设置对象类型,1 成员、账号;2 印章;3 模板;4 档案;5 流程
     *
     * @param operatelogObjecttype 对象类型,1 成员、账号;2 印章;3 模板;4 档案;5 流程
     */
    public void setOperatelogObjecttype(Integer operatelogObjecttype) {
        this.operatelogObjecttype = operatelogObjecttype;
    }

    /**
     * 获取状态,1表示成功;2表示失败
     *
     * @return operatelog_state - 状态,1表示成功;2表示失败
     */
    public Integer getOperatelogState() {
        return operatelogState;
    }

    /**
     * 设置状态,1表示成功;2表示失败
     *
     * @param operatelogState 状态,1表示成功;2表示失败
     */
    public void setOperatelogState(Integer operatelogState) {
        this.operatelogState = operatelogState;
    }

    /**
     * 获取说明,
     *
     * @return operatelog_explain - 说明,
     */
    public String getOperatelogExplain() {
        return operatelogExplain;
    }

    /**
     * 设置说明,
     *
     * @param operatelogExplain 说明,
     */
    public void setOperatelogExplain(String operatelogExplain) {
        this.operatelogExplain = operatelogExplain;
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