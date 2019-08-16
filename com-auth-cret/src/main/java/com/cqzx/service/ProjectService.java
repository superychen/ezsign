package com.cqzx.service;

import com.cqzx.domain.Companyinfo;

/**
 * 项目业务逻辑接口
 */
public interface ProjectService {

    //默认创建企业项目
    Boolean createCompanyProject(Companyinfo companyinfo);

}
