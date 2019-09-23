package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sealinfo")
public class Sealinfo {
    /**
     * 印章信息ID
     */
    @Id
    @Column(name = "sealinfo_id")
    @GeneratedValue(generator = "JDBC")
    private String sealinfoId;

    /**
     * 印章类型:1=个人，2=企业
     */
    @Column(name = "sealinfo_type")
    private Integer sealinfoType;

    /**
     * 使用方ID:印章类型=1时为person_info_id,否则为company_info_id
     */
    @Column(name = "sealinfo_userid")
    private String sealinfoUserid;

    /**
     * 印章名称
     */
    @Column(name = "sealinfo_name")
    private String sealinfoName;

    /**
     * 印章编码
     */
    @Column(name = "sealinfo_code")
    private Integer sealinfoCode;

    /**
     * 印章图片文件格式
     */
    @Column(name = "sealinfo_filetype")
    private String sealinfoFiletype;

    /**
     * 印章图片高
     */
    @Column(name = "sealinfo_width")
    private Integer sealinfoWidth;

    /**
     * 印章图片宽
     */
    @Column(name = "sealinfo_height")
    private Integer sealinfoHeight;

    /**
     * 印章图片存储ID
     */
    @Column(name = "sealinfo_imageid")
    private String sealinfoImageid;

    /**
     * 印章对应证书信息ID
     */
    @Column(name = "sealinfo_certid")
    private String sealinfoCertid;

    /**
     * 印章创建ID  对应personinfo或者memberinfo的id
     */
    @Column(name = "sealinfo_createrid")
    private String sealinfoCreaterid;

    /**
     * 1 对应personinfo  2 对应memberinfo
     */
    @Column(name = "sealinfo_creatertype")
    private Integer sealinfoCreatertype;

    /**
     * 印章创建人名称
     */
    @Column(name = "sealinfo_creatername")
    private String sealinfoCreatername;

    /**
     * 印章创建时间
     */
    @Column(name = "sealinfo_createtime")
    private Date sealinfoCreatetime;

    /**
     * 印章状态:0=无效;1=有效;2=未审核
     */
    @Column(name = "sealinfo_state")
    private Integer sealinfoState;

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
     * 获取印章信息ID
     *
     * @return sealinfo_id - 印章信息ID
     */
    public String getSealinfoId() {
        return sealinfoId;
    }

    /**
     * 设置印章信息ID
     *
     * @param sealinfoId 印章信息ID
     */
    public void setSealinfoId(String sealinfoId) {
        this.sealinfoId = sealinfoId;
    }

    /**
     * 获取印章类型:1=个人，2=企业
     *
     * @return sealinfo_type - 印章类型:1=个人，2=企业
     */
    public Integer getSealinfoType() {
        return sealinfoType;
    }

    /**
     * 设置印章类型:1=个人，2=企业
     *
     * @param sealinfoType 印章类型:1=个人，2=企业
     */
    public void setSealinfoType(Integer sealinfoType) {
        this.sealinfoType = sealinfoType;
    }

    /**
     * 获取使用方ID:印章类型=1时为person_info_id,否则为company_info_id
     *
     * @return sealinfo_userid - 使用方ID:印章类型=1时为person_info_id,否则为company_info_id
     */
    public String getSealinfoUserid() {
        return sealinfoUserid;
    }

    /**
     * 设置使用方ID:印章类型=1时为person_info_id,否则为company_info_id
     *
     * @param sealinfoUserid 使用方ID:印章类型=1时为person_info_id,否则为company_info_id
     */
    public void setSealinfoUserid(String sealinfoUserid) {
        this.sealinfoUserid = sealinfoUserid;
    }

    /**
     * 获取印章名称
     *
     * @return sealinfo_name - 印章名称
     */
    public String getSealinfoName() {
        return sealinfoName;
    }

    /**
     * 设置印章名称
     *
     * @param sealinfoName 印章名称
     */
    public void setSealinfoName(String sealinfoName) {
        this.sealinfoName = sealinfoName;
    }

    /**
     * 获取印章编码
     *
     * @return sealinfo_code - 印章编码
     */
    public Integer getSealinfoCode() {
        return sealinfoCode;
    }

    /**
     * 设置印章编码
     *
     * @param sealinfoCode 印章编码
     */
    public void setSealinfoCode(Integer sealinfoCode) {
        this.sealinfoCode = sealinfoCode;
    }

    /**
     * 获取印章图片文件格式
     *
     * @return sealinfo_filetype - 印章图片文件格式
     */
    public String getSealinfoFiletype() {
        return sealinfoFiletype;
    }

    /**
     * 设置印章图片文件格式
     *
     * @param sealinfoFiletype 印章图片文件格式
     */
    public void setSealinfoFiletype(String sealinfoFiletype) {
        this.sealinfoFiletype = sealinfoFiletype;
    }

    /**
     * 获取印章图片高
     *
     * @return sealinfo_width - 印章图片高
     */
    public Integer getSealinfoWidth() {
        return sealinfoWidth;
    }

    /**
     * 设置印章图片高
     *
     * @param sealinfoWidth 印章图片高
     */
    public void setSealinfoWidth(Integer sealinfoWidth) {
        this.sealinfoWidth = sealinfoWidth;
    }

    /**
     * 获取印章图片宽
     *
     * @return sealinfo_height - 印章图片宽
     */
    public Integer getSealinfoHeight() {
        return sealinfoHeight;
    }

    /**
     * 设置印章图片宽
     *
     * @param sealinfoHeight 印章图片宽
     */
    public void setSealinfoHeight(Integer sealinfoHeight) {
        this.sealinfoHeight = sealinfoHeight;
    }

    /**
     * 获取印章图片存储ID
     *
     * @return sealinfo_imageid - 印章图片存储ID
     */
    public String getSealinfoImageid() {
        return sealinfoImageid;
    }

    /**
     * 设置印章图片存储ID
     *
     * @param sealinfoImageid 印章图片存储ID
     */
    public void setSealinfoImageid(String sealinfoImageid) {
        this.sealinfoImageid = sealinfoImageid;
    }

    /**
     * 获取印章对应证书信息ID
     *
     * @return sealinfo_certid - 印章对应证书信息ID
     */
    public String getSealinfoCertid() {
        return sealinfoCertid;
    }

    /**
     * 设置印章对应证书信息ID
     *
     * @param sealinfoCertid 印章对应证书信息ID
     */
    public void setSealinfoCertid(String sealinfoCertid) {
        this.sealinfoCertid = sealinfoCertid;
    }

    /**
     * 获取印章创建ID  对应personinfo或者memberinfo的id
     *
     * @return sealinfo_createrid - 印章创建ID  对应personinfo或者memberinfo的id
     */
    public String getSealinfoCreaterid() {
        return sealinfoCreaterid;
    }

    /**
     * 设置印章创建ID  对应personinfo或者memberinfo的id
     *
     * @param sealinfoCreaterid 印章创建ID  对应personinfo或者memberinfo的id
     */
    public void setSealinfoCreaterid(String sealinfoCreaterid) {
        this.sealinfoCreaterid = sealinfoCreaterid;
    }

    /**
     * 获取1 对应personinfo  2 对应memberinfo
     *
     * @return sealinfo_creatertype - 1 对应personinfo  2 对应memberinfo
     */
    public Integer getSealinfoCreatertype() {
        return sealinfoCreatertype;
    }

    /**
     * 设置1 对应personinfo  2 对应memberinfo
     *
     * @param sealinfoCreatertype 1 对应personinfo  2 对应memberinfo
     */
    public void setSealinfoCreatertype(Integer sealinfoCreatertype) {
        this.sealinfoCreatertype = sealinfoCreatertype;
    }

    /**
     * 获取印章创建人名称
     *
     * @return sealinfo_creatername - 印章创建人名称
     */
    public String getSealinfoCreatername() {
        return sealinfoCreatername;
    }

    /**
     * 设置印章创建人名称
     *
     * @param sealinfoCreatername 印章创建人名称
     */
    public void setSealinfoCreatername(String sealinfoCreatername) {
        this.sealinfoCreatername = sealinfoCreatername;
    }

    /**
     * 获取印章创建时间
     *
     * @return sealinfo_createtime - 印章创建时间
     */
    public Date getSealinfoCreatetime() {
        return sealinfoCreatetime;
    }

    /**
     * 设置印章创建时间
     *
     * @param sealinfoCreatetime 印章创建时间
     */
    public void setSealinfoCreatetime(Date sealinfoCreatetime) {
        this.sealinfoCreatetime = sealinfoCreatetime;
    }

    /**
     * 获取印章状态:0=无效;1=有效;2=未审核
     *
     * @return sealinfo_state - 印章状态:0=无效;1=有效;2=未审核
     */
    public Integer getSealinfoState() {
        return sealinfoState;
    }

    /**
     * 设置印章状态:0=无效;1=有效;2=未审核
     *
     * @param sealinfoState 印章状态:0=无效;1=有效;2=未审核
     */
    public void setSealinfoState(Integer sealinfoState) {
        this.sealinfoState = sealinfoState;
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
}