package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "contract")
public class Contract {
    /**
     * 合同id,
     */
    @Id
    @Column(name = "contract_id")
    @GeneratedValue(generator = "JDBC")
    private String contractId;

    /**
     * 创建者id,
     */
    @Column(name = "contract_createrid")
    private String contractCreaterid;

    /**
     * 对应member或者personinfo的id
     */
    @Column(name = "contract_creatertype")
    private Integer contractCreatertype;

    /**
     * 创建时间,
     */
    @Column(name = "contract_creattime")
    private Date contractCreattime;

    /**
     * 合同名称,
     */
    @Column(name = "contract_name")
    private String contractName;

    /**
     * ,1 pdf;2 word;3 ofd;4 图片
     */
    @Column(name = "contract_filetype")
    private Integer contractFiletype;

    /**
     * 签署的文件类型,1 合同源文件;2 附件（不需要签署的文件）
     */
    @Column(name = "contract_signfiletype")
    private Integer contractSignfiletype;

    /**
     * 对应用户合同模板表usertemplet_usertype
     */
    @Column(name = "contract_templetid")
    private String contractTempletid;

    /**
     * 对应档案库id,
     */
    @Column(name = "contract_comfileid")
    private String contractComfileid;

    /**
     * 对应流程id,
     */
    @Column(name = "contract_processid")
    private String contractProcessid;

    /**
     * 状态,1 可用;2 不可用
     */
    @Column(name = "contract_status")
    private Integer contractStatus;

    /**
     * 文件hash,
     */
    @Column(name = "contract_hash")
    private String contractHash;

    /**
     * 文件存储地址
     */
    @Column(name = "contract_fileid")
    private String contractFileid;

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
     * 获取合同id,
     *
     * @return contract_id - 合同id,
     */
    public String getContractId() {
        return contractId;
    }

    /**
     * 设置合同id,
     *
     * @param contractId 合同id,
     */
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    /**
     * 获取创建者id,
     *
     * @return contract_createrid - 创建者id,
     */
    public String getContractCreaterid() {
        return contractCreaterid;
    }

    /**
     * 设置创建者id,
     *
     * @param contractCreaterid 创建者id,
     */
    public void setContractCreaterid(String contractCreaterid) {
        this.contractCreaterid = contractCreaterid;
    }

    /**
     * 获取对应member或者personinfo的id
     *
     * @return contract_creatertype - 对应member或者personinfo的id
     */
    public Integer getContractCreatertype() {
        return contractCreatertype;
    }

    /**
     * 设置对应member或者personinfo的id
     *
     * @param contractCreatertype 对应member或者personinfo的id
     */
    public void setContractCreatertype(Integer contractCreatertype) {
        this.contractCreatertype = contractCreatertype;
    }

    /**
     * 获取创建时间,
     *
     * @return contract_creattime - 创建时间,
     */
    public Date getContractCreattime() {
        return contractCreattime;
    }

    /**
     * 设置创建时间,
     *
     * @param contractCreattime 创建时间,
     */
    public void setContractCreattime(Date contractCreattime) {
        this.contractCreattime = contractCreattime;
    }

    /**
     * 获取合同名称,
     *
     * @return contract_name - 合同名称,
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * 设置合同名称,
     *
     * @param contractName 合同名称,
     */
    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    /**
     * 获取,1 pdf;2 word;3 ofd;4 图片
     *
     * @return contract_filetype - ,1 pdf;2 word;3 ofd;4 图片
     */
    public Integer getContractFiletype() {
        return contractFiletype;
    }

    /**
     * 设置,1 pdf;2 word;3 ofd;4 图片
     *
     * @param contractFiletype ,1 pdf;2 word;3 ofd;4 图片
     */
    public void setContractFiletype(Integer contractFiletype) {
        this.contractFiletype = contractFiletype;
    }

    /**
     * 获取签署的文件类型,1 合同源文件;2 附件（不需要签署的文件）
     *
     * @return contract_signfiletype - 签署的文件类型,1 合同源文件;2 附件（不需要签署的文件）
     */
    public Integer getContractSignfiletype() {
        return contractSignfiletype;
    }

    /**
     * 设置签署的文件类型,1 合同源文件;2 附件（不需要签署的文件）
     *
     * @param contractSignfiletype 签署的文件类型,1 合同源文件;2 附件（不需要签署的文件）
     */
    public void setContractSignfiletype(Integer contractSignfiletype) {
        this.contractSignfiletype = contractSignfiletype;
    }

    /**
     * 获取对应用户合同模板表usertemplet_usertype
     *
     * @return contract_templetid - 对应用户合同模板表usertemplet_usertype
     */
    public String getContractTempletid() {
        return contractTempletid;
    }

    /**
     * 设置对应用户合同模板表usertemplet_usertype
     *
     * @param contractTempletid 对应用户合同模板表usertemplet_usertype
     */
    public void setContractTempletid(String contractTempletid) {
        this.contractTempletid = contractTempletid;
    }

    /**
     * 获取对应档案库id,
     *
     * @return contract_comfileid - 对应档案库id,
     */
    public String getContractComfileid() {
        return contractComfileid;
    }

    /**
     * 设置对应档案库id,
     *
     * @param contractComfileid 对应档案库id,
     */
    public void setContractComfileid(String contractComfileid) {
        this.contractComfileid = contractComfileid;
    }

    /**
     * 获取对应流程id,
     *
     * @return contract_processid - 对应流程id,
     */
    public String getContractProcessid() {
        return contractProcessid;
    }

    /**
     * 设置对应流程id,
     *
     * @param contractProcessid 对应流程id,
     */
    public void setContractProcessid(String contractProcessid) {
        this.contractProcessid = contractProcessid;
    }

    /**
     * 获取状态,1 可用;2 不可用
     *
     * @return contract_status - 状态,1 可用;2 不可用
     */
    public Integer getContractStatus() {
        return contractStatus;
    }

    /**
     * 设置状态,1 可用;2 不可用
     *
     * @param contractStatus 状态,1 可用;2 不可用
     */
    public void setContractStatus(Integer contractStatus) {
        this.contractStatus = contractStatus;
    }

    /**
     * 获取文件hash,
     *
     * @return contract_hash - 文件hash,
     */
    public String getContractHash() {
        return contractHash;
    }

    /**
     * 设置文件hash,
     *
     * @param contractHash 文件hash,
     */
    public void setContractHash(String contractHash) {
        this.contractHash = contractHash;
    }

    /**
     * 获取文件存储地址
     *
     * @return contract_fileid - 文件存储地址
     */
    public String getContractFileid() {
        return contractFileid;
    }

    /**
     * 设置文件存储地址
     *
     * @param contractFileid 文件存储地址
     */
    public void setContractFileid(String contractFileid) {
        this.contractFileid = contractFileid;
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