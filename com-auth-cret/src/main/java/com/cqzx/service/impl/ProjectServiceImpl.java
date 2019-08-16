package com.cqzx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.util.JavaBeanUtil;
import com.cqzx.comm.util.MD5Hash;
import com.cqzx.domain.Companyinfo;
import com.cqzx.domain.Customer;
import com.cqzx.domain.Project;
import com.cqzx.feign.DbService;
import com.cqzx.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description: 项目业务逻辑处理层
 * @Author: cqyc
 * @Date: 2019-8-9
 */
@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {


    private static final Integer PROJECT_MEMBER_TYPE=2;//2 企业,

    @Autowired
    private DbService dbService;

    @Autowired
    private HttpSession session;

    /**
     * 默认创建企业的项目
     * @param companyinfo 企业基本信息
     * @return
     */
    @Override
    public Boolean createCompanyProject(Companyinfo companyinfo) {
        Project project = new Project();
        project.setProjectId(MD5Hash.UUIDCreate());
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            throw new ZxException(ExceptionEnums.SESSION_GET_ERROR);
        }
        project.setProjectMemberid(customer.getCustomerId());
        project.setProjectMembertype(PROJECT_MEMBER_TYPE);//对应企业的超级管理员类型1 个人;2 企业,
        project.setProjectCompanyid(companyinfo.getCompanyinfoId());//对应企业id,
        project.setProjectName(companyinfo.getCompanyinfoName());//项目名称(默认企名称),
        //todo 设置项目套餐 ,这里先随便写，估计先查询memberinfo表对应套餐,得到管理端的套餐接口后在这里进行设置
        project.setProjectMealid("222222");
        project.setProjectMealname("333333");

        Map<String, Object> map = JavaBeanUtil.convertBeanToMap(project);
        for (String s : map.keySet()) {
            log.debug("当前转换为map--->{}",s);
        }
        String save = dbService.save("project", "project_id", map);
        log.debug("保存结果-->{}",save);

        JSONObject saveRes = JSONObject.parseObject(save);
        String msg = (String) saveRes.get("data");
        if(StringUtils.equals(msg,"could not execute statement")){
            throw new ZxException(ExceptionEnums.PROJECT_CREATE_ERROR);
        }
        return true;
    }
}
