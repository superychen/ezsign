package com.cqzx.service;

import com.cqzx.domain.Perosoninfo;

/**
 * 个人认证业务
 */
public interface PersonAuthenService {


    Boolean updateAfterBank(String bankid, String idnumber, String fullname, String phonenum, String code);

    Boolean authAfterPhone(String personinfoName, String personinfoTelephone, String imgCode, String code);

    Boolean updateAfterFace(String customerId, String idCardname, String idCardnumber, String telephone);

    void faceAuthError(String customerId);

    void faceAuthTypeTwo(String customerId);

}
