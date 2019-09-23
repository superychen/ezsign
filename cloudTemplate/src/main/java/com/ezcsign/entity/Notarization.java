package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "notarization")
public class Notarization {
    /**
     * 公证操作流水id,
     */
    @Id
    @Column(name = "notarization_id")
    @GeneratedValue(generator = "JDBC")
    private String notarizationId;

    /**
     * 公证类型,1 合同文件公证;2 签约过程公证;3 合同文件的司法鉴定
     */
    @Column(name = "notarization_type")
    private Integer notarizationType;

    /**
     * 对应的签约流水号,
     */
    @Column(name = "notarization_signrequestid")
    private String notarizationSignrequestid;

    /**
     * 所操作的hash,
     */
    @Column(name = "notarization_hash")
    private String notarizationHash;

    /**
     * 返回的公证序列号,
     */
    @Column(name = "notarization_serviceid")
    private String notarizationServiceid;

    /**
     * 公证操作时间,
     */
    @Column(name = "notarization_time")
    private Date notarizationTime;

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
     * 获取公证操作流水id,
     *
     * @return notarization_id - 公证操作流水id,
     */
    public String getNotarizationId() {
        return notarizationId;
    }

    /**
     * 设置公证操作流水id,
     *
     * @param notarizationId 公证操作流水id,
     */
    public void setNotarizationId(String notarizationId) {
        this.notarizationId = notarizationId;
    }

    /**
     * 获取公证类型,1 合同文件公证;2 签约过程公证;3 合同文件的司法鉴定
     *
     * @return notarization_type - 公证类型,1 合同文件公证;2 签约过程公证;3 合同文件的司法鉴定
     */
    public Integer getNotarizationType() {
        return notarizationType;
    }

    /**
     * 设置公证类型,1 合同文件公证;2 签约过程公证;3 合同文件的司法鉴定
     *
     * @param notarizationType 公证类型,1 合同文件公证;2 签约过程公证;3 合同文件的司法鉴定
     */
    public void setNotarizationType(Integer notarizationType) {
        this.notarizationType = notarizationType;
    }

    /**
     * 获取对应的签约流水号,
     *
     * @return notarization_signrequestid - 对应的签约流水号,
     */
    public String getNotarizationSignrequestid() {
        return notarizationSignrequestid;
    }

    /**
     * 设置对应的签约流水号,
     *
     * @param notarizationSignrequestid 对应的签约流水号,
     */
    public void setNotarizationSignrequestid(String notarizationSignrequestid) {
        this.notarizationSignrequestid = notarizationSignrequestid;
    }

    /**
     * 获取所操作的hash,
     *
     * @return notarization_hash - 所操作的hash,
     */
    public String getNotarizationHash() {
        return notarizationHash;
    }

    /**
     * 设置所操作的hash,
     *
     * @param notarizationHash 所操作的hash,
     */
    public void setNotarizationHash(String notarizationHash) {
        this.notarizationHash = notarizationHash;
    }

    /**
     * 获取返回的公证序列号,
     *
     * @return notarization_serviceid - 返回的公证序列号,
     */
    public String getNotarizationServiceid() {
        return notarizationServiceid;
    }

    /**
     * 设置返回的公证序列号,
     *
     * @param notarizationServiceid 返回的公证序列号,
     */
    public void setNotarizationServiceid(String notarizationServiceid) {
        this.notarizationServiceid = notarizationServiceid;
    }

    /**
     * 获取公证操作时间,
     *
     * @return notarization_time - 公证操作时间,
     */
    public Date getNotarizationTime() {
        return notarizationTime;
    }

    /**
     * 设置公证操作时间,
     *
     * @param notarizationTime 公证操作时间,
     */
    public void setNotarizationTime(Date notarizationTime) {
        this.notarizationTime = notarizationTime;
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