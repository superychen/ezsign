package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "signrequest")
public class Signrequest {
    /**
     * 签约流水id
     */
    @Id
    @Column(name = "signrequest_id")
    @GeneratedValue(generator = "JDBC")
    private String signrequestId;

    /**
     * 发起人id,对应member或者personinfo的id或者apiid
     */
    @Column(name = "signrequest_createrid")
    private String signrequestCreaterid;

    /**
     * 发起人名称
     */
    @Column(name = "signrequest_creatername")
    private String signrequestCreatername;

    /**
     * 发起人类型,1 个人 personinfo的id;2 企业memberinfo的id;3 api发起apiid
     */
    @Column(name = "signrequest_creatertype")
    private Integer signrequestCreatertype;

    /**
     * 发起时间,
     */
    @Column(name = "signrequest_creattime")
    private Date signrequestCreattime;

    /**
     * 签约来源,1 saas;2 api;3 手机端
     */
    @Column(name = "signrequest_from")
    private Integer signrequestFrom;

    /**
     * 签署状态,1 未发起;2 审批退回;3 审批中;4 审批通过未签署;5 部分签署;6 签署完毕;7 用户拒签;8 保全完毕;9 公证完毕;10 已过期;11 回收站;12 合同中止;13 合同撤销
     */
    @Column(name = "signrequest_status")
    private Integer signrequestStatus;

    /**
     * 所有签约人名称，用“、”隔开,超过存储长度时，直接省略
     */
    @Column(name = "signrequest_signername")
    private String signrequestSignername;

    /**
     * 截止日期,
     */
    @Column(name = "signrequest_expiretime")
    private Date signrequestExpiretime;

    /**
     * 查看合同是否需要短信验证码,1 需要;2 不需要
     */
    @Column(name = "signrequest_withcheckcode")
    private Integer signrequestWithcheckcode;

    /**
     * 是否按顺序签署,1 按顺序;2 不按顺序
     */
    @Column(name = "signrequest_isinorder")
    private Integer signrequestIsinorder;

    /**
     * 是否为批量发起合同,1 是;2 否
     */
    @Column(name = "signrequest_isbatch")
    private Integer signrequestIsbatch;

    /**
     * 发起人自动签章,1是;2否
     */
    @Column(name = "signrequest_automatic")
    private Integer signrequestAutomatic;

    /**
     * 是否加盖保全签章,1 是;2 否
     */
    @Column(name = "signrequest_withbaoquan")
    private Integer signrequestWithbaoquan;

    /**
     * 对应流程id,
     */
    @Column(name = "signrequest_processid")
    private String signrequestProcessid;

    /**
     * 付款类型,1 发起人付费;2 签署人分摊
     */
    @Column(name = "signrequest_paytype")
    private Integer signrequestPaytype;

    /**
     * 当前合同签约人总数,
     */
    @Column(name = "signrequest_signercount")
    private Integer signrequestSignercount;

    /**
     * 合同源文件id,
     */
    @Column(name = "signrequest_origcontractid")
    private String signrequestOrigcontractid;

    /**
     * 当前合同状态说明,主要用于说明是什么原因进行的中止，如密码错误或者认证失败
     */
    @Column(name = "signrequest_statusnote")
    private String signrequestStatusnote;

    /**
     * 结束时间,
     */
    @Column(name = "signrequest_finishtime")
    private Date signrequestFinishtime;

    /**
     * 已完成签约人数量,
     */
    @Column(name = "signrequest_finishcount")
    private Integer signrequestFinishcount;

    /**
     * 当前合同文件id,
     */
    @Column(name = "signrequest_currentcontractid")
    private String signrequestCurrentcontractid;

    /**
     * 生成的保全文件id,
     */
    @Column(name = "signrequest_baoquanfileid")
    private String signrequestBaoquanfileid;

    /**
     * 保全证书文件的hash,
     */
    @Column(name = "signrequest_baoquanfilehash")
    private String signrequestBaoquanfilehash;

    /**
     * 合同详情查看地址,
     */
    @Column(name = "signrequest_detailurl")
    private String signrequestDetailurl;

    /**
     * 1标准签署流程  2用户自认证 3 key签名
     */
    @Column(name = "signrequest_signdevice")
    private Integer signrequestSigndevice;

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
     * 合同审批地址,
     */
    @Column(name = "signrequest_examineurl")
    private String signrequestExamineurl;

    /**
     * 获取签约流水id
     *
     * @return signrequest_id - 签约流水id
     */
    public String getSignrequestId() {
        return signrequestId;
    }

    /**
     * 设置签约流水id
     *
     * @param signrequestId 签约流水id
     */
    public void setSignrequestId(String signrequestId) {
        this.signrequestId = signrequestId;
    }

    /**
     * 获取发起人id,对应member或者personinfo的id或者apiid
     *
     * @return signrequest_createrid - 发起人id,对应member或者personinfo的id或者apiid
     */
    public String getSignrequestCreaterid() {
        return signrequestCreaterid;
    }

    /**
     * 设置发起人id,对应member或者personinfo的id或者apiid
     *
     * @param signrequestCreaterid 发起人id,对应member或者personinfo的id或者apiid
     */
    public void setSignrequestCreaterid(String signrequestCreaterid) {
        this.signrequestCreaterid = signrequestCreaterid;
    }

    /**
     * 获取发起人名称
     *
     * @return signrequest_creatername - 发起人名称
     */
    public String getSignrequestCreatername() {
        return signrequestCreatername;
    }

    /**
     * 设置发起人名称
     *
     * @param signrequestCreatername 发起人名称
     */
    public void setSignrequestCreatername(String signrequestCreatername) {
        this.signrequestCreatername = signrequestCreatername;
    }

    /**
     * 获取发起人类型,1 个人 personinfo的id;2 企业memberinfo的id;3 api发起apiid
     *
     * @return signrequest_creatertype - 发起人类型,1 个人 personinfo的id;2 企业memberinfo的id;3 api发起apiid
     */
    public Integer getSignrequestCreatertype() {
        return signrequestCreatertype;
    }

    /**
     * 设置发起人类型,1 个人 personinfo的id;2 企业memberinfo的id;3 api发起apiid
     *
     * @param signrequestCreatertype 发起人类型,1 个人 personinfo的id;2 企业memberinfo的id;3 api发起apiid
     */
    public void setSignrequestCreatertype(Integer signrequestCreatertype) {
        this.signrequestCreatertype = signrequestCreatertype;
    }

    /**
     * 获取发起时间,
     *
     * @return signrequest_creattime - 发起时间,
     */
    public Date getSignrequestCreattime() {
        return signrequestCreattime;
    }

    /**
     * 设置发起时间,
     *
     * @param signrequestCreattime 发起时间,
     */
    public void setSignrequestCreattime(Date signrequestCreattime) {
        this.signrequestCreattime = signrequestCreattime;
    }

    /**
     * 获取签约来源,1 saas;2 api;3 手机端
     *
     * @return signrequest_from - 签约来源,1 saas;2 api;3 手机端
     */
    public Integer getSignrequestFrom() {
        return signrequestFrom;
    }

    /**
     * 设置签约来源,1 saas;2 api;3 手机端
     *
     * @param signrequestFrom 签约来源,1 saas;2 api;3 手机端
     */
    public void setSignrequestFrom(Integer signrequestFrom) {
        this.signrequestFrom = signrequestFrom;
    }

    /**
     * 获取签署状态,1 未发起;2 审批退回;3 审批中;4 审批通过未签署;5 部分签署;6 签署完毕;7 用户拒签;8 保全完毕;9 公证完毕;10 已过期;11 回收站;12 合同中止;13 合同撤销
     *
     * @return signrequest_status - 签署状态,1 未发起;2 审批退回;3 审批中;4 审批通过未签署;5 部分签署;6 签署完毕;7 用户拒签;8 保全完毕;9 公证完毕;10 已过期;11 回收站;12 合同中止;13 合同撤销
     */
    public Integer getSignrequestStatus() {
        return signrequestStatus;
    }

    /**
     * 设置签署状态,1 未发起;2 审批退回;3 审批中;4 审批通过未签署;5 部分签署;6 签署完毕;7 用户拒签;8 保全完毕;9 公证完毕;10 已过期;11 回收站;12 合同中止;13 合同撤销
     *
     * @param signrequestStatus 签署状态,1 未发起;2 审批退回;3 审批中;4 审批通过未签署;5 部分签署;6 签署完毕;7 用户拒签;8 保全完毕;9 公证完毕;10 已过期;11 回收站;12 合同中止;13 合同撤销
     */
    public void setSignrequestStatus(Integer signrequestStatus) {
        this.signrequestStatus = signrequestStatus;
    }

    /**
     * 获取所有签约人名称，用“、”隔开,超过存储长度时，直接省略
     *
     * @return signrequest_signername - 所有签约人名称，用“、”隔开,超过存储长度时，直接省略
     */
    public String getSignrequestSignername() {
        return signrequestSignername;
    }

    /**
     * 设置所有签约人名称，用“、”隔开,超过存储长度时，直接省略
     *
     * @param signrequestSignername 所有签约人名称，用“、”隔开,超过存储长度时，直接省略
     */
    public void setSignrequestSignername(String signrequestSignername) {
        this.signrequestSignername = signrequestSignername;
    }

    /**
     * 获取截止日期,
     *
     * @return signrequest_expiretime - 截止日期,
     */
    public Date getSignrequestExpiretime() {
        return signrequestExpiretime;
    }

    /**
     * 设置截止日期,
     *
     * @param signrequestExpiretime 截止日期,
     */
    public void setSignrequestExpiretime(Date signrequestExpiretime) {
        this.signrequestExpiretime = signrequestExpiretime;
    }

    /**
     * 获取查看合同是否需要短信验证码,1 需要;2 不需要
     *
     * @return signrequest_withcheckcode - 查看合同是否需要短信验证码,1 需要;2 不需要
     */
    public Integer getSignrequestWithcheckcode() {
        return signrequestWithcheckcode;
    }

    /**
     * 设置查看合同是否需要短信验证码,1 需要;2 不需要
     *
     * @param signrequestWithcheckcode 查看合同是否需要短信验证码,1 需要;2 不需要
     */
    public void setSignrequestWithcheckcode(Integer signrequestWithcheckcode) {
        this.signrequestWithcheckcode = signrequestWithcheckcode;
    }

    /**
     * 获取是否按顺序签署,1 按顺序;2 不按顺序
     *
     * @return signrequest_isinorder - 是否按顺序签署,1 按顺序;2 不按顺序
     */
    public Integer getSignrequestIsinorder() {
        return signrequestIsinorder;
    }

    /**
     * 设置是否按顺序签署,1 按顺序;2 不按顺序
     *
     * @param signrequestIsinorder 是否按顺序签署,1 按顺序;2 不按顺序
     */
    public void setSignrequestIsinorder(Integer signrequestIsinorder) {
        this.signrequestIsinorder = signrequestIsinorder;
    }

    /**
     * 获取是否为批量发起合同,1 是;2 否
     *
     * @return signrequest_isbatch - 是否为批量发起合同,1 是;2 否
     */
    public Integer getSignrequestIsbatch() {
        return signrequestIsbatch;
    }

    /**
     * 设置是否为批量发起合同,1 是;2 否
     *
     * @param signrequestIsbatch 是否为批量发起合同,1 是;2 否
     */
    public void setSignrequestIsbatch(Integer signrequestIsbatch) {
        this.signrequestIsbatch = signrequestIsbatch;
    }

    /**
     * 获取发起人自动签章,1是;2否
     *
     * @return signrequest_automatic - 发起人自动签章,1是;2否
     */
    public Integer getSignrequestAutomatic() {
        return signrequestAutomatic;
    }

    /**
     * 设置发起人自动签章,1是;2否
     *
     * @param signrequestAutomatic 发起人自动签章,1是;2否
     */
    public void setSignrequestAutomatic(Integer signrequestAutomatic) {
        this.signrequestAutomatic = signrequestAutomatic;
    }

    /**
     * 获取是否加盖保全签章,1 是;2 否
     *
     * @return signrequest_withbaoquan - 是否加盖保全签章,1 是;2 否
     */
    public Integer getSignrequestWithbaoquan() {
        return signrequestWithbaoquan;
    }

    /**
     * 设置是否加盖保全签章,1 是;2 否
     *
     * @param signrequestWithbaoquan 是否加盖保全签章,1 是;2 否
     */
    public void setSignrequestWithbaoquan(Integer signrequestWithbaoquan) {
        this.signrequestWithbaoquan = signrequestWithbaoquan;
    }

    /**
     * 获取对应流程id,
     *
     * @return signrequest_processid - 对应流程id,
     */
    public String getSignrequestProcessid() {
        return signrequestProcessid;
    }

    /**
     * 设置对应流程id,
     *
     * @param signrequestProcessid 对应流程id,
     */
    public void setSignrequestProcessid(String signrequestProcessid) {
        this.signrequestProcessid = signrequestProcessid;
    }

    /**
     * 获取付款类型,1 发起人付费;2 签署人分摊
     *
     * @return signrequest_paytype - 付款类型,1 发起人付费;2 签署人分摊
     */
    public Integer getSignrequestPaytype() {
        return signrequestPaytype;
    }

    /**
     * 设置付款类型,1 发起人付费;2 签署人分摊
     *
     * @param signrequestPaytype 付款类型,1 发起人付费;2 签署人分摊
     */
    public void setSignrequestPaytype(Integer signrequestPaytype) {
        this.signrequestPaytype = signrequestPaytype;
    }

    /**
     * 获取当前合同签约人总数,
     *
     * @return signrequest_signercount - 当前合同签约人总数,
     */
    public Integer getSignrequestSignercount() {
        return signrequestSignercount;
    }

    /**
     * 设置当前合同签约人总数,
     *
     * @param signrequestSignercount 当前合同签约人总数,
     */
    public void setSignrequestSignercount(Integer signrequestSignercount) {
        this.signrequestSignercount = signrequestSignercount;
    }

    /**
     * 获取合同源文件id,
     *
     * @return signrequest_origcontractid - 合同源文件id,
     */
    public String getSignrequestOrigcontractid() {
        return signrequestOrigcontractid;
    }

    /**
     * 设置合同源文件id,
     *
     * @param signrequestOrigcontractid 合同源文件id,
     */
    public void setSignrequestOrigcontractid(String signrequestOrigcontractid) {
        this.signrequestOrigcontractid = signrequestOrigcontractid;
    }

    /**
     * 获取当前合同状态说明,主要用于说明是什么原因进行的中止，如密码错误或者认证失败
     *
     * @return signrequest_statusnote - 当前合同状态说明,主要用于说明是什么原因进行的中止，如密码错误或者认证失败
     */
    public String getSignrequestStatusnote() {
        return signrequestStatusnote;
    }

    /**
     * 设置当前合同状态说明,主要用于说明是什么原因进行的中止，如密码错误或者认证失败
     *
     * @param signrequestStatusnote 当前合同状态说明,主要用于说明是什么原因进行的中止，如密码错误或者认证失败
     */
    public void setSignrequestStatusnote(String signrequestStatusnote) {
        this.signrequestStatusnote = signrequestStatusnote;
    }

    /**
     * 获取结束时间,
     *
     * @return signrequest_finishtime - 结束时间,
     */
    public Date getSignrequestFinishtime() {
        return signrequestFinishtime;
    }

    /**
     * 设置结束时间,
     *
     * @param signrequestFinishtime 结束时间,
     */
    public void setSignrequestFinishtime(Date signrequestFinishtime) {
        this.signrequestFinishtime = signrequestFinishtime;
    }

    /**
     * 获取已完成签约人数量,
     *
     * @return signrequest_finishcount - 已完成签约人数量,
     */
    public Integer getSignrequestFinishcount() {
        return signrequestFinishcount;
    }

    /**
     * 设置已完成签约人数量,
     *
     * @param signrequestFinishcount 已完成签约人数量,
     */
    public void setSignrequestFinishcount(Integer signrequestFinishcount) {
        this.signrequestFinishcount = signrequestFinishcount;
    }

    /**
     * 获取当前合同文件id,
     *
     * @return signrequest_currentcontractid - 当前合同文件id,
     */
    public String getSignrequestCurrentcontractid() {
        return signrequestCurrentcontractid;
    }

    /**
     * 设置当前合同文件id,
     *
     * @param signrequestCurrentcontractid 当前合同文件id,
     */
    public void setSignrequestCurrentcontractid(String signrequestCurrentcontractid) {
        this.signrequestCurrentcontractid = signrequestCurrentcontractid;
    }

    /**
     * 获取生成的保全文件id,
     *
     * @return signrequest_baoquanfileid - 生成的保全文件id,
     */
    public String getSignrequestBaoquanfileid() {
        return signrequestBaoquanfileid;
    }

    /**
     * 设置生成的保全文件id,
     *
     * @param signrequestBaoquanfileid 生成的保全文件id,
     */
    public void setSignrequestBaoquanfileid(String signrequestBaoquanfileid) {
        this.signrequestBaoquanfileid = signrequestBaoquanfileid;
    }

    /**
     * 获取保全证书文件的hash,
     *
     * @return signrequest_baoquanfilehash - 保全证书文件的hash,
     */
    public String getSignrequestBaoquanfilehash() {
        return signrequestBaoquanfilehash;
    }

    /**
     * 设置保全证书文件的hash,
     *
     * @param signrequestBaoquanfilehash 保全证书文件的hash,
     */
    public void setSignrequestBaoquanfilehash(String signrequestBaoquanfilehash) {
        this.signrequestBaoquanfilehash = signrequestBaoquanfilehash;
    }

    /**
     * 获取合同详情查看地址,
     *
     * @return signrequest_detailurl - 合同详情查看地址,
     */
    public String getSignrequestDetailurl() {
        return signrequestDetailurl;
    }

    /**
     * 设置合同详情查看地址,
     *
     * @param signrequestDetailurl 合同详情查看地址,
     */
    public void setSignrequestDetailurl(String signrequestDetailurl) {
        this.signrequestDetailurl = signrequestDetailurl;
    }

    /**
     * 获取1标准签署流程  2用户自认证 3 key签名
     *
     * @return signrequest_signdevice - 1标准签署流程  2用户自认证 3 key签名
     */
    public Integer getSignrequestSigndevice() {
        return signrequestSigndevice;
    }

    /**
     * 设置1标准签署流程  2用户自认证 3 key签名
     *
     * @param signrequestSigndevice 1标准签署流程  2用户自认证 3 key签名
     */
    public void setSignrequestSigndevice(Integer signrequestSigndevice) {
        this.signrequestSigndevice = signrequestSigndevice;
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

    /**
     * 获取合同审批地址,
     *
     * @return signrequest_examineurl - 合同审批地址,
     */
    public String getSignrequestExamineurl() {
        return signrequestExamineurl;
    }

    /**
     * 设置合同审批地址,
     *
     * @param signrequestExamineurl 合同审批地址,
     */
    public void setSignrequestExamineurl(String signrequestExamineurl) {
        this.signrequestExamineurl = signrequestExamineurl;
    }
}