package com.cqzx.service;


import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SealConsoleService {

    List<Map<String, Object>> membersSeal(String userId);

    void updateSeal(String sealId, String sealName);

    void deleteSeal(String sealId);

    void addSeal(String sealName,String sealFont,String sealNum,String memberId);

    void uploadSeal(String sealName, String sealFile,String memberId);

    void memberSeal(String sealId, String memberId, Date startTime, Date endTime);

    List<Map<String,Object>> sealGrants(Integer page,String sealName,String authorizer,String startTime,String endTime,String memberinfoId) throws ParseException;

    void updateGrant(String sealGrantId, String start, String end);

    void deleteGrant(String grantId);

    Integer countGrant();

    List<Map<String,Object>> cusMemberSeal(String userId);

    List<Map<String,Object>> cusPersonSeal(String userId);

    void savePersonSeal(String addSealFont, Integer addSealNum,String userId);
}
