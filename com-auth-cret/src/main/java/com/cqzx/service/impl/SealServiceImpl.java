package com.cqzx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.util.JavaBeanUtil;
import com.cqzx.comm.util.MD5Hash;
import com.cqzx.comm.util.ToJson;
import com.cqzx.comm.util.sealUtil.CreateSeal;
import com.cqzx.domain.*;
import com.cqzx.feign.DbService;
import com.cqzx.feign.FileService;
import com.cqzx.service.SealService;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @Description: 印章业务处理层
 * @Author: cqyc
 * @Date: 2019-8-8
 */
@Service
@Slf4j
public class SealServiceImpl implements SealService {

    @Autowired
    private FileService fileService;

    @Autowired
    private DbService dbService;

    @Autowired
    private HttpSession session;
    /**
     * 生成个人印章
     * @param person
     * @return
     */
    @Override
    public Boolean createPersonSeal(Perosoninfo person) {
        //生成个人印章
        byte[] bytes = CreateSeal.personSeal(person.getPersoninfoName());
        //todo (文件上传服务)将印章保存到fastdfs
        String fileId = uploadFile(bytes, person.getPersoninfoIdcard()+".jpg");//用个人的身份证号作为印章文件名
        Sealinfo sealinfo = new Sealinfo();
        sealinfo.setSealinfoId(MD5Hash.UUIDCreate());
        sealinfo.setSealinfoType(1);//这里为创建个人印章，所以type为1
        sealinfo.setSealinfoUserid(person.getPersoninfoId());//关联个人id,默认使用者id
        sealinfo.setSealinfoName(person.getPersoninfoName());//印章名称
        sealinfo.setSealinfoFiletype("jpg");//印章图片文件格式
        sealinfo.setSealinfoImageid(fileId);//印章图片存储ID
        sealinfo.setSealinfoCreaterid(person.getPersoninfoId());//印章创建ID  对应personinfoid
        sealinfo.setSealinfoCreatertype(1);//1 对应personinfo  2 对应memberinfo
        sealinfo.setSealinfoCreatername(person.getPersoninfoName());//印章创建人名称
        sealinfo.setSealinfoCreatetime(new Date());
        sealinfo.setSealinfoState(1);//印章状态:0=无效;1=有效;2=未审核

        //将印章信息保存到数据库中
        return insertSeal(sealinfo);
    }

    /**
     * 创建企业印章
     * @param companyinfo 企业信息
     * @return
     */
    @Override
    public Boolean createCompanySeal(Companyinfo companyinfo) {
        byte[] companySeal = CreateSeal.companySeal(companyinfo.getCompanyinfoName());
        String fileId = uploadFile(companySeal,companyinfo.getCompanyinfoCreditcode()+".jpg");//用企业的社会信用作为印章文件名

        Sealinfo sealinfo = new Sealinfo();
        sealinfo.setSealinfoId(MD5Hash.UUIDCreate());//企业印章的唯一id
        sealinfo.setSealinfoType(2);//这里为创建企业印章，所以type为2
        sealinfo.setSealinfoUserid(companyinfo.getCompanyinfoId());//企业使用方id,默认为创建人的id
        sealinfo.setSealinfoName(companyinfo.getCompanyinfoName());//企业印章名称
        sealinfo.setSealinfoFiletype("jpg");//印章图片文件格式
        sealinfo.setSealinfoImageid(fileId);//印章图片存储ID
        //从session里面取出登录的customer的值，根据customerid查询对应的memberid的值
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            throw new ZxException(ExceptionEnums.CUSTOMER_SESSION_ERROR);
        }
        //获取memberinfo表中的信息
        Map<String, Object> memberMap = dbService.get("memberinfo", "memberinfo_customerid", customer.getCustomerId());
        //将从数据库服务的查询出来的数据转换为json字符串
        String yc = ToJson.stringToJson(String.valueOf(memberMap.get("data")));
        log.debug("yc ----> {}",yc);
        Memberinfo memberinfo = JSONObject.parseObject(yc, Memberinfo.class);
        log.debug("memberinfo ----> {}",memberinfo);
        sealinfo.setSealinfoCreaterid(memberinfo.getMemberinfoId());//印章创建ID  对应memberinfo的id
        sealinfo.setSealinfoCreatertype(2);//1 对应personinfo  2 对应memberinfo
        sealinfo.setSealinfoCreatername(memberinfo.getMemberinfoCompanyname());//印章创建人名称
        sealinfo.setSealinfoCreatetime(new Date());
        sealinfo.setSealinfoState(1);//印章状态:0=无效;1=有效;2=未审核

        return insertSeal(sealinfo);
    }

    /**
     * 上传文件
     */
    protected String uploadFile(byte[] bytes,String filename) {
        CodeEntity upload = fileService.upload(bytes, filename);
        Map<String,Object> data = (Map<String, Object>) upload.getData();
        log.debug("uploadfile--->data--->{}",data.get("fileid"));
        //将fileId
        String res  = StringUtils.replace(String.valueOf(data.get("fileid")), ";;;", "/");
        return res;
    }

    /**
     * 将个人或者企业的印章保存到数据库中去
     * @param sealinfo 印章信息表
     * @return
     */
    protected Boolean insertSeal(Sealinfo sealinfo){
        //将印章信息保存到数据库中
        Map<String, Object> map = JavaBeanUtil.convertBeanToMap(sealinfo);
        String save = dbService.save("sealinfo", "sealinfo_id", map);
        log.debug("保存结果-->{}",save);

        JSONObject saveRes = JSONObject.parseObject(save);
        String msg = (String) saveRes.get("data");
        if(StringUtils.equals(msg,"could not execute statement")){
            throw new ZxException(ExceptionEnums.CERT_CREATE_ERROR);
        }
        return true;
    }


}
