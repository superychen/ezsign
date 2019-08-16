package com.cqzx.feign;

import com.cqzx.feign.impl.GetServiceImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "CQZX-LOGIN",fallback = GetServiceImpl.class,path = "/user")
public interface GetService {
    @GetMapping("/code")
    String loginWithPhone(@RequestParam("loginname") String loginname,
                          @RequestParam("loginType")Integer loginType);
}
