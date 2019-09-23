package com.cqzx.service;

import com.cqzx.domain.Companyinfo;

import java.util.Map;

public interface CompanyAuthenService {
    Map<String,String> remit(String companyinfoCreditcode);

    String companyUploadFile(byte[] bytes,String filename);

    Boolean companyRegist(Companyinfo companyinfo);

    Boolean companyRemitAuth(String companyinfoAmount, String companyinfoVerifycode,String companyinfoCreditcode);

    void afterCompanyAuth(Companyinfo companyinfo);
}
