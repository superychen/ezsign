package com.cqzx.feign.impl;

import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.domain.CodeEntity;
import com.cqzx.feign.CertSealService;
import org.springframework.stereotype.Component;

/**
 * @Description: feign调用失败回滚
 * @Author: cqyc
 * @Date: 2019-8-13
 */
@Component
public class CertSealServiceImpl implements CertSealService {

    @Override
    public CodeEntity generateCertificates(String userid, String userType) {
        throw new ZxException(ExceptionEnums.EXECUTE_SQL_SELECT_ERROR);
    }

    @Override
    public CodeEntity generateSeal(String userid, String userType) {
        throw new ZxException(ExceptionEnums.EXECUTE_SQL_SELECT_ERROR);
    }
}
