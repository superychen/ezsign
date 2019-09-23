package com.ezcsign.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ezcsign.comm.JavaBeanUtil;
import com.ezcsign.comm.MD5Hash;
import com.ezcsign.comm.exception.ExceptionEnums;
import com.ezcsign.comm.exception.ZxException;
import com.ezcsign.entity.Customer;
import com.ezcsign.entity.Keyapply;
import com.ezcsign.feign.CommService;
import com.ezcsign.service.KeyApplyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @Description: 钥匙盘申请业务层
 * @Author: cqyc
 * @Date: 2019-9-10
 */
@Service
@Slf4j
public class KeyApplyServiceImpl implements KeyApplyService {

    @Autowired
    private HttpSession session;

    @Autowired
    private CommService commService;

    /**
     * 钥匙盘申请
     */
    @Override
    public void applyKey(String name, String telephone, String address, Customer customer) {
        //1为个人，2为企业
        Keyapply keyapply = new Keyapply();
        String userId = (String) session.getAttribute("userId");
        keyapply.setKeyapplyId(MD5Hash.UUIDCreate());//唯一账号
        keyapply.setKeyapplyMemberid(userId);
        keyapply.setKeyapplyApplytime(new Date());
        keyapply.setKeyapplyAddress(address);
        keyapply.setKeyapplyTelephone(telephone);
        keyapply.setKeyapplyContactor(name);
        keyapply.setKeyapplyStatus(1);
        if(customer.getUserType() == 2){
            //当前的账户类型为2
            keyapply.setKeyapplyMembertype(2);
        }else if(customer.getUserType() == 1){
            ///当前账户类型为1，个人类型
            keyapply.setKeyapplyMembertype(1);
        }else {
            throw new ZxException(ExceptionEnums.SESSION_HAS_ERROR);
        }
        Map<String, Object> map = JavaBeanUtil.convertBeanToMap(keyapply);
        String save = commService.save("keyapply", "keyapply_id", map);
        log.debug("保存结果--> {}",save);
        JSONObject perSaveRes = JSONObject.parseObject(save);
        String perMsg = (String) perSaveRes.get("data");
        if(StringUtils.equals(perMsg,"could not execute statement")){
            throw new ZxException(ExceptionEnums.APPLY_PAN_INSERT_ERROR);
        }
    }
}
