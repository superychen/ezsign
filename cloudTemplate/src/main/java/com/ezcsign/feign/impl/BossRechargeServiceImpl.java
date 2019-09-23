package com.ezcsign.feign.impl;

import com.ezcsign.comm.exception.ExceptionEnums;
import com.ezcsign.comm.exception.ZxException;
import com.ezcsign.feign.BossRechargeService;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Component
public class BossRechargeServiceImpl implements BossRechargeService {

    @Override
    public Map<String, Object> saveCallConfRechargeByPackageInfo(Map<String, Object> saveParams) {
        throw new RuntimeException("套餐信息调整服务配置异常");
    }

    @Override
    public Map<String, Object> getPackagesListByType(Map<String, Object> packageInfo) {
        throw new ZxException(ExceptionEnums.EXECUTE_SQL_SELECT_ERROR);
    }

    @Override
    public Map<String, Object> callConfListByMemberIdShow(String projectId, Integer customerType, String memberId) {
        throw new ZxException(ExceptionEnums.EXECUTE_SQL_SELECT_ERROR);
    }

    @Override
    public Map<String, Object> callConfListByMemberId(String projectId, Integer customerType, String memberId) {
        throw new ZxException(ExceptionEnums.EXECUTE_SQL_SELECT_ERROR);
    }

    @Override
    public Map<String, Object> saveCustomPackageInfo(Map<String, Object> saveParams) {
        throw new RuntimeException("套餐信息调整服务配置异常");
    }
}
