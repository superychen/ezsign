package com.cqzx.comm.util.sealUtil;


import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.sun.org.apache.regexp.internal.REUtil;

/**
 * @Description: 印章生成
 * @Author: cqyc
 * @Date: 2019-8-8
 */
public class CreateSeal {

    /**
     * 创建个人印章
     * @param name
     * @return
     */
    public static byte[] personSeal(String name){
        Sealfun sealfun = new Sealfun();
//        CommonUtil.getStrObjCvert(data.get("fullName"));
        String sealname = name;
        int fontSize = 0;
        if(name.length() == 3){
            fontSize = 30;
        }
        if(name.length() == 2 || name.length() == 4){
            fontSize = 40;
        }
        try {
            byte[] sealBytes = sealfun.imagePersonSeal(name, fontSize);
            return sealBytes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ZxException(ExceptionEnums.SEAL_CREATE_ERROR);
        }
    }

    public static byte[] companySeal(String companyname){
        Sealfun sealfun = new Sealfun();
        byte[] sealBytes = new byte[0];
        try {
            sealBytes = sealfun.imageCompanySeal(companyname,"");
            return sealBytes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ZxException(ExceptionEnums.SEAL_CREATE_ERROR);
        }
    }


}
