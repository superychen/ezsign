package com.ezcsign.feign;

import com.ezcsign.feign.impl.BossRechargeServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "EZCABOSS-SERVICE-CONF",fallback = BossRechargeServiceImpl.class)
public interface BossRechargeService {

    /**
     *  customer_type:客户类型  project_id:项目Id不能为空
     *  member_id: 会员Id不能为空   package_id  不能为空
     * @param saveParams
     * @return
     */
    @RequestMapping("/callconf/saveCallConfRechargeByPackageInfo")
    Map<String, Object> saveCallConfRechargeByPackageInfo(@RequestBody Map<String, Object> saveParams);


    /**
     * 通过套餐类型(0=个人;1=企业;2=API)获取套餐列表
     */
    @RequestMapping(value = "/package/getPackagesListByType", method = RequestMethod.POST)
    Map<String, Object> getPackagesListByType(@RequestBody Map<String, Object> packageInfo);

    /**
     *
     * @param projectId 项目信息ID(当前客户类型不为个人时，项目信息ID不能为空)
     * @param customerType 客户类型:0=个人;1=企业;2=API
     * @param memberId 会员ID。客户类型=0、1时为关联结点ID,否则为个人信息ID
     * @return
     */
    @PostMapping(value = "/callconf/getCallConfListByMemberIdShow")
    Map<String,Object> callConfListByMemberIdShow(@RequestParam("projectId") String projectId,
                                                 @RequestParam("customerType") Integer customerType,
                                                 @RequestParam("memberId") String memberId);



    @PostMapping(value = "/callconf/getCallConfListByMemberId")
    Map<String,Object> callConfListByMemberId(@RequestParam("projectId") String projectId,
                                              @RequestParam("customerType") Integer customerType,
                                              @RequestParam("memberId") String memberId);


    @PostMapping(value = "/callconf/saveCustomPackageInfo")
    Map<String, Object> saveCustomPackageInfo(@RequestBody Map<String, Object> saveParams);
}
