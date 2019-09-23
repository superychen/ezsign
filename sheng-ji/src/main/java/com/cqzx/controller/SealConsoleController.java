package com.cqzx.controller;

import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.domain.CodeEntity;
import com.cqzx.domain.Customer;
import com.cqzx.service.SealConsoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 印章管理控制台
 * @Author:
 * @Date:
 */
@RestController
@RequestMapping("/sealCon")
@Slf4j
public class SealConsoleController {

    @Autowired
    private SealConsoleService sealConsoleService;

    /**
     * 查看印章管理的所属企业的印章
     */
    @GetMapping("/seals")
    public List<Map<String,Object>> membersSeal(HttpSession session){
        //取出当前的进入的memberinfoId
        Map<String,Object> adminMan = (Map<String, Object>) session.getAttribute("adminMan");
        if (adminMan == null) {
            throw new ZxException(ExceptionEnums.LOGIN_ERROR);
        }
        log.debug("从session中获取出来memberinfo_id-->{}",adminMan.get("memberinfo_id"));
        return sealConsoleService.membersSeal(String.valueOf(adminMan.get("memberinfo_id")));
    }

    /**
     * 修改对应的印章信息
     * @param sealId 印章的Id
     * @param sealName 印章的名称
     * @return codeEntity
     */
    @PostMapping("/update/{sealId}")
    public CodeEntity updateSeal(@PathVariable("sealId") String sealId,
                             @RequestParam("sealName") String sealName){
        sealConsoleService.updateSeal(sealId,sealName);
        return new CodeEntity(200,"ok");
    }

    /**
     * 根据印章Id来删除对应的删除
     * @param sealId 印章ID
     * @return codeEntity
     */
    @PostMapping("/delSeal")
    public CodeEntity deleteSeal(@RequestParam("sealId") String sealId){
        sealConsoleService.deleteSeal(sealId);
        return new CodeEntity(200,"ok");
    }

    /**
     * 新增印章
     * @param sealName 印章名称
     * @param sealFont 印章文字
     * @param sealNum 印章编号
     * @return
     */
    @PostMapping("/addSeal")
    public CodeEntity addSeal(@RequestParam("sealName") String sealName,
                              @RequestParam("sealFont") String sealFont,
                              @RequestParam("sealNum") String sealNum,HttpSession session){
        //取出当前的进入的memberinfoId
        Map<String,Object> adminMan = (Map<String, Object>) session.getAttribute("adminMan");
        if (adminMan == null) {
            throw new ZxException(ExceptionEnums.LOGIN_ERROR);
        }
        sealConsoleService.addSeal(sealName,sealFont,sealNum,String.valueOf(adminMan.get("memberinfo_id")));
        return new CodeEntity(200,"ok");
    }

    /**
     * 通过上传印章来创建印章
     * @param sealName 印章名称
     * @param sealFile 上传的印章的文件路径
     */
    @PostMapping("/uploadSeal")
    public CodeEntity uploadSeal(@RequestParam("sealName") String sealName,
                                 @RequestParam("sealFile") String sealFile,
                                 HttpSession session){
        //取出当前的进入的memberinfoId
        Map<String,Object> adminMan = (Map<String, Object>) session.getAttribute("adminMan");
        if (adminMan == null) {
            throw new ZxException(ExceptionEnums.LOGIN_ERROR);
        }
        sealConsoleService.uploadSeal(sealName,sealFile,String.valueOf(adminMan.get("memberinfo_id")));
        return new CodeEntity(200,"ok");
    }


    /**
     * 对相应的印章进行授权
     * @param sealId 印章的ID
     * @param memberId 对该印章授权对应的memberId
     * @param startTime 授权的开始时间
     * @param endTime 授权的结束时间
     * @return codeEntity
     */
    @PostMapping("memSeal")
    public CodeEntity memberSeal(@RequestParam("sealId") String sealId,
                                 @RequestParam("memberId") String memberId,
                                 @RequestParam("startTime")String startTime,
                                 @RequestParam("endTime") String endTime){
        startTime = startTime.replace("Z"," UTC");
        endTime = endTime.replace("Z"," UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        try {
            Date start = format.parse(startTime);
            Date end = format.parse(endTime);
            log.debug("startTime--->{},endTime--->{}",start,end);
            sealConsoleService.memberSeal(sealId,memberId,start,end);
            return new CodeEntity(200,"ok");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ZxException(ExceptionEnums.DATE_CONVERT_ERROR);
        }
    }

    /**
     * 分页查询所有的印章授权列表
     * @param sealName 印章名称
     * @param authorizer 授权人
     * @param startTime 开始时间
     * @param endTime 结束时间
     */
    @GetMapping("/sealGrants")
    public List<Map<String,Object>> sealGrants(@RequestParam("page") Integer page,
                                               @RequestParam(value = "sealName",required = false) String sealName,
                                               @RequestParam(value = "authorizer",required = false) String authorizer,
                                               @RequestParam(value = "startTime",required = false) String startTime,
                                               @RequestParam(value = "endTime",required = false) String endTime,
                                               HttpSession session){
        try {
            Map<String,Object> adminMan = (Map<String, Object>) session.getAttribute("adminMan");
            if (adminMan.size() == 0) {
                throw new RuntimeException("您登陆的账号可能没有访问权限");
            }
            String memberinfoId = (String) adminMan.get("memberinfo_id");
            return sealConsoleService.sealGrants(page,sealName,authorizer,startTime,endTime,memberinfoId);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ZxException(ExceptionEnums.DATE_CONVERT_ERROR);
        }
    }

    @PostMapping("/updateGrant")
    public CodeEntity updateGrant(@RequestParam("startTime")String startTime,
                                  @RequestParam("endTime") String endTime,
                                  @RequestParam("sealGrantId") String sealGrantId){
        startTime = startTime.replace("Z"," UTC");
        endTime = endTime.replace("Z"," UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = format.parse(startTime);
            Date end = format.parse(endTime);
            String startDate = sdf.format(start);
            String endDate = sdf.format(end);
            sealConsoleService.updateGrant(sealGrantId,startDate,endDate);
            return new CodeEntity(200,"ok");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ZxException(ExceptionEnums.DATE_CONVERT_ERROR);
        }
    }

    /**
     * 删除授权表对应的数据
     * @param grantId
     * @return
     */
    @PostMapping("/delGrant")
    public CodeEntity deleteGrant(@RequestParam("grantId") String grantId){
        sealConsoleService.deleteGrant(grantId);
        return new CodeEntity(200,"ok");
    }

    /**
     * 查询授权表中的总数
     */
    @PostMapping("/count")
    public Integer countGrant(){
        return sealConsoleService.countGrant();
    }

    /**
     * 我的印章之企业印章,查询当前的企业授权表中的对应账号的企业印章
     */
    @GetMapping("/cusMem/seal")
    public List<Map<String,Object>> cusMemberSeal(HttpSession session){
        String userId = (String) session.getAttribute("userId");
        return sealConsoleService.cusMemberSeal(userId);
    }

    /**
     *  我的印章之个人印章,查询当前的印章信息表中有几个个人印章
     */
    @GetMapping("/cusPer/seal")
    public List<Map<String,Object>> cusPersonSeal(HttpSession session){
        String userId = (String) session.getAttribute("userId");
        return sealConsoleService.cusPersonSeal(userId);
    }

    @GetMapping("/loginType")
    public Map<String,Object> isAuthType(HttpSession session){
        Customer customer = objectCustomer(session);
        Map<String, Object> map = new HashMap<>();
        //1 表示进入个人签约，2 表示进入企业签约
        map.put("loginType",customer.getUserType());
        return map;
    }

    /**
     * 创建个人印章
     * @param addSealFont 印章姓名
     * @param addSealNum 编号
     */
    @PostMapping("/person/addseal")
    public CodeEntity savePersonSeal(@RequestParam("addSealFont") String addSealFont,
                                     @RequestParam("addSealNum") Integer addSealNum,
                                     HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            throw new ZxException(ExceptionEnums.LOGIN_ERROR);
        }
        sealConsoleService.savePersonSeal(addSealFont,addSealNum,userId);
        return new CodeEntity(200,"ok");
    }


    protected Customer objectCustomer(HttpSession session){
        Map<String,Object> customerMap = (Map<String, Object>) session.getAttribute("customer");
        if(customerMap.size() <= 0){
            throw new ZxException(ExceptionEnums.LOGIN_ERROR);
        }
        String jsonObject = new JSONObject(customerMap).toJSONString();
        Customer customer = JSONObject.parseObject(jsonObject, Customer.class);
        return customer;
    }
}
