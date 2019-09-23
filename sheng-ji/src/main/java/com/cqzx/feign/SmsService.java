package com.cqzx.feign;

import com.cqzx.feign.impl.SmsServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@FeignClient(value = "EZCASIGN-CQZX-LOGIN",fallback = SmsServiceImpl.class,path = "/user")
public interface SmsService {

    /**
     *
     * @param loginname 手机号码
     * @param loginType 0表示手机登陆，1表示邮箱登录
     * @return
     */
    @GetMapping("/code")
    String loginWithPhone(@RequestParam("loginname") String loginname,
                          @RequestParam("loginType") Integer loginType);
}
