package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "certinfo")
public class Certinfo {
    /**
     * 证书信息ID
     */
    @Id
    @Column(name = "certinfo_id")
    @GeneratedValue(generator = "JDBC")
    private String certinfoId;

    /**
     * 证书类型:1=个人，2=企业
     */
    @Column(name = "certinfo_type")
    private Integer certinfoType;

    /**
     * 使用方ID:证书类型=1时为person_info_id,否则为company_info_id
     */
    @Column(name = "certinfo_userid")
    private String certinfoUserid;

    /**
     * 证书序列号
     */
    @Column(name = "certinfo_sn")
    private String certinfoSn;

    /**
     * 证书DN
     */
    @Column(name = "certinfo_dn")
    private String certinfoDn;

    /**
     * 证书算法
     */
    @Column(name = "certinfo_alg")
    private String certinfoAlg;

    /**
     * 证书生效日期
     */
    @Column(name = "certinfo_fromtime")
    private Date certinfoFromtime;

    /**
     * 证书失效日期
     */
    @Column(name = "certinfo_endtime")
    private Date certinfoEndtime;

    /**
     * 证书颁发者
     */
    @Column(name = "certinfo_issuer")
    private String certinfoIssuer;

    /**
     * 证书状态:0=有效;1=失效
     */
    @Column(name = "certinfo_state")
    private Integer certinfoState;

    /**
     * 证书DN
     */
    @Column(name = "certinfo_pwd")
    private String certinfoPwd;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 创建者ID
     */
    @Column(name = "create_id")
    private String createId;

    /**
     * 创建者姓名
     */
    @Column(name = "create_name")
    private String createName;

    /**
     * 修改者ID
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
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 数据状态:0=正常数据;1=逻辑删除
     */
    private Integer status;

    /**
     * 证书PFX,二进制文件
     */
    @Column(name = "certinfo_pfx")
    private byte[] certinfoPfx;

    /**
     * 证书CER文件,二进制文件
     */
    @Column(name = "certinfo_cer")
    private byte[] certinfoCer;

    /**
     * 获取证书信息ID
     *
     * @return certinfo_id - 证书信息ID
     */
    public String getCertinfoId() {
        return certinfoId;
    }

    /**
     * 设置证书信息ID
     *
     * @param certinfoId 证书信息ID
     */
    public void setCertinfoId(String certinfoId) {
        this.certinfoId = certinfoId;
    }

    /**
     * 获取证书类型:1=个人，2=企业
     *
     * @return certinfo_type - 证书类型:1=个人，2=企业
     */
    public Integer getCertinfoType() {
        return certinfoType;
    }

    /**
     * 设置证书类型:1=个人，2=企业
     *
     * @param certinfoType 证书类型:1=个人，2=企业
     */
    public void setCertinfoType(Integer certinfoType) {
        this.certinfoType = certinfoType;
    }

    /**
     * 获取使用方ID:证书类型=1时为person_info_id,否则为company_info_id
     *
     * @return certinfo_userid - 使用方ID:证书类型=1时为person_info_id,否则为company_info_id
     */
    public String getCertinfoUserid() {
        return certinfoUserid;
    }

    /**
     * 设置使用方ID:证书类型=1时为person_info_id,否则为company_info_id
     *
     * @param certinfoUserid 使用方ID:证书类型=1时为person_info_id,否则为company_info_id
     */
    public void setCertinfoUserid(String certinfoUserid) {
        this.certinfoUserid = certinfoUserid;
    }

    /**
     * 获取证书序列号
     *
     * @return certinfo_sn - 证书序列号
     */
    public String getCertinfoSn() {
        return certinfoSn;
    }

    /**
     * 设置证书序列号
     *
     * @param certinfoSn 证书序列号
     */
    public void setCertinfoSn(String certinfoSn) {
        this.certinfoSn = certinfoSn;
    }

    /**
     * 获取证书DN
     *
     * @return certinfo_dn - 证书DN
     */
    public String getCertinfoDn() {
        return certinfoDn;
    }

    /**
     * 设置证书DN
     *
     * @param certinfoDn 证书DN
     */
    public void setCertinfoDn(String certinfoDn) {
        this.certinfoDn = certinfoDn;
    }

    /**
     * 获取证书算法
     *
     * @return certinfo_alg - 证书算法
     */
    public String getCertinfoAlg() {
        return certinfoAlg;
    }

    /**
     * 设置证书算法
     *
     * @param certinfoAlg 证书算法
     */
    public void setCertinfoAlg(String certinfoAlg) {
        this.certinfoAlg = certinfoAlg;
    }

    /**
     * 获取证书生效日期
     *
     * @return certinfo_fromtime - 证书生效日期
     */
    public Date getCertinfoFromtime() {
        return certinfoFromtime;
    }

    /**
     * 设置证书生效日期
     *
     * @param certinfoFromtime 证书生效日期
     */
    public void setCertinfoFromtime(Date certinfoFromtime) {
        this.certinfoFromtime = certinfoFromtime;
    }

    /**
     * 获取证书失效日期
     *
     * @return certinfo_endtime - 证书失效日期
     */
    public Date getCertinfoEndtime() {
        return certinfoEndtime;
    }

    /**
     * 设置证书失效日期
     *
     * @param certinfoEndtime 证书失效日期
     */
    public void setCertinfoEndtime(Date certinfoEndtime) {
        this.certinfoEndtime = certinfoEndtime;
    }

    /**
     * 获取证书颁发者
     *
     * @return certinfo_issuer - 证书颁发者
     */
    public String getCertinfoIssuer() {
        return certinfoIssuer;
    }

    /**
     * 设置证书颁发者
     *
     * @param certinfoIssuer 证书颁发者
     */
    public void setCertinfoIssuer(String certinfoIssuer) {
        this.certinfoIssuer = certinfoIssuer;
    }

    /**
     * 获取证书状态:0=有效;1=失效
     *
     * @return certinfo_state - 证书状态:0=有效;1=失效
     */
    public Integer getCertinfoState() {
        return certinfoState;
    }

    /**
     * 设置证书状态:0=有效;1=失效
     *
     * @param certinfoState 证书状态:0=有效;1=失效
     */
    public void setCertinfoState(Integer certinfoState) {
        this.certinfoState = certinfoState;
    }

    /**
     * 获取证书DN
     *
     * @return certinfo_pwd - 证书DN
     */
    public String getCertinfoPwd() {
        return certinfoPwd;
    }

    /**
     * 设置证书DN
     *
     * @param certinfoPwd 证书DN
     */
    public void setCertinfoPwd(String certinfoPwd) {
        this.certinfoPwd = certinfoPwd;
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
     * 获取创建者ID
     *
     * @return create_id - 创建者ID
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 设置创建者ID
     *
     * @param createId 创建者ID
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
     * 获取修改者ID
     *
     * @return update_id - 修改者ID
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * 设置修改者ID
     *
     * @param updateId 修改者ID
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
     * @return update_date - 修改者时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置修改者时间
     *
     * @param updateDate 修改者时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
     * 获取证书PFX,二进制文件
     *
     * @return certinfo_pfx - 证书PFX,二进制文件
     */
    public byte[] getCertinfoPfx() {
        return certinfoPfx;
    }

    /**
     * 设置证书PFX,二进制文件
     *
     * @param certinfoPfx 证书PFX,二进制文件
     */
    public void setCertinfoPfx(byte[] certinfoPfx) {
        this.certinfoPfx = certinfoPfx;
    }

    /**
     * 获取证书CER文件,二进制文件
     *
     * @return certinfo_cer - 证书CER文件,二进制文件
     */
    public byte[] getCertinfoCer() {
        return certinfoCer;
    }

    /**
     * 设置证书CER文件,二进制文件
     *
     * @param certinfoCer 证书CER文件,二进制文件
     */
    public void setCertinfoCer(byte[] certinfoCer) {
        this.certinfoCer = certinfoCer;
    }
}