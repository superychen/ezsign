package com.cqzx.feign.impl;

import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.feign.GetService;
import org.springframework.stereotype.Component;

/**
 * @Description: feign调用失败回滚执行类
 * @Author: cqyc
 * @Date: 2019-7-29
 */
@Component
public class GetServiceImpl implements GetService {
    @Override
    public String loginWithPhone(String loginname, Integer loginType) {
        throw new ZxException(ExceptionEnums.SEND_CODE_ERROR);
    }
}
