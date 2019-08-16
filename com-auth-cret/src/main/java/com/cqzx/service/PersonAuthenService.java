package com.cqzx.service;

import com.cqzx.domain.Perosoninfo;

/**
 * 个人认证业务
 */
public interface PersonAuthenService {

    Boolean updateAfterFace(Perosoninfo perosoninfo);

    Boolean updateAfterBank(String bankid, String idnumber, String fullname, String phonenum,String code);

    Boolean authAfterPhone(String personinfoName, String personinfoTelephone, String imgCode, String code);
}
