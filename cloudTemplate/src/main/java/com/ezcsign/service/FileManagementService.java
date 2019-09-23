package com.ezcsign.service;

/**
 * 档案管理
 * @author tzw
 * @date 2019/07/29
 */

public interface FileManagementService {
    /**
     * 设置归档配置
     * @param companyinfoFilejurisdiction1 允许手动归档
     * @param companyinfoFilejurisdiction2 允许上传文件归档
     * @return
     */
    String modifyArchive(Integer companyinfoFilejurisdiction1,Integer companyinfoFilejurisdiction2,String customerId);



}
