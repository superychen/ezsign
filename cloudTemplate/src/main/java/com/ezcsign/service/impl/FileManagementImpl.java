package com.ezcsign.service.impl;

import com.ezcsign.db.DbEntity;
import com.ezcsign.db.DbService;
import com.ezcsign.db.MydbService;
import com.ezcsign.service.File;
import com.ezcsign.service.FileManagementService;
import com.ezcsign.utile.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 档案管理
 * @author tzw
 * @date 2019/07/29
 */

@Service
public class FileManagementImpl extends MydbService implements FileManagementService {
    @Autowired
    File file;
    @Autowired
    MydbService mydbService;
    @Autowired
    DbService dbService;

    @Override
    public String modifyArchive(Integer companyinfoFilejurisdiction1, Integer companyinfoFilejurisdiction2,String customerId) {
        Map<String,Object> map = new HashMap<>();
        DbEntity dbEntity = mydbService.findBySQL("SELECT memberinfo_companyid FROM memberinfo WHERE memberinfo_customerid = '"+customerId+"'",map);
        String selectmemberinfoCompanyId = dbEntity.getData().get(0).get("memberinfo_companyid").toString();
        System.out.println(selectmemberinfoCompanyId);
        String modifyArchive = dbService.executeSql("UPDATE companyinfo SET companyinfo_filejurisdiction1 ="+ companyinfoFilejurisdiction1+
                                ",companyinfo_filejurisdiction2= "+companyinfoFilejurisdiction2 +" WHERE  companyinfo_id='"+selectmemberinfoCompanyId+"'");
        return modifyArchive;
    }
}
