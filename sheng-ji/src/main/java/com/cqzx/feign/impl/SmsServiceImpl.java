package com.cqzx.feign.impl;

import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.feign.SmsService;
import org.springframework.stereotype.Component;

/**
 * @Description: 调用服务异常处理类
 * @Author: cqyc
 * @Date: 2019-8-02
 */
@Component
public class SmsServiceImpl implements SmsService {
    @Override
    public String loginWithPhone(String loginname, Integer loginType) {
        throw new ZxException(ExceptionEnums.SMS_SERVICE_ERROR);
    }
}
