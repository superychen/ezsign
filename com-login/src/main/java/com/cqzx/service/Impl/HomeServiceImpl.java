package com.cqzx.service.Impl;

import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.domain.Customer;
import com.cqzx.feign.GetService;
import com.cqzx.service.HomeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 进入首页业务层
 * @Author: cqyc
 * @Date: 2019-9-20
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HttpSession session;

    @Autowired
    private GetService getService;
    @Override
    public Map<String,Object> homePersonOrCompany(Customer customer) {
        Map<String, Object> map = new HashMap<>();
        //企业签约
        map.put("loginName",customer.getCustomerLoginname());
        map.put("userType",customer.getUserType());
        String userId = (String) session.getAttribute("userId");
        if(customer.getUserType() == 2){
            Map<String, Object> memGet = getService.get("memberinfo", "memberinfo_id", userId);
            Map<String,Object> memberMap = (Map<String, Object>) memGet.get("data");
            map.put("name",memberMap.get("memberinfo_name"));
        }else if(customer.getUserType() == 1){
        //个人签约
            if(StringUtils.isBlank(customer.getCustomerPersonid())){
                throw new ZxException(ExceptionEnums.NOT_FOUND_PERSON_INFO);
            }
            Map<String, Object> perGet = getService.get("personinfo","personinfo_id",customer.getCustomerPersonid());
            Map<String,Object> data = (Map<String, Object>) perGet.get("data");
            map.put("name",data.get("personinfo_name"));
        }else {
            throw new ZxException(ExceptionEnums.SESSION_USER_ERROR);
        }
        return map;
    }
}
