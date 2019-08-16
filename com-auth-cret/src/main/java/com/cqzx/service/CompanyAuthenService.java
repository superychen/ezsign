package com.cqzx.service;

import com.cqzx.domain.Companyinfo;

import java.util.Map;

public interface CompanyAuthenService {
    Map<String,String> remit(String companyinfoName);

    String companyUploadFile(byte[] bytes,String filename);

    Boolean companyRegist(Companyinfo companyinfo);

    Boolean companyRemitAuth(String companyinfoAmount, String companyinfoVerifycode,String companyinfoName);

    void afterCompanyAuth(Companyinfo companyinfo);
}
