package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "signerauth")
public class Signerauth {
    /**
     * 身份认证流水id,
     */
    @Id
    @Column(name = "signerauth_id")
    @GeneratedValue(generator = "JDBC")
    private String signerauthId;

    /**
     * 对应认证的签署人的id,
     */
    @Column(name = "signerauth_signerinfoid")
    private String signerauthSignerinfoid;

    /**
     * 认证状态,0 未做认证;1 认证通过;2 认证未通过
     */
    @Column(name = "signerauth_authstatus")
    private Integer signerauthAuthstatus;

    /**
     * 认证时间（最后一次）,
     */
    @Column(name = "signerauth_authtime")
    private Date signerauthAuthtime;

    /**
     * 1短信 ;2人脸识别;3银行卡三要素;4银行卡四要素,
     */
    @Column(name = "signerauth_authtype")
    private Integer signerauthAuthtype;

    /**
     * 认证次数,
     */
    @Column(name = "signerauth_count")
    private Integer signerauthCount;

    /**
     * 对应的认证流水的平台返回的id,
     */
    @Column(name = "signerauth_authapiid")
    private String signerauthAuthapiid;

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
     * 获取身份认证流水id,
     *
     * @return signerauth_id - 身份认证流水id,
     */
    public String getSignerauthId() {
        return signerauthId;
    }

    /**
     * 设置身份认证流水id,
     *
     * @param signerauthId 身份认证流水id,
     */
    public void setSignerauthId(String signerauthId) {
        this.signerauthId = signerauthId;
    }

    /**
     * 获取对应认证的签署人的id,
     *
     * @return signerauth_signerinfoid - 对应认证的签署人的id,
     */
    public String getSignerauthSignerinfoid() {
        return signerauthSignerinfoid;
    }

    /**
     * 设置对应认证的签署人的id,
     *
     * @param signerauthSignerinfoid 对应认证的签署人的id,
     */
    public void setSignerauthSignerinfoid(String signerauthSignerinfoid) {
        this.signerauthSignerinfoid = signerauthSignerinfoid;
    }

    /**
     * 获取认证状态,0 未做认证;1 认证通过;2 认证未通过
     *
     * @return signerauth_authstatus - 认证状态,0 未做认证;1 认证通过;2 认证未通过
     */
    public Integer getSignerauthAuthstatus() {
        return signerauthAuthstatus;
    }

    /**
     * 设置认证状态,0 未做认证;1 认证通过;2 认证未通过
     *
     * @param signerauthAuthstatus 认证状态,0 未做认证;1 认证通过;2 认证未通过
     */
    public void setSignerauthAuthstatus(Integer signerauthAuthstatus) {
        this.signerauthAuthstatus = signerauthAuthstatus;
    }

    /**
     * 获取认证时间（最后一次）,
     *
     * @return signerauth_authtime - 认证时间（最后一次）,
     */
    public Date getSignerauthAuthtime() {
        return signerauthAuthtime;
    }

    /**
     * 设置认证时间（最后一次）,
     *
     * @param signerauthAuthtime 认证时间（最后一次）,
     */
    public void setSignerauthAuthtime(Date signerauthAuthtime) {
        this.signerauthAuthtime = signerauthAuthtime;
    }

    /**
     * 获取1短信 ;2人脸识别;3银行卡三要素;4银行卡四要素,
     *
     * @return signerauth_authtype - 1短信 ;2人脸识别;3银行卡三要素;4银行卡四要素,
     */
    public Integer getSignerauthAuthtype() {
        return signerauthAuthtype;
    }

    /**
     * 设置1短信 ;2人脸识别;3银行卡三要素;4银行卡四要素,
     *
     * @param signerauthAuthtype 1短信 ;2人脸识别;3银行卡三要素;4银行卡四要素,
     */
    public void setSignerauthAuthtype(Integer signerauthAuthtype) {
        this.signerauthAuthtype = signerauthAuthtype;
    }

    /**
     * 获取认证次数,
     *
     * @return signerauth_count - 认证次数,
     */
    public Integer getSignerauthCount() {
        return signerauthCount;
    }

    /**
     * 设置认证次数,
     *
     * @param signerauthCount 认证次数,
     */
    public void setSignerauthCount(Integer signerauthCount) {
        this.signerauthCount = signerauthCount;
    }

    /**
     * 获取对应的认证流水的平台返回的id,
     *
     * @return signerauth_authapiid - 对应的认证流水的平台返回的id,
     */
    public String getSignerauthAuthapiid() {
        return signerauthAuthapiid;
    }

    /**
     * 设置对应的认证流水的平台返回的id,
     *
     * @param signerauthAuthapiid 对应的认证流水的平台返回的id,
     */
    public void setSignerauthAuthapiid(String signerauthAuthapiid) {
        this.signerauthAuthapiid = signerauthAuthapiid;
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