package com.cqzx.service;

import com.cqzx.domain.Perosoninfo;

public interface AuthenticationService {
    String faceAuthtication(byte[] bytes,String requestid, String idCardname, String idCardnumber);

    String faceAuthCode();

    String bank4Auth(String bankid, String idnumber, String fullname, String phonenum);

    Boolean registerPerson(Perosoninfo perosoninfo);

}
