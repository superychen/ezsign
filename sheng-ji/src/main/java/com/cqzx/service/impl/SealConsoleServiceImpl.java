package com.cqzx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.comm.util.JavaBeanUtil;
import com.cqzx.comm.util.MD5Hash;
import com.cqzx.comm.util.ToJson;
import com.cqzx.comm.util.sealUtil.CreateSeal;
import com.cqzx.domain.CodeEntity;
import com.cqzx.domain.Memberinfo;
import com.cqzx.domain.Sealgrant;
import com.cqzx.domain.Sealinfo;
import com.cqzx.feign.DbService;
import com.cqzx.feign.FileService;
import com.cqzx.service.SealConsoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Service
@Slf4j
public class SealConsoleServiceImpl implements SealConsoleService {

    @Autowired
    private DbService dbService;

    @Autowired
    private FileService fileService;


    @Override
    public List<Map<String, Object>> membersSeal(String memberinfoId) {
        Map<String, Object> memMap = dbService.get("memberinfo", "memberinfo_id", memberinfoId);
        String memString = ToJson.stringToJson(String.valueOf(memMap));
        Memberinfo memberinfo = JSONObject.parseObject(memString, Memberinfo.class);
        //查询印章信息
        String sql = "select * from sealinfo where sealinfo_userid='" + memberinfo.getMemberinfoCompanyid() + "' and sealinfo_creatertype='2'";
        HashMap<String, Object> params = new HashMap<>();
        Map<String, Object> sealMap = dbService.findBySQL(sql, params);
        List<Map<String, Object>> listSeal = (List<Map<String, Object>>) sealMap.get("data");
        log.debug("查询印章管理所属的全部印章--->{}", listSeal);
        if (listSeal.size() <= 0) {
            throw new ZxException(ExceptionEnums.SEAL_CREATE_ERROR);
        }
        return listSeal;
    }

    /**
     * 修改印章名称
     * @param sealId   印章Id
     * @param sealName 印章名称
     */
    @Override
    public void updateSeal(String sealId, String sealName) {
        String sql = "update sealinfo set sealinfo_name ='" + sealName + "' where sealinfo_id = '" +
                sealId + "' ";
        Map<String, Object> updateRes = dbService.executeSql(sql);
        log.debug("修改印章表后返回的结果-->{}", updateRes.get("data"));
        //如果修改后的结果不为1,修改失败
        if (!StringUtils.equals(String.valueOf(updateRes.get("data")), "1")) {
            throw new ZxException(ExceptionEnums.UPDATE_SEAL_NAME_ERROR);
        }
    }

    /**
     * 删除印章名称
     *
     * @param sealId 印章Id
     */
    @Override
    public void deleteSeal(String sealId) {
        String sql = "delete from sealinfo where sealinfo_id = '" + sealId + "' ";
        Map<String, Object> delRes = dbService.executeSql(sql);
        log.debug("删除印章表后返回的结果-->{}", delRes.get("data"));
        //如果修改后的结果不为1,修改失败
        if (!StringUtils.equals(String.valueOf(delRes.get("data")), "1")) {
            throw new ZxException(ExceptionEnums.DELETE_SEAL_ERROR);
        }
    }

    @Override
    public void addSeal(String sealName, String sealFont, String sealNum, String memberId) {
        //印章管理里面创建企业印章
        byte[] sealBytes = CreateSeal.companySealFoot(sealFont, sealNum);

        Map<String, Object> memMap = dbService.get("memberinfo", "memberinfo_id", memberId);
        String memString = ToJson.stringToJson(String.valueOf(memMap));
        Memberinfo memberinfo = JSONObject.parseObject(memString, Memberinfo.class);

        String fileId = uploadFile(sealBytes, sealName + ".jpg");
        Sealinfo sealinfo = new Sealinfo();
        sealinfo.setSealinfoId(MD5Hash.UUIDCreate());//企业印章的唯一id
        sealinfo.setSealinfoType(2);//这里为创建企业印章，所以type为2
        sealinfo.setSealinfoUserid(memberinfo.getMemberinfoCompanyid());//企业使用方id,默认为创建人的id
        sealinfo.setSealinfoName(sealName);
        sealinfo.setSealinfoFiletype("jpg");
        sealinfo.setSealinfoImageid(fileId);
        sealinfo.setSealinfoCreaterid(memberinfo.getMemberinfoId());
        sealinfo.setSealinfoCreatertype(2);//1 对应personinfo  2 对应memberinfo
        sealinfo.setSealinfoCreatername(memberinfo.getMemberinfoCompanyname());//印章创建人名称
        sealinfo.setSealinfoCreatetime(new Date());
        sealinfo.setSealinfoState(1);//印章状态:0=无效;1=有效;2=未审核
        insertSeal(sealinfo);
    }

    /**
     * 通过上传印章的方式来创建印章
     *
     * @param sealName 印章名称
     * @param sealFile 印章文件路径
     */
    @Override
    public void uploadSeal(String sealName, String sealFile, String memberId) {
        //印章管理里面创建企业印章
        Map<String, Object> memMap = dbService.get("memberinfo", "memberinfo_id", memberId);
        String memString = ToJson.stringToJson(String.valueOf(memMap));
        Memberinfo memberinfo = JSONObject.parseObject(memString, Memberinfo.class);
        Sealinfo sealinfo = new Sealinfo();
        sealinfo.setSealinfoId(MD5Hash.UUIDCreate());//企业印章的唯一id
        sealinfo.setSealinfoType(2);//这里为创建企业印章，所以type为2
        sealinfo.setSealinfoUserid(memberinfo.getMemberinfoCompanyid());//企业使用方id,默认为创建人的id
        sealinfo.setSealinfoName(sealName);
        sealinfo.setSealinfoFiletype("jpg");
        sealinfo.setSealinfoImageid(sealFile);
        sealinfo.setSealinfoCreaterid(memberinfo.getMemberinfoId());
        sealinfo.setSealinfoCreatertype(2);//1 对应personinfo  2 对应memberinfo
        sealinfo.setSealinfoCreatername(memberinfo.getMemberinfoCompanyname());//印章创建人名称
        sealinfo.setSealinfoCreatetime(new Date());
        sealinfo.setSealinfoState(1);//印章状态:0=无效;1=有效;2=未审核
        insertSeal(sealinfo);
    }

    /**
     * 对相应的印章进行授权
     */
    @Override
    public void memberSeal(String sealId, String memberId, Date startTime, Date endTime) {
//         todo 授权时判断相同得memberId和sealId只能同时出现一次
        String sql = "select count(1) from sealgrant where " +
                " sealgrant_memberid ='"+memberId+"' and sealgrant_sealid='"+sealId+"'";
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> bySQL = dbService.findBySQL(sql, map1);
        if (StringUtils.equals(String.valueOf(bySQL.get("data")), "null")) {
            throw new ZxException(ExceptionEnums.SEAL_GRANT_SELECT);
        }
        List<Map<String, Object>> data = (List<Map<String, Object>>) bySQL.get("data");
        Integer res = (Integer) data.get(0).get("count(1)");
        if(res != 0){
            throw new ZxException(ExceptionEnums.SEAL_GRANT_HAS_BEEN);
        }
        Sealgrant sealgrant = new Sealgrant();
        sealgrant.setSealgrantId(MD5Hash.UUIDCreate());
        sealgrant.setSealgrantMemberid(memberId);
        sealgrant.setSealgrantSealid(sealId);
        sealgrant.setSealgrantFromtime(startTime);
        sealgrant.setSealgrantTotime(endTime);
        //为印章授权的只有企业
        sealgrant.setSealgrantMembertype(2);
        Map<String, Object> map = JavaBeanUtil.convertBeanToMap(sealgrant);
        String save = dbService.save("sealgrant", "sealgrant_id", map);
        log.debug("保存结果-->{}", save);
        JSONObject saveRes = JSONObject.parseObject(save);
        String msg = (String) saveRes.get("data");
        if (StringUtils.equals(msg, "could not execute statement")) {
            throw new ZxException(ExceptionEnums.SEAL_GRANT_ERROR);
        }
    }

    /**
     * 分页查询所有的授权人列表
     * @param memberinfoId 从session里面获取出来的企业用户的最高权限memberId
     * @param page 页码
     * @return
     */
    @Override
    public List<Map<String, Object>> sealGrants(Integer page, String sealName, String authorizer, String startTime, String endTime,
                                                String memberinfoId) throws ParseException {

        String memSql = "select memberinfo_companyid from memberinfo where memberinfo_id = '"+memberinfoId+"' ";
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> bySQL1 = dbService.findBySQL(memSql, map);
        List<Map<String,Object>> data1 = (List<Map<String, Object>>) bySQL1.get("data");
        if(data1.size() == 0){
            throw new RuntimeException("您登陆的账号可能没有访问权限");
        }

        String memberinfoCompanyid = (String) data1.get(0).get("memberinfo_companyid");

        String sql = "select sealgrant_id,sealinfo_id, sealinfo_name, sealinfo_code, sealgrant_fromtime, memberinfo_name, sealgrant_totime " +
                " from sealgrant as g left join sealinfo as s on g.sealgrant_sealid=s.sealinfo_id " +
                " left join memberinfo as m on g.sealgrant_memberid = m.memberinfo_id " +
                " where s.sealinfo_userid = '"+memberinfoCompanyid+"'";
        String countSql = "select count(1) " +
                " from sealgrant as g left join sealinfo as s on g.sealgrant_sealid=s.sealinfo_id " +
                " left join memberinfo as m on g.sealgrant_memberid = m.memberinfo_id " +
                " where s.sealinfo_userid = '"+memberinfoCompanyid+"' ";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotBlank(sealName)) {
            sql = sql + " and s.sealinfo_name like '%" + sealName + "%' ";
            countSql = countSql + " and s.sealinfo_name like '%" + sealName + "%' ";
        }
        if (StringUtils.isNotBlank(authorizer) && StringUtils.isNotBlank(sealName)) {
            sql = sql + " and m.memberinfo_name like '%" + authorizer + "%' ";
            countSql = countSql + " and m.memberinfo_name like '%" + authorizer + "%' ";

        }
        if (StringUtils.isBlank(sealName) && StringUtils.isNotBlank(authorizer)) {
            sql = sql + " and m.memberinfo_name like '%" + authorizer + "%' ";
            countSql = countSql + " and m.memberinfo_name like '%" + authorizer + "%' ";
        }
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            startTime = startTime.replace("Z", " UTC");
            endTime = endTime.replace("Z", " UTC");
            Date start = format.parse(startTime);
            Date end = format.parse(endTime);
            String startDate = sdf.format(start);
            String endDate = sdf.format(end);
            if (StringUtils.isNotBlank(sealName) || StringUtils.isNotBlank(authorizer)) {
                sql = sql + " and g.sealgrant_fromtime > '" + startDate + "' and g.sealgrant_totime < '" + endDate + "'";
                countSql = countSql + " and g.sealgrant_fromtime > '" + startDate + "' and g.sealgrant_totime < '" + endDate + "'";
            } else {
                sql = sql + " and g.sealgrant_fromtime > '" + startDate + "' and g.sealgrant_totime < '" + endDate + "'";
                countSql = countSql + " and g.sealgrant_fromtime > '" + startDate + "' and g.sealgrant_totime < '" + endDate + "'";
            }
        }

        //执行统计sql
        Map<String, Object> bySQL = dbService.findBySQL(countSql, map);
        if (StringUtils.equals(String.valueOf(bySQL.get("data")), "null")) {
            throw new ZxException(ExceptionEnums.SEAL_GRANT_SELECT);
        }
        List<Map<String, Object>> data = (List<Map<String, Object>>) bySQL.get("data");
        Integer res = (Integer) data.get(0).get("count(1)");


        Map<String, Object> bySQLPage = dbService.findBySQLPage(sql, map, page, 5);
        if (StringUtils.equals(String.valueOf(bySQLPage.get("data")), "null")) {
            throw new ZxException(ExceptionEnums.SEAL_GRANT_SELECT);
        }
        List<Map<String, Object>> pageRes = (List<Map<String, Object>>) bySQLPage.get("data");
        for (Map<String, Object> pageRe : pageRes) {
            pageRe.put("sealgrant_fromtime", sdf.format(new Date(Long.parseLong(String.valueOf(pageRe.get("sealgrant_fromtime"))))));
            pageRe.put("sealgrant_totime", sdf.format(new Date(Long.parseLong(String.valueOf(pageRe.get("sealgrant_totime"))))));
            pageRe.put("totalSize", res);
        }
        return pageRes;
    }

    /**
     * 修改授权对应的授权时间
     *
     * @param sealGrantId 授权Id
     * @param start       开始时间
     * @param end         结束时间
     */
    @Override
    public void updateGrant(String sealGrantId, String start, String end) {
        String sql = "update sealgrant set sealgrant_fromtime = '" + start + "', " +
                " sealgrant_totime = '" + end + "' where sealgrant_id = '" + sealGrantId + "' ";
        Map<String, Object> map = dbService.executeSql(sql);
        log.debug("修改印章表后返回的结果-->{}", map);
        //如果修改后的结果不为1,修改失败
        if (!StringUtils.equals(String.valueOf(map.get("data")), "1")) {
            throw new ZxException(ExceptionEnums.UPDATE_SEAL_ERROR);
        }
    }

    /**
     * 删除授权
     */
    @Override
    public void deleteGrant(String grantId) {
        String sql = "delete from sealgrant where sealgrant_id = '" + grantId + "' ";
        Map<String, Object> delRes = dbService.executeSql(sql);
        log.debug("删除印章表后返回的结果-->{}", delRes);
        //如果修改后的结果不为1,修改失败
        if (!StringUtils.equals(String.valueOf(delRes.get("data")), "1")) {
            throw new ZxException(ExceptionEnums.DELETE_SEAL_GRANT_ERROR);
        }
    }

    /**
     * 初始化查询所有的总数
     */
    @Override
    public Integer countGrant() {
        String sql = "select count(*) from sealgrant";
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> bySQL = dbService.findBySQL(sql, map);
        if (StringUtils.equals(String.valueOf(bySQL.get("data")), "null")) {
            throw new ZxException(ExceptionEnums.SEAL_GRANT_SELECT);
        }
        List<Map<String, Object>> data = (List<Map<String, Object>>) bySQL.get("data");
        Integer res = (Integer) data.get(0).get("count(*)");
        return res;
    }

    //我的印章之企业印章,查询当前的企业授权表中的对应账号的企业印章
    @Override
    public List<Map<String, Object>> cusMemberSeal(String userId) {
        String sql = "select s.sealinfo_id,s.sealinfo_name,s.sealinfo_imageid from sealinfo as  s " +
                " left join sealgrant as g on s.sealinfo_id = g.sealgrant_sealid " +
                " left join memberinfo as m on m.memberinfo_id = g.sealgrant_memberid " +
                " where g.sealgrant_memberid = '"+userId+"'";
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> bySQL = dbService.findBySQL(sql, map);
        if (StringUtils.equals(String.valueOf(bySQL.get("data")), "[]")) {
            List<Map<String, Object>> maps = new ArrayList<>();
            return maps;
        }
        List<Map<String, Object>> data = (List<Map<String, Object>>) bySQL.get("data");
        return data;
    }

    // 我的印章之个人印章,查询当前的印章信息表中有几个个人印章
    @Override
    public List<Map<String, Object>> cusPersonSeal(String userId) {
        String sql = "select sealinfo_id,sealinfo_name,sealinfo_imageid from sealinfo where sealinfo_userid = '"+userId+"'" +
                " and sealinfo_type = '1'";
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> bySQL = dbService.findBySQL(sql, map);
        if (StringUtils.equals(String.valueOf(bySQL.get("data")), "[]")) {
            List<Map<String, Object>> maps = new ArrayList<>();
            return maps;
        }
        List<Map<String, Object>> data = (List<Map<String, Object>>) bySQL.get("data");
        return data;
    }

    /**
     * 创建个人印章
     */
    @Override
    public void savePersonSeal(String addSealFont, Integer addSealNum,String userId) {
        //印章管理里面创建企业印章
        byte[] sealBytes = CreateSeal.personSeal(addSealFont);
        String fileId = uploadFile(sealBytes, addSealFont+".jpg");
        Sealinfo sealinfo = new Sealinfo();
        sealinfo.setSealinfoId(MD5Hash.UUIDCreate());//个人印章唯一Id
        sealinfo.setSealinfoType(1);//这里为创建个人印章，所以type为1
        sealinfo.setSealinfoUserid(userId);//对应的personinfo的id
        sealinfo.setSealinfoName(addSealFont);
        sealinfo.setSealinfoCode(addSealNum);
        sealinfo.setSealinfoFiletype("jpg");
        sealinfo.setSealinfoImageid(fileId);
        sealinfo.setSealinfoCreaterid(userId);
        sealinfo.setSealinfoCreatertype(1);//1 对应personinfo  2 对应memberinfo
        sealinfo.setSealinfoCreatername(addSealFont);//印章创建人名称
        sealinfo.setSealinfoCreatetime(new Date());
        sealinfo.setSealinfoState(1);//印章状态:0=无效;1=有效;2=未审核
        insertSeal(sealinfo);
    }


    /**
     * 上传文件
     */
    protected String uploadFile(byte[] bytes, String filename) {
        CodeEntity upload = fileService.upload(bytes, filename);
        Map<String, Object> data = (Map<String, Object>) upload.getData();
        log.info("uploadfile--->data--->{}", data.get("fileid"));
        //将fileId
        String res = StringUtils.replace(String.valueOf(data.get("fileid")), ";;;", "/");
        return res;
    }


    /**
     * 将个人或者企业的印章保存到数据库中去
     *
     * @param sealinfo 印章信息表
     * @return
     */
    protected void insertSeal(Sealinfo sealinfo) {
        //将印章信息保存到数据库中
        Map<String, Object> map = JavaBeanUtil.convertBeanToMap(sealinfo);
        String save = dbService.save("sealinfo", "sealinfo_id", map);
        log.debug("保存结果-->{}", save);
        JSONObject saveRes = JSONObject.parseObject(save);
        String msg = (String) saveRes.get("data");
        if (StringUtils.equals(msg, "could not execute statement")) {
            throw new ZxException(ExceptionEnums.CERT_CREATE_ERROR);
        }
    }
}
