package com.cqzx.service;

import com.cqzx.domain.Companyinfo;
import com.cqzx.domain.Perosoninfo;

/**
 * 印章业务接口
 */
public interface SealService {

    Boolean createPersonSeal(Perosoninfo person);

    Boolean createCompanySeal(Companyinfo companyinfo);
}
