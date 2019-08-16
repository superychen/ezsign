package com.cqzx.feign;

import com.cqzx.domain.CodeEntity;
import com.cqzx.feign.impl.CertSealServiceImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "AUTH-CRET",fallback = CertSealServiceImpl.class,path = "/create")
public interface CertSealService {

    /**
     * 提供签约的接口，生成证书
     * @param userid 根据用户的类型来判断传入的是personinfoId还是companyinfoId
     * @param userType 用户的类型 1:个人签约类型，2：企业签约类型
     * @return 返回状态码
     */
    @PostMapping("/cert")
    CodeEntity generateCertificates(@RequestParam("userid") String  userid,
                                    @RequestParam("userType") String userType);

    /**
     * 提供生成印章的接口（个人/企业）
     * @param userid 传入的personinfoid或者companyinfoid
     * @param userType 表示用户的类型 1：个人印章类型，2：企业印章类型
     * @return 返回状态码
     */
    @PostMapping("/seal")
    CodeEntity generateSeal(@RequestParam("userid") String userid,
                            @RequestParam("userType") String userType);

}
