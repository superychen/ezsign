package com.cqzx.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.util.JavaBeanUtil;
import com.cqzx.comm.util.MD5Hash;
import com.cqzx.comm.util.ToJson;
import com.cqzx.comm.util.api.CompanyApi;
import com.cqzx.domain.CodeEntity;
import com.cqzx.domain.Companyinfo;
import com.cqzx.domain.Customer;
import com.cqzx.domain.Memberinfo;
import com.cqzx.feign.DbService;
import com.cqzx.feign.FileService;
import com.cqzx.service.CertService;
import com.cqzx.service.CompanyAuthenService;
import com.cqzx.service.ProjectService;
import com.cqzx.service.SealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Description: 企业认证业务逻辑处理层（对公打款，授权书认证，法人四要素验证）
 * @Author: cqyc
 * @Date: 2019-8-01
 */
@Service
@Slf4j
public class CompanyAuthenServiceImpl implements CompanyAuthenService {
    private final static Integer COMPANYINFO_STATE = 0;
    private final static Integer COMPANYINFO_AUTHTYPE = 0;
    //已认证
    private final static Integer COMPANYINFO_STATE_PASS=2;
    //对公打款认证
    private final static Integer COMPANYINFO_AUTHTYPE_REMIT=1;

    @Autowired
    private DbService dbService;

    @Autowired
    private FileService fileService;

    @Autowired
    private CompanyApi companyApi;

    @Autowired
    private HttpSession session;

    @Autowired
    private CertService certService;

    @Autowired
    private SealService sealService;

    @Autowired
    private ProjectService projectService;

    /**
     * 企业打款
     * @param companyinfoCreditcode
     * @return
     */
    @Override
    public Map<String, String> remit(String companyinfoCreditcode) {

        Random ra = new Random();
        String money = "0." + (ra.nextInt(9)+1);
        String ranCode = String.valueOf(new Random().nextInt(9999));
        //修改企业状态为已提交认证未审核的状态
        String sql = "UPDATE  companyinfo SET companyinfo_state= "+1+",companyinfo_amount='"+money+"',companyinfo_verifycode='"+ranCode+"' where companyinfo_creditcode='"+companyinfoCreditcode+"' ";
        //修改企业信息后的结果
        Map<String, Object> res = dbService.executeSql(sql);

        String data = String.valueOf(res.get("data"));
        if(StringUtils.equals(data,"1")){
            Map<String, String> map = new HashMap<>();
            map.put("msg","打款金额已经向后台发送成功");
            map.put("code","200");

            //todo 向管理端发送请求说有人已经申请打款
            return map;
        }
        throw new ZxException(ExceptionEnums.REMIT_MONEY_ERROR);
    }

    /**
     * 上传营业执照
     * @param bytes
     * @return
     */
    @Override
    public String companyUploadFile(byte[] bytes,String filename) {
        CodeEntity upload = fileService.upload(bytes, filename);
        Map<String,Object> data = (Map<String, Object>) upload.getData();
        String res = StringUtils.replace(String.valueOf(data.get("fileid")),";;;","/");
        log.info("res--->{}",res);
        return res;
    }

    /**
     * 注册企业业务
     * @param companyinfo
     * @return
     */
    @Override
    public Boolean companyRegist(Companyinfo companyinfo) {
        String res = companyLook(companyinfo.getCompanyinfoName());
        JSONObject jsonObject = JSONObject.parseObject(res);

        //得到企查查的基本信息
        String result = String.valueOf(jsonObject.get("Result"));
        log.debug("企查查消息接口---->{}",result);
//        String orderNumber = String.valueOf(jsonObject.get("OrderNumber"));
        JSONObject companyLook = JSONObject.parseObject(result);

        String operName = String.valueOf(companyLook.get("OperName"));
        //社会信用代码,
        String creditCode = String.valueOf(companyLook.get("CreditCode"));
        companyinfo.setCompanyinfoCreditcode(companyinfo.getCompanyinfoCreditcode().trim());
        if(!StringUtils.equals(creditCode.trim(),companyinfo.getCompanyinfoCreditcode().trim()) || !StringUtils.equals(operName,companyinfo.getCompanyinfoLegalperson())){
            throw new ZxException(ExceptionEnums.REMIT_AUTH_CREDIT_LEGAL);
        }

        String insertSql = "insert into ";

        //创建企业注册的id
        companyinfo.setCompanyinfoId(MD5Hash.UUIDCreate());
        //注册的企业状态改为未认证状态
        companyinfo.setCompanyinfoState(COMPANYINFO_STATE);
        //注册的企业认证方式初始设置为方式都没有
        companyinfo.setCompanyinfoAuthtype(COMPANYINFO_AUTHTYPE);
        companyinfo.setCompanyinfoType(1);
        companyinfo.setCompanyinfoLogoid(String.valueOf(companyLook.get("ImageUrl")));

        //将实体类转换为map
        Map<String, Object> map = JavaBeanUtil.convertBeanToMap(companyinfo);
        String save = dbService.save("companyinfo", "companyinfo_id", map);
        JSONObject saveRes = JSONObject.parseObject(save);
        String saveCode = String.valueOf(saveRes.get("code"));
        log.info("注册企业返回结果---->{}",saveRes);
        if (!StringUtils.equals(saveCode,"200")) {
            throw new ZxException(ExceptionEnums.COMPANY_REGISTER_ERROR);
        }
        //创建完企业后,创建成员节点表
        Memberinfo memberinfo = new Memberinfo();
        memberinfo.setMemberinfoId(MD5Hash.UUIDCreate());//用户成员节点id,
        memberinfo.setMemberinfoType(0);//节点类型,0   根节点公司; 因为是创建企业，所以企业肯定是根节点，这里写死
        memberinfo.setMemberinfoLeftcode(1);
        memberinfo.setMemberinfoRightcode(2);
        memberinfo.setMemberinfoLevel(1);
        memberinfo.setMemberinfoName(companyinfo.getCompanyinfoName());
        memberinfo.setMemberinfoPid(memberinfo.getMemberinfoId());
        memberinfo.setMemberinfoCompanyname(companyinfo.getCompanyinfoName());//企业名(企业信息表)
        memberinfo.setMemberinfoCompanyid(companyinfo.getCompanyinfoId());//节点所在企业的id,
        //将创建后的memberinfoId存入session
        session.setAttribute("registMemberId",memberinfo.getMemberinfoId()); 
        //此时已经登陆状态，所以从登陆状态中取出session
        Map<String,Object> cusMap = (Map<String, Object>) session.getAttribute("customer");
        String cusJson = new JSONObject(cusMap).toJSONString();
        Customer customer = JSONObject.parseObject(cusJson, Customer.class);

        if (customer == null) {
            throw new ZxException(ExceptionEnums.CUSTOMER_SESSION_ERROR);
        }
        memberinfo.setMemberinfoCustomerid(customer.getCustomerId());//节点关联的账户id,
        memberinfo.setMemberinfoStatus(1);//节点状态,1 有效;2 冻结;3 注销;4已提交认证未审核
        //这里是企业的创建，所以默认为超级管理员
        memberinfo.setMemberinfoRole(1);//企业角色,0 成员;1 超级管理员;2 审批管理员;3 档案管理员;4 系统管理员
        memberinfo.setMemberinfoConfig1(1);//1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知,
        memberinfo.setMemberinfoConfig2(1);//1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知,
        memberinfo.setMemberinfoConfig3(1);//1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知,
        memberinfo.setMemberinfoConfig4(1);//1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知,
        memberinfo.setMemberinfoConfig5(1);//1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知,
        memberinfo.setMemberinfoConfig6(1);//1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知,
        memberinfo.setMemberinfoConfig7(1);//1 短信和邮箱都通知;2 短信;3 邮箱;4 都不通知,
        memberinfo.setMemberinfoCheckbox8(1);//1 勾选保存;2 不保存,

        //将实体类转换为map
        Map<String, Object> memberMap = JavaBeanUtil.convertBeanToMap(memberinfo);
        String memberSave = dbService.save("memberinfo", "memberinfo_id", memberMap);
        JSONObject memberSaveRes = JSONObject.parseObject(memberSave);
        String memberSaveCode = String.valueOf(memberSaveRes.get("code"));
        if (!StringUtils.equals(memberSaveCode,"200")) {
            throw new ZxException(ExceptionEnums.COMPANY_REGISTER_ERROR);
        }
        return true;
    }

    /**
     * 对公打款认证
     * @param companyinfoAmount 银行打款金额,
     * @param companyinfoVerifycode 银行打款验证码,
     * @return 返回认证成功或者失败
     */
    @Override
    public Boolean companyRemitAuth(String companyinfoAmount, String companyinfoVerifycode,String companyinfoCreditcode) {
        Map<String, Object> map = dbService.get("companyinfo", "companyinfo_creditcode", companyinfoCreditcode);
        //将查询到的结果转换为json字符串
        if(StringUtils.equals(String.valueOf(map.get("data")),"{}")){
            throw new ZxException(ExceptionEnums.SELECT_COMPANY_ERROR);
        }
        String data = ToJson.stringToJson(String.valueOf(map.get("data")));
        Companyinfo companyinfo = JSONObject.parseObject(data, Companyinfo.class);
        log.info("对公打款认证时得到的企业名称--->{}",companyinfo.getCompanyinfoName());
        //如果当前企业已经认证过,就不需要当前认证
        if(companyinfo.getCompanyinfoAuthtype() != 0){
            throw new ZxException(ExceptionEnums.COMPANY_HAS_AUTH);
        }
        //如果金额错误，抛出异常
        if(!StringUtils.equals(companyinfo.getCompanyinfoAmount(),companyinfoAmount)){
            throw new ZxException(ExceptionEnums.REMIT_AUTH_AMOUNT_ERROR);
        }

        //如果验证码错误，抛出异常
        if (!StringUtils.equals(companyinfo.getCompanyinfoVerifycode(),companyinfoVerifycode)) {
            throw new ZxException(ExceptionEnums.REMIT_AUTH_CODE_ERROR);
        }

        String sql = "update companyinfo set companyinfo_state="+COMPANYINFO_STATE_PASS+" ,companyinfo_authtype="+COMPANYINFO_AUTHTYPE_REMIT+" where companyinfo_creditcode='"+companyinfoCreditcode+"'";
        //修改后的结果
        Map<String, Object> upResult = dbService.executeSql(sql);
        String upData = String.valueOf(upResult.get("data"));
        log.debug("修改后的结果--->{}",upResult);

        //企业认证成功后需要做的事情
        afterCompanyAuth(companyinfo);
        //比较当前比较的结果
        if (StringUtils.equals(upData,"1")) {
            return true;
        }
        
        return false;
    }


    /**
     * 企查查接口
     * @param companyinfoName
     * @return
     */
    protected String companyLook(String companyinfoName){
        String res = companyApi.companyAuth(companyinfoName);
        JSONObject jsonObject = JSONObject.parseObject(res);

        Object datas = jsonObject.get("datas");

        JSONObject jsonObject1 = JSONObject.parseObject(String.valueOf(datas));

        Object status = jsonObject1.get("Status");

        //判断企查查是否有这个企业
        if (!StringUtils.equals(String.valueOf(status),"200")) {
            throw new ZxException(ExceptionEnums.HAS_COMPANY_ERROR);
        }
        log.debug("企查查查询结果--->{}",res);
        return String.valueOf(datas);
    }


    /**
     * todo 企业认证成功后需要做的事情
     */
    @Override
    public void afterCompanyAuth(Companyinfo companyinfo){
        String certSql = "select certinfo_id,certinfo_type,certinfo_userid from certinfo " +
                        " where certinfo_type = '2' and certinfo_userid = '"+companyinfo.getCompanyinfoId()+"' ";
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> cuerts = dbService.findBySQL(certSql, map);
        //将查询出来的数据,先查询证书这张表有没有对应的企业信息，一对一的，如果证书有数据，就不创建证书以及印章
        List<Map<String,Object>> data = (List<Map<String, Object>>) cuerts.get("data");
        if(data.size() == 0){
            //生成企业证书
            Boolean isCompanyCert = certService.createCompanyCert(companyinfo);
            if(!isCompanyCert){
                throw new ZxException(ExceptionEnums.CERT_CREATE_ERROR);
            }
            //生成企业印章
            Boolean isCompanySeal = sealService.createCompanySeal(companyinfo);
            if(!isCompanySeal){
                throw new ZxException(ExceptionEnums.SEAL_CREATE_ERROR);
            }
            //todo 关联用户资金这一块,无法得到接口，暂时不进行关联，如果关联，在对应的memberinfo表中配置套餐信息

            //创建对应企业的项目
            String projectId = projectService.createCompanyProject(companyinfo);
            String registMemberId = (String) session.getAttribute("registMemberId");
            if(StringUtils.isBlank(registMemberId)){
                throw new ZxException(ExceptionEnums.REGISTER_MEMBER_SESSION_ERROR);
            }
            String sql = "update memberinfo set memberinfo_projectid ='"+projectId+"' where "+
                    " memberinfo_id = '"+registMemberId+"' ";
            //将projiectid修改到memberinfo中去
            dbService.executeSql(sql);
            if (StringUtils.isBlank(projectId)) {
                throw new ZxException(ExceptionEnums.PROJECT_CREATE_ERROR);
            }
        }
    }


    /**
     * 查询单个企业的信息
     */
    @Override
    public Companyinfo getOneCompany(String companyInfoId) {
        String sql ="select " +
                "  companyinfo_id,companyinfo_name,companyinfo_creditcode,companyinfo_legalperson,companyinfo_file " +
                " from companyinfo where companyinfo_id ='"+companyInfoId+"'";
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> bySQL = dbService.findBySQL(sql, map);
        if(StringUtils.equals(String.valueOf(bySQL.get("data")),"null")){
            throw new ZxException(ExceptionEnums.SELECT_COMPANY_ERROR);
        }
        List<Map<String,Object>> listData = (List<Map<String, Object>>) bySQL.get("data");
        if(CollectionUtils.isEmpty(listData)){
            throw new ZxException(ExceptionEnums.SELECT_COMPANY_ERROR);
        }
        Map<String, Object> map1 = listData.get(0);
        String jsonString = JSON.toJSONString(map1);
        Companyinfo companyinfo = JSONObject.parseObject(jsonString, Companyinfo.class);
        log.debug("在查询单个企业过程中获得的企业基本信息---->{}",companyinfo.getCompanyinfoName());
        return companyinfo;
    }

}
