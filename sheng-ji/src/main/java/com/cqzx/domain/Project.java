package com.cqzx.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 项目实体类
 * @Author: cqyc
 * @Date: 2019-8-9
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project implements Serializable {
    private String projectId;
    private String projectMemberid;
    private Integer projectMembertype;
    private String projectCompanyid;
    private String projectName;
    private String projectMealid;
    private String projectMealname;
    private Date createDate;
    private String createId;
    private String createName;
    private String updateId;
    private String updateName;
    private Date updateDatetime;
    private Integer status;
}
