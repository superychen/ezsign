package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "signerinfo")
public class Signerinfo {
    /**
     * 签约人信息id,
     */
    @Id
    @Column(name = "signerinfo_id")
    @GeneratedValue(generator = "JDBC")
    private String signerinfoId;

    /**
     * 签约人姓名,
     */
    @Column(name = "signerinfo_name")
    private String signerinfoName;

    /**
     * 签约请求外键,
     */
    @Column(name = "signerinfo_signrequestid")
    private String signerinfoSignrequestid;

    /**
     * ,对应personinfo或者member的id
     */
    @Column(name = "signerinfo_memberid")
    private String signerinfoMemberid;

    /**
     * 签约人账户类型,1 personinfo对应的id;2 memberinfo对应的id
     */
    @Column(name = "signerinfo_membertype")
    private Integer signerinfoMembertype;

    /**
     * 签署设备形式,1 易通签平台签署;2 使用key签署
     */
    @Column(name = "signerinfo_signdevice")
    private Integer signerinfoSigndevice;

    /**
     * 签署状态,1 未签署;2 已签署;3 拒签
     */
    @Column(name = "signerinfo_status")
    private Integer signerinfoStatus;

    /**
     * 签署顺序,如果为按顺序签署，对应签署的顺序号
     */
    @Column(name = "signerinfo_signorder")
    private Integer signerinfoSignorder;

    /**
     * 签署人电话,
     */
    @Column(name = "signerinfo_telephone")
    private String signerinfoTelephone;

    /**
     * 签署人邮箱,
     */
    @Column(name = "signerinfo_email")
    private String signerinfoEmail;

    /**
     * 是否自动签章,1 是
     */
    @Column(name = "signerinfo_automatic")
    private Integer signerinfoAutomatic;

    /**
     * 签章形式,印章代码
     */
    @Column(name = "signerinfo_sealcode")
    private Integer signerinfoSealcode;

    /**
     * 是否指定签章位置,1 发起时候已指定;2 用户自己拖动
     */
    @Column(name = "signerinfo_islocated")
    private Integer signerinfoIslocated;

    /**
     * 签章位置字符串（非json，去除变量名）,结构形式;{[page,posX,posY,sealcode,signtype];};其中，page表示页码;     posX表示签章位置X;     posY表示签章位置Y;     sealcode表示印章代码 ;     signtype对应signinfo_signtype;如果此项为空，签章时默认位置为0,0，并采用该用户的第一个印章
     */
    @Column(name = "signerinfo_sealposit")
    private String signerinfoSealposit;

    /**
     * 签章形式,1 按单个位置签章;2 每一页都盖签章;3 只盖单数页;4 只盖双数页;5 骑缝章（单数页一个位置，双数页一个位置）
     */
    @Column(name = "signerinfo_sealtype")
    private Integer signerinfoSealtype;

    /**
     * 强制阅读秒数,
     */
    @Column(name = "signerinfo_readsecond")
    private Integer signerinfoReadsecond;

    /**
     * 签署时间,
     */
    @Column(name = "signerinfo_signtime")
    private Date signerinfoSigntime;

    /**
     * 拒签理由,80字以内
     */
    @Column(name = "signerinfo_refusereason")
    private String signerinfoRefusereason;

    /**
     * 签约地址,
     */
    @Column(name = "signerinfo_signurl")
    private String signerinfoSignurl;

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
     * 获取签约人信息id,
     *
     * @return signerinfo_id - 签约人信息id,
     */
    public String getSignerinfoId() {
        return signerinfoId;
    }

    /**
     * 设置签约人信息id,
     *
     * @param signerinfoId 签约人信息id,
     */
    public void setSignerinfoId(String signerinfoId) {
        this.signerinfoId = signerinfoId;
    }

    /**
     * 获取签约人姓名,
     *
     * @return signerinfo_name - 签约人姓名,
     */
    public String getSignerinfoName() {
        return signerinfoName;
    }

    /**
     * 设置签约人姓名,
     *
     * @param signerinfoName 签约人姓名,
     */
    public void setSignerinfoName(String signerinfoName) {
        this.signerinfoName = signerinfoName;
    }

    /**
     * 获取签约请求外键,
     *
     * @return signerinfo_signrequestid - 签约请求外键,
     */
    public String getSignerinfoSignrequestid() {
        return signerinfoSignrequestid;
    }

    /**
     * 设置签约请求外键,
     *
     * @param signerinfoSignrequestid 签约请求外键,
     */
    public void setSignerinfoSignrequestid(String signerinfoSignrequestid) {
        this.signerinfoSignrequestid = signerinfoSignrequestid;
    }

    /**
     * 获取,对应personinfo或者member的id
     *
     * @return signerinfo_memberid - ,对应personinfo或者member的id
     */
    public String getSignerinfoMemberid() {
        return signerinfoMemberid;
    }

    /**
     * 设置,对应personinfo或者member的id
     *
     * @param signerinfoMemberid ,对应personinfo或者member的id
     */
    public void setSignerinfoMemberid(String signerinfoMemberid) {
        this.signerinfoMemberid = signerinfoMemberid;
    }

    /**
     * 获取签约人账户类型,1 personinfo对应的id;2 memberinfo对应的id
     *
     * @return signerinfo_membertype - 签约人账户类型,1 personinfo对应的id;2 memberinfo对应的id
     */
    public Integer getSignerinfoMembertype() {
        return signerinfoMembertype;
    }

    /**
     * 设置签约人账户类型,1 personinfo对应的id;2 memberinfo对应的id
     *
     * @param signerinfoMembertype 签约人账户类型,1 personinfo对应的id;2 memberinfo对应的id
     */
    public void setSignerinfoMembertype(Integer signerinfoMembertype) {
        this.signerinfoMembertype = signerinfoMembertype;
    }

    /**
     * 获取签署设备形式,1 易通签平台签署;2 使用key签署
     *
     * @return signerinfo_signdevice - 签署设备形式,1 易通签平台签署;2 使用key签署
     */
    public Integer getSignerinfoSigndevice() {
        return signerinfoSigndevice;
    }

    /**
     * 设置签署设备形式,1 易通签平台签署;2 使用key签署
     *
     * @param signerinfoSigndevice 签署设备形式,1 易通签平台签署;2 使用key签署
     */
    public void setSignerinfoSigndevice(Integer signerinfoSigndevice) {
        this.signerinfoSigndevice = signerinfoSigndevice;
    }

    /**
     * 获取签署状态,1 未签署;2 已签署;3 拒签
     *
     * @return signerinfo_status - 签署状态,1 未签署;2 已签署;3 拒签
     */
    public Integer getSignerinfoStatus() {
        return signerinfoStatus;
    }

    /**
     * 设置签署状态,1 未签署;2 已签署;3 拒签
     *
     * @param signerinfoStatus 签署状态,1 未签署;2 已签署;3 拒签
     */
    public void setSignerinfoStatus(Integer signerinfoStatus) {
        this.signerinfoStatus = signerinfoStatus;
    }

    /**
     * 获取签署顺序,如果为按顺序签署，对应签署的顺序号
     *
     * @return signerinfo_signorder - 签署顺序,如果为按顺序签署，对应签署的顺序号
     */
    public Integer getSignerinfoSignorder() {
        return signerinfoSignorder;
    }

    /**
     * 设置签署顺序,如果为按顺序签署，对应签署的顺序号
     *
     * @param signerinfoSignorder 签署顺序,如果为按顺序签署，对应签署的顺序号
     */
    public void setSignerinfoSignorder(Integer signerinfoSignorder) {
        this.signerinfoSignorder = signerinfoSignorder;
    }

    /**
     * 获取签署人电话,
     *
     * @return signerinfo_telephone - 签署人电话,
     */
    public String getSignerinfoTelephone() {
        return signerinfoTelephone;
    }

    /**
     * 设置签署人电话,
     *
     * @param signerinfoTelephone 签署人电话,
     */
    public void setSignerinfoTelephone(String signerinfoTelephone) {
        this.signerinfoTelephone = signerinfoTelephone;
    }

    /**
     * 获取签署人邮箱,
     *
     * @return signerinfo_email - 签署人邮箱,
     */
    public String getSignerinfoEmail() {
        return signerinfoEmail;
    }

    /**
     * 设置签署人邮箱,
     *
     * @param signerinfoEmail 签署人邮箱,
     */
    public void setSignerinfoEmail(String signerinfoEmail) {
        this.signerinfoEmail = signerinfoEmail;
    }

    /**
     * 获取是否自动签章,1 是
     *
     * @return signerinfo_automatic - 是否自动签章,1 是
     */
    public Integer getSignerinfoAutomatic() {
        return signerinfoAutomatic;
    }

    /**
     * 设置是否自动签章,1 是
     *
     * @param signerinfoAutomatic 是否自动签章,1 是
     */
    public void setSignerinfoAutomatic(Integer signerinfoAutomatic) {
        this.signerinfoAutomatic = signerinfoAutomatic;
    }

    /**
     * 获取签章形式,印章代码
     *
     * @return signerinfo_sealcode - 签章形式,印章代码
     */
    public Integer getSignerinfoSealcode() {
        return signerinfoSealcode;
    }

    /**
     * 设置签章形式,印章代码
     *
     * @param signerinfoSealcode 签章形式,印章代码
     */
    public void setSignerinfoSealcode(Integer signerinfoSealcode) {
        this.signerinfoSealcode = signerinfoSealcode;
    }

    /**
     * 获取是否指定签章位置,1 发起时候已指定;2 用户自己拖动
     *
     * @return signerinfo_islocated - 是否指定签章位置,1 发起时候已指定;2 用户自己拖动
     */
    public Integer getSignerinfoIslocated() {
        return signerinfoIslocated;
    }

    /**
     * 设置是否指定签章位置,1 发起时候已指定;2 用户自己拖动
     *
     * @param signerinfoIslocated 是否指定签章位置,1 发起时候已指定;2 用户自己拖动
     */
    public void setSignerinfoIslocated(Integer signerinfoIslocated) {
        this.signerinfoIslocated = signerinfoIslocated;
    }

    /**
     * 获取签章位置字符串（非json，去除变量名）,结构形式;{[page,posX,posY,sealcode,signtype];};其中，page表示页码;     posX表示签章位置X;     posY表示签章位置Y;     sealcode表示印章代码 ;     signtype对应signinfo_signtype;如果此项为空，签章时默认位置为0,0，并采用该用户的第一个印章
     *
     * @return signerinfo_sealposit - 签章位置字符串（非json，去除变量名）,结构形式;{[page,posX,posY,sealcode,signtype];};其中，page表示页码;     posX表示签章位置X;     posY表示签章位置Y;     sealcode表示印章代码 ;     signtype对应signinfo_signtype;如果此项为空，签章时默认位置为0,0，并采用该用户的第一个印章
     */
    public String getSignerinfoSealposit() {
        return signerinfoSealposit;
    }

    /**
     * 设置签章位置字符串（非json，去除变量名）,结构形式;{[page,posX,posY,sealcode,signtype];};其中，page表示页码;     posX表示签章位置X;     posY表示签章位置Y;     sealcode表示印章代码 ;     signtype对应signinfo_signtype;如果此项为空，签章时默认位置为0,0，并采用该用户的第一个印章
     *
     * @param signerinfoSealposit 签章位置字符串（非json，去除变量名）,结构形式;{[page,posX,posY,sealcode,signtype];};其中，page表示页码;     posX表示签章位置X;     posY表示签章位置Y;     sealcode表示印章代码 ;     signtype对应signinfo_signtype;如果此项为空，签章时默认位置为0,0，并采用该用户的第一个印章
     */
    public void setSignerinfoSealposit(String signerinfoSealposit) {
        this.signerinfoSealposit = signerinfoSealposit;
    }

    /**
     * 获取签章形式,1 按单个位置签章;2 每一页都盖签章;3 只盖单数页;4 只盖双数页;5 骑缝章（单数页一个位置，双数页一个位置）
     *
     * @return signerinfo_sealtype - 签章形式,1 按单个位置签章;2 每一页都盖签章;3 只盖单数页;4 只盖双数页;5 骑缝章（单数页一个位置，双数页一个位置）
     */
    public Integer getSignerinfoSealtype() {
        return signerinfoSealtype;
    }

    /**
     * 设置签章形式,1 按单个位置签章;2 每一页都盖签章;3 只盖单数页;4 只盖双数页;5 骑缝章（单数页一个位置，双数页一个位置）
     *
     * @param signerinfoSealtype 签章形式,1 按单个位置签章;2 每一页都盖签章;3 只盖单数页;4 只盖双数页;5 骑缝章（单数页一个位置，双数页一个位置）
     */
    public void setSignerinfoSealtype(Integer signerinfoSealtype) {
        this.signerinfoSealtype = signerinfoSealtype;
    }

    /**
     * 获取强制阅读秒数,
     *
     * @return signerinfo_readsecond - 强制阅读秒数,
     */
    public Integer getSignerinfoReadsecond() {
        return signerinfoReadsecond;
    }

    /**
     * 设置强制阅读秒数,
     *
     * @param signerinfoReadsecond 强制阅读秒数,
     */
    public void setSignerinfoReadsecond(Integer signerinfoReadsecond) {
        this.signerinfoReadsecond = signerinfoReadsecond;
    }

    /**
     * 获取签署时间,
     *
     * @return signerinfo_signtime - 签署时间,
     */
    public Date getSignerinfoSigntime() {
        return signerinfoSigntime;
    }

    /**
     * 设置签署时间,
     *
     * @param signerinfoSigntime 签署时间,
     */
    public void setSignerinfoSigntime(Date signerinfoSigntime) {
        this.signerinfoSigntime = signerinfoSigntime;
    }

    /**
     * 获取拒签理由,80字以内
     *
     * @return signerinfo_refusereason - 拒签理由,80字以内
     */
    public String getSignerinfoRefusereason() {
        return signerinfoRefusereason;
    }

    /**
     * 设置拒签理由,80字以内
     *
     * @param signerinfoRefusereason 拒签理由,80字以内
     */
    public void setSignerinfoRefusereason(String signerinfoRefusereason) {
        this.signerinfoRefusereason = signerinfoRefusereason;
    }

    /**
     * 获取签约地址,
     *
     * @return signerinfo_signurl - 签约地址,
     */
    public String getSignerinfoSignurl() {
        return signerinfoSignurl;
    }

    /**
     * 设置签约地址,
     *
     * @param signerinfoSignurl 签约地址,
     */
    public void setSignerinfoSignurl(String signerinfoSignurl) {
        this.signerinfoSignurl = signerinfoSignurl;
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