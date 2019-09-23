package com.ezcsign.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "scenefile")
public class Scenefile {
    /**
     * 签约现场存证id,
     */
    @Id
    @Column(name = "scenefile_id")
    @GeneratedValue(generator = "JDBC")
    private String scenefileId;

    /**
     * 对应的签约人id,
     */
    @Column(name = "scenefile_signerinfoid")
    private String scenefileSignerinfoid;

    /**
     * 文件类型,1.图片;2.视频文件
     */
    @Column(name = "scenefile_filetype")
    private Integer scenefileFiletype;

    /**
     * 文件名称,
     */
    @Column(name = "scenefile_name")
    private String scenefileName;

    /**
     * 文件存储id,
     */
    @Column(name = "scenefile_fileid")
    private String scenefileFileid;

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
     * 获取签约现场存证id,
     *
     * @return scenefile_id - 签约现场存证id,
     */
    public String getScenefileId() {
        return scenefileId;
    }

    /**
     * 设置签约现场存证id,
     *
     * @param scenefileId 签约现场存证id,
     */
    public void setScenefileId(String scenefileId) {
        this.scenefileId = scenefileId;
    }

    /**
     * 获取对应的签约人id,
     *
     * @return scenefile_signerinfoid - 对应的签约人id,
     */
    public String getScenefileSignerinfoid() {
        return scenefileSignerinfoid;
    }

    /**
     * 设置对应的签约人id,
     *
     * @param scenefileSignerinfoid 对应的签约人id,
     */
    public void setScenefileSignerinfoid(String scenefileSignerinfoid) {
        this.scenefileSignerinfoid = scenefileSignerinfoid;
    }

    /**
     * 获取文件类型,1.图片;2.视频文件
     *
     * @return scenefile_filetype - 文件类型,1.图片;2.视频文件
     */
    public Integer getScenefileFiletype() {
        return scenefileFiletype;
    }

    /**
     * 设置文件类型,1.图片;2.视频文件
     *
     * @param scenefileFiletype 文件类型,1.图片;2.视频文件
     */
    public void setScenefileFiletype(Integer scenefileFiletype) {
        this.scenefileFiletype = scenefileFiletype;
    }

    /**
     * 获取文件名称,
     *
     * @return scenefile_name - 文件名称,
     */
    public String getScenefileName() {
        return scenefileName;
    }

    /**
     * 设置文件名称,
     *
     * @param scenefileName 文件名称,
     */
    public void setScenefileName(String scenefileName) {
        this.scenefileName = scenefileName;
    }

    /**
     * 获取文件存储id,
     *
     * @return scenefile_fileid - 文件存储id,
     */
    public String getScenefileFileid() {
        return scenefileFileid;
    }

    /**
     * 设置文件存储id,
     *
     * @param scenefileFileid 文件存储id,
     */
    public void setScenefileFileid(String scenefileFileid) {
        this.scenefileFileid = scenefileFileid;
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