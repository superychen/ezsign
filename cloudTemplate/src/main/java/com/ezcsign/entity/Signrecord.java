package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "signrecord")
public class Signrecord {
    /**
     * 用户签约详情id
     */
    @Id
    @Column(name = "signrecord_id")
    @GeneratedValue(generator = "JDBC")
    private String signrecordId;

    /**
     * 1 注册;2 认证;3 发起签约;4 签署认证;5 合同签署;6 合同保全;7 上传公证处;8 司法鉴定中心;9 对接在线仲裁单位
     */
    @Column(name = "signrecord_type")
    private Integer signrecordType;

    /**
     * 详细内容,1某某注册了账号*****;2某某通过什么认证方式，在易通签平台进行了身份认证，认证信息为：;3某某发起一份合同签约，签约序列号为，合同名称为，（合同的原文件hash）;4谁通过什么方式进行了身份认证，对应的认证流水id为*。
     */
    @Column(name = "signrecord_recordinfo")
    private String signrecordRecordinfo;

    /**
     * 本条操作的hash,
     */
    @Column(name = "signrecord_hash")
    private String signrecordHash;

    /**
     * 操作时间,
     */
    @Column(name = "signrecord_time")
    private Date signrecordTime;

    /**
     * 操作的ip,
     */
    @Column(name = "signrecord_ip")
    private String signrecordIp;

    /**
     * 对应签约流水id,
     */
    @Column(name = "signrecord_signrequestid")
    private String signrecordSignrequestid;

    /**
     * 对应的操作返回的流水id,
     */
    @Column(name = "signrecord_serviceid")
    private String signrecordServiceid;

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
     * 获取用户签约详情id
     *
     * @return signrecord_id - 用户签约详情id
     */
    public String getSignrecordId() {
        return signrecordId;
    }

    /**
     * 设置用户签约详情id
     *
     * @param signrecordId 用户签约详情id
     */
    public void setSignrecordId(String signrecordId) {
        this.signrecordId = signrecordId;
    }

    /**
     * 获取1 注册;2 认证;3 发起签约;4 签署认证;5 合同签署;6 合同保全;7 上传公证处;8 司法鉴定中心;9 对接在线仲裁单位
     *
     * @return signrecord_type - 1 注册;2 认证;3 发起签约;4 签署认证;5 合同签署;6 合同保全;7 上传公证处;8 司法鉴定中心;9 对接在线仲裁单位
     */
    public Integer getSignrecordType() {
        return signrecordType;
    }

    /**
     * 设置1 注册;2 认证;3 发起签约;4 签署认证;5 合同签署;6 合同保全;7 上传公证处;8 司法鉴定中心;9 对接在线仲裁单位
     *
     * @param signrecordType 1 注册;2 认证;3 发起签约;4 签署认证;5 合同签署;6 合同保全;7 上传公证处;8 司法鉴定中心;9 对接在线仲裁单位
     */
    public void setSignrecordType(Integer signrecordType) {
        this.signrecordType = signrecordType;
    }

    /**
     * 获取详细内容,1某某注册了账号*****;2某某通过什么认证方式，在易通签平台进行了身份认证，认证信息为：;3某某发起一份合同签约，签约序列号为，合同名称为，（合同的原文件hash）;4谁通过什么方式进行了身份认证，对应的认证流水id为*。
     *
     * @return signrecord_recordinfo - 详细内容,1某某注册了账号*****;2某某通过什么认证方式，在易通签平台进行了身份认证，认证信息为：;3某某发起一份合同签约，签约序列号为，合同名称为，（合同的原文件hash）;4谁通过什么方式进行了身份认证，对应的认证流水id为*。
     */
    public String getSignrecordRecordinfo() {
        return signrecordRecordinfo;
    }

    /**
     * 设置详细内容,1某某注册了账号*****;2某某通过什么认证方式，在易通签平台进行了身份认证，认证信息为：;3某某发起一份合同签约，签约序列号为，合同名称为，（合同的原文件hash）;4谁通过什么方式进行了身份认证，对应的认证流水id为*。
     *
     * @param signrecordRecordinfo 详细内容,1某某注册了账号*****;2某某通过什么认证方式，在易通签平台进行了身份认证，认证信息为：;3某某发起一份合同签约，签约序列号为，合同名称为，（合同的原文件hash）;4谁通过什么方式进行了身份认证，对应的认证流水id为*。
     */
    public void setSignrecordRecordinfo(String signrecordRecordinfo) {
        this.signrecordRecordinfo = signrecordRecordinfo;
    }

    /**
     * 获取本条操作的hash,
     *
     * @return signrecord_hash - 本条操作的hash,
     */
    public String getSignrecordHash() {
        return signrecordHash;
    }

    /**
     * 设置本条操作的hash,
     *
     * @param signrecordHash 本条操作的hash,
     */
    public void setSignrecordHash(String signrecordHash) {
        this.signrecordHash = signrecordHash;
    }

    /**
     * 获取操作时间,
     *
     * @return signrecord_time - 操作时间,
     */
    public Date getSignrecordTime() {
        return signrecordTime;
    }

    /**
     * 设置操作时间,
     *
     * @param signrecordTime 操作时间,
     */
    public void setSignrecordTime(Date signrecordTime) {
        this.signrecordTime = signrecordTime;
    }

    /**
     * 获取操作的ip,
     *
     * @return signrecord_ip - 操作的ip,
     */
    public String getSignrecordIp() {
        return signrecordIp;
    }

    /**
     * 设置操作的ip,
     *
     * @param signrecordIp 操作的ip,
     */
    public void setSignrecordIp(String signrecordIp) {
        this.signrecordIp = signrecordIp;
    }

    /**
     * 获取对应签约流水id,
     *
     * @return signrecord_signrequestid - 对应签约流水id,
     */
    public String getSignrecordSignrequestid() {
        return signrecordSignrequestid;
    }

    /**
     * 设置对应签约流水id,
     *
     * @param signrecordSignrequestid 对应签约流水id,
     */
    public void setSignrecordSignrequestid(String signrecordSignrequestid) {
        this.signrecordSignrequestid = signrecordSignrequestid;
    }

    /**
     * 获取对应的操作返回的流水id,
     *
     * @return signrecord_serviceid - 对应的操作返回的流水id,
     */
    public String getSignrecordServiceid() {
        return signrecordServiceid;
    }

    /**
     * 设置对应的操作返回的流水id,
     *
     * @param signrecordServiceid 对应的操作返回的流水id,
     */
    public void setSignrecordServiceid(String signrecordServiceid) {
        this.signrecordServiceid = signrecordServiceid;
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