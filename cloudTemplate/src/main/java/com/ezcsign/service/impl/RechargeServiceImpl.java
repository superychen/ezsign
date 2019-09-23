package com.ezcsign.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ezcsign.comm.exception.ExceptionEnums;
import com.ezcsign.comm.exception.ZxException;
import com.ezcsign.entity.Customer;
import com.ezcsign.entity.Memberinfo;
import com.ezcsign.entity.Personinfo;
import com.ezcsign.feign.BossRechargeService;
import com.ezcsign.feign.CommService;
import com.ezcsign.service.RechargeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 充值业务层
 * @Author: cqyc
 * @Date:  2019-9-5
 */
@Service
@Slf4j
public class RechargeServiceImpl implements RechargeService {

    @Autowired
    private HttpSession session;

    @Autowired
    private CommService commService;

    @Autowired
    private BossRechargeService bossRechargeService;
    /**
     * 查询当前是个人签还是企业签对应的充值套餐记录
     * @param customer
     * @return
     */
    @Override
    public Map<String, Object> packageUserOrCompany(Customer customer) {
        log.info("查询套餐得到的用户类型--->{}",customer.getUserType());
        //证明用户进入的类型为企业签约
        Map<String, Object> map = new HashMap<>();
        if(customer.getUserType() == 2){
            //1表示
            map.put("packageType",1);//套餐类型:0=个人;1=企业;2=API
            map.put("getPackageDetailList","y");//是否取明细项
            Map<String, Object> listByType = bossRechargeService.getPackagesListByType(map);
            return listByType;
        }else if(customer.getUserType() == 1){
            //用户进入的是个人签约
            map.put("packageType",0);//套餐类型:0=个人;1=企业;2=API
            map.put("getPackageDetailList","y");//是否取明细项
            Map<String, Object> listByType = bossRechargeService.getPackagesListByType(map);
            return listByType;
        }
        return null;
    }

    /**
     * 根据用户查询对应的服务剩余数量
     * @param customer
     * @return
     */
    @Override
    public Map<String, Object> restServiceUserOrMember(Customer customer,Integer isShowType) {
        //证明用户进入的类型为企业签约
        //对应的当前账号的memberId
        String userId = (String) session.getAttribute("userId");
        if(StringUtils.isBlank(userId)){
            throw new ZxException(ExceptionEnums.SESSION_HAS_ERROR);
        }
        if(customer.getUserType() == 2){
            String sql = "select * from memberinfo where " +
                    " memberinfo_id= '"+userId+"'";
            Memberinfo memberinfo = JSONObject.parseObject(objectRestService(sql), Memberinfo.class);
            log.debug("查看剩余服务份数得到的memberinfo的信息--->{}",memberinfo);
            if(isShowType == 1){
                return bossRechargeService.callConfListByMemberId(memberinfo.getMemberinfoProjectid(),1,userId);
            }else if(isShowType == 2){

                return bossRechargeService.callConfListByMemberIdShow(memberinfo.getMemberinfoProjectid(),1,userId);
            }else{
                throw new ZxException(ExceptionEnums.SELECT_CONF_LIST_ERROR);
            }
        }else if(customer.getUserType() == 1){
            //用户进入的是个人签约
            if(isShowType == 1){
                return bossRechargeService.callConfListByMemberId(null,0,userId);
            }else if(isShowType == 2){

                return bossRechargeService.callConfListByMemberIdShow(null,0,userId);
            }else{
                throw new ZxException(ExceptionEnums.SELECT_CONF_LIST_ERROR);
            }
        }
        return null;
    }

    /**
     * 动态查找所有的用户充值表
     * @param fromTime 充值时间（开始）
     * @param toTime 重置时间（结束）
     * @param isInvoice 是否开票
     * @param customer
     * @return
     */
    @Override
    public List<Map<String, Object>> allRecharges(String fromTime, String toTime, Integer isInvoice, Customer customer) throws ParseException {
        String userId = (String) session.getAttribute("userId");
        String sql = "";
        if(customer.getUserType() == 2){
            sql = "select memberinfo_name,package_name,recharge_moneyamount,recharge_id,recharge_time,recharge_status, " +
                    "  recharge_invoicestatus,invoice_operattime from recharge as r " +
                    "left join memberinfo as m on m.memberinfo_id = r.recharge_operatorid " +
                    "left join package p on r.recharge_mealid = p.package_id " +
                    "left join invoice i on r.recharge_invoiceid = i.invoice_id " +
                    "where recharge_operatorid='"+userId+"'";
        }else if(customer.getUserType() == 1){
            sql ="select personinfo_name,package_name,recharge_moneyamount,recharge_id,recharge_time,recharge_status, " +
                    "  recharge_invoicestatus,invoice_operattime from recharge as r " +
                    "  left join personinfo as e on e.personinfo_id = r.recharge_operatorid " +
                    "  left join package p on r.recharge_mealid = p.package_id " +
                    "  left join invoice i on r.recharge_invoiceid = i.invoice_id " +
                    "where recharge_operatorid='"+userId+"'";
        }

        //如果开始充值时间不为空
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (StringUtils.isNotBlank(fromTime)) {
            fromTime = fromTime.replace("Z"," UTC");
            Date start = format.parse(fromTime);
            String startDate = sdf.format(start);
            sql = sql + " and recharge_time > '"+startDate+"'";
        }
        if(StringUtils.isNotBlank(toTime)){
            toTime = toTime.replace("Z"," UTC");
            Date end = format.parse(toTime);
            String endDate = sdf.format(end);
            sql = sql + " and recharge_time < '"+endDate+"'";
        }
        if(isInvoice != null && isInvoice != 0){
            sql = sql + " and recharge_invoicestatus = '"+isInvoice+"' ";
        }
        log.info("根据前台的查询请求去判断出来的sql--->{}",sql);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> bySQL = commService.findBySQL(sql, map);
        if(StringUtils.equals(String.valueOf(bySQL.get("data")),"[]")){
            //返回一个空
            return new ArrayList<>();
        }
        List<Map<String,Object>> listData = (List<Map<String, Object>>) bySQL.get("data");
        return listData;
    }

    //充值后需要的操作
    @Override
    public Boolean isPayMoneyRight(JSONObject packInfo, Customer customer) {
        Map<String, Object> map = new HashMap<>();
        String userId = (String) session.getAttribute("userId");
        String sql;
        map.put("member_id",userId); //这个是通用的
        //登录账号为企业
        if(customer.getUserType() == 2){
            //循环去调用充值后套餐的接口
            map.put("customer_type","1"); //企业
            sql = "select memberinfo_id,memberinfo_name,memberinfo_projectid from memberinfo " +
                    "where memberinfo_id = '"+userId+"' ";
            String memJsonString = objectRestService(sql);
            Memberinfo memberinfo = JSONObject.parseObject(memJsonString, Memberinfo.class);
            map.put("project_id",memberinfo.getMemberinfoProjectid());
            log.info("返回出来getMemberinfoProjectid---->{}",memberinfo.getMemberinfoProjectid());
        }else if(customer.getUserType() == 1){//登录账号为个人
            map.put("customer_type","0"); //个人
            map.put("project_id",""); //个人账号传入的项目id为null
        }
        JSONArray packInfo1 = JSONArray.parseArray(JSON.toJSONString(packInfo.get("packInfo")));
        Map<String, Object> rechargeRes = new HashMap<>();
        if(packInfo1.size() > 0){
            for (int i = 0; i < packInfo1.size(); i++) {
                String id = (String) packInfo1.getJSONObject(i).get("id");
                map.put("package_id",id);
                //循环调用充值后的套餐id
                rechargeRes = bossRechargeService.saveCallConfRechargeByPackageInfo(map);
            }
            if(!StringUtils.equals("200",String.valueOf(rechargeRes.get("code")))) {
                return false;
            }
            return true;
        }
        return false;
    }

    //充值后需要的操作222
    @Override
    public Boolean isPayMoneyLeft(JSONObject serverInfo, Customer customer) {
        Map<String, Object> map = new HashMap<>();
        String userId = (String) session.getAttribute("userId");
        String sql;
        map.put("member_id",userId); //这个是通用的
        if(customer.getUserType() == 2){
            //循环去调用充值后套餐的接口
            map.put("customer_type","1"); //企业
            sql = "select memberinfo_id,memberinfo_name,memberinfo_projectid from memberinfo " +
                    "where memberinfo_id = '"+userId+"' ";
            String memJsonString = objectRestService(sql);
            Memberinfo memberinfo = JSONObject.parseObject(memJsonString, Memberinfo.class);
            map.put("project_id",memberinfo.getMemberinfoProjectid());
        }else if(customer.getUserType() == 1){
            map.put("customer_type","0"); //个人
            map.put("project_id",""); //个人账号传入的项目id为null
        }
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(serverInfo.get("serverInfo")));
        // todo 给传过来的参数进行赋值
        List<Map<String, Object>> listMaps = new ArrayList<>();
        if(array.size() > 0){
            for (int i = 0; i < array.size(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                HashMap<String, Object> resMap = new HashMap<>();
                resMap.put("package_detail_goods_type_code",jsonObject.getString("package_detail_goods_type_code"));
                resMap.put("package_detail_goods_type_name",jsonObject.getString("package_detail_goods_type_name"));
                resMap.put("package_detail_univalent",jsonObject.getString("package_detail_univalent"));
                resMap.put("package_detail_show",jsonObject.getString("package_detail_show"));
                resMap.put("package_detail_usage",jsonObject.getString("package_detail_usage"));
                resMap.put("package_detail_effectiveday",jsonObject.getString("package_detail_effectiveday"));
                resMap.put("package_detail_goods_unit",jsonObject.getString("package_detail_goods_unit"));
                listMaps.add(resMap);
            }
            log.debug("在服务配置时将前台的参数封装后的结果--->{}",listMaps);
            map.put("package_detail_info_list", listMaps);
            Map<String, Object> map1 = bossRechargeService.saveCustomPackageInfo(map);
            log.info("传入参数后成功返回的结果---->{}",map1);
            if(!StringUtils.equals("200",String.valueOf(map1.get("code")))) {
                return false;
            }
            return true;
        }
        return false;
    }


    protected String objectRestService(String sql){
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> bySQL = commService.findBySQL(sql, map);
        if(StringUtils.equals(String.valueOf(bySQL.get("data")),"null")){
            throw new ZxException(ExceptionEnums.SELECT_MEMBER_INFO_ERROR);
        }
        List<Map<String,Object>> listData = (List<Map<String, Object>>) bySQL.get("data");
        Map<String, Object> map1 = listData.get(0);
        String jsonString = JSON.toJSONString(map1);
        return jsonString;
    }

}
