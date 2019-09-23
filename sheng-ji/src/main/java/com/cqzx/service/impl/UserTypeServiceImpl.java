package com.cqzx.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.util.ToJson;
import com.cqzx.domain.Companyinfo;
import com.cqzx.domain.Customer;
import com.cqzx.domain.Memberinfo;
import com.cqzx.domain.Perosoninfo;
import com.cqzx.feign.DbService;
import com.cqzx.service.UserTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Service
@Slf4j
public class UserTypeServiceImpl implements UserTypeService {

    @Autowired
    private DbService dbService;

    @Autowired
    private HttpSession session;
    /**
     * 判断当前的登录用户是否拥有个人签和企业签
     * @param customer
     * @return
     */
    //0表示该账户没有对应的信息或者企业信息
    @Override
    public Map<String, String> isAuth(Customer customer) {
        Map<String, String> map = new HashMap<>();
        //不管如何,都将当前登录的账户Id返回出去
        map.put("customerId",customer.getCustomerId());
        //如果当前登录的账户对应的个人信息为空,则map存入的personinfo为0
        if(StringUtils.isBlank(customer.getCustomerPersonid())){
            map.put("personInfo","0");
        }else{
            Map<String, Object> perMap = dbService.get("personinfo", "personinfo_id", customer.getCustomerPersonid());
            if(StringUtils.equals(String.valueOf(perMap.get("data")),"{}")){
                map.put("personInfo","0");
            }else{
                String stringMem = ToJson.stringToJson(String.valueOf(perMap.get("data")));
                Perosoninfo person = JSONObject.parseObject(stringMem, Perosoninfo.class);
                log.debug("person_authType-->{}",person.getPersoninfoAuthtype());
                if(String.valueOf(person.getPersoninfoAuthtype()).equals("1000000")){
                    map.put("personInfo","0");
                }else {
                    map.put("personInfo","1");
                }
            }
        }
        //查询memberinfo中的customerId
        Map<String, Object> memMap = dbService.get("memberinfo", "memberinfo_customerid", customer.getCustomerId());

        if(StringUtils.equals(String.valueOf(memMap.get("data")),"{}")){
            map.put("companyInfo","0");
        }else{
            String stringMem = ToJson.stringToJson(String.valueOf(memMap.get("data")));
            Memberinfo memberinfo = JSONObject.parseObject(stringMem, Memberinfo.class);
            log.debug("usertype中转换出来的memberinfo-->{}",memberinfo);

            String sql = "select * from companyinfo where companyinfo_id='"+memberinfo.getMemberinfoCompanyid()+"'";
            HashMap<String, Object> map1 = new HashMap<>();
            Map<String, Object> bySQL = dbService.findBySQL(sql, map1);
            List<Map<String,Object>> data = (List<Map<String, Object>>) bySQL.get("data");
            if(data.size() == 0 ){
                throw new RuntimeException("查询企业失败,请确定是否有该企业");
            }
            Map<String, Object> map2 = data.get(0);
            String jsonString = JSON.toJSONString(map2);
            Companyinfo companyinfo = JSONObject.parseObject(jsonString, Companyinfo.class);
//            Map<String, Object> comMap = dbService.get("companyinfo", "companyinfo_id", memberinfo.getMemberinfoCompanyid());
//            String stringCom = ToJson.stringToJson(String.valueOf(comMap.get("data")));
//            Companyinfo companyinfo = JSONObject.parseObject(stringCom, Companyinfo.class);

            //1为有效
            if(memberinfo.getMemberinfoStatus() == 1){
                //企业的状态或者认证的方式为0，如果为1，也不通过都表示未认证
                if(companyinfo.getCompanyinfoState() != 0  && companyinfo.getCompanyinfoAuthtype() != 0){
                    // 说明该账号应该有对应的memberid，有企业对应,并且该企业已经通过认证
                    if(companyinfo.getCompanyinfoState() == 1){
                        //2表示已提交审核，但是还未认证成功,等待管理端修改状态即可认证
                        map.put("companyInfo","2");
                    }else if(companyinfo.getCompanyinfoState() == 8){
                        //状态为预审核通过,用户登录后确认，我这边判断为4，如果为4，多一个按钮
                        map.put("companyInfo","4");
                        map.put("companyInfoCrediCode",companyinfo.getCompanyinfoCreditcode());
                        session.setAttribute("registMemberId",memberinfo.getMemberinfoId());
                    }else{
                        map.put("companyInfo","1");
                    }
                }else{
                    //3表示从组织架构创建的企业,memberinfo有对应的数据，但是并没有认证
                    map.put("companyInfoId",companyinfo.getCompanyinfoId());
                    map.put("companyInfo","3");
                }
            }else{
                //未认证
                map.put("companyInfo","0");
            }
        }
        return map;
    }

    @Override
    public Map<String, String> qrFor(String customerId) {
        Map<String, String> hashMap = new HashMap<>();
        Map<String, Object> map = dbService.getString(customerId + ":authFace");
        String data = String.valueOf(map.get("data"));
        log.debug("轮询redis中是否有人脸认证结果的值--->{}",data);
        if(StringUtils.equals(data,"null")){
            hashMap.put("status","500");//返回状态还在认证中
            return hashMap;
        }else{
            if(StringUtils.equals("true",data)){
                hashMap.put("status","200");
                return hashMap;
            }else{
                //415认证失败,需要告诉客户端
                hashMap.put("status","415");
                return hashMap;
            }
        }
    }

}
