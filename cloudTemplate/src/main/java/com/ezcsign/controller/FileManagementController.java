package com.ezcsign.controller;

import com.ezcsign.entity.Signerinfo;
import com.ezcsign.re.CodeEntity;
import com.ezcsign.re.ReCode;
import com.ezcsign.service.FileManagementService;
import com.ezcsign.utile.MapUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
public class FileManagementController {
    @Autowired
    FileManagementService fileManagementService;
    @PostMapping("/modifyArchive")
    public CodeEntity modifyArchive(Integer companyinfoFilejurisdiction1, Integer companyinfoFilejurisdiction2, HttpSession httpSession){
        HashMap Map = (HashMap) httpSession.getAttribute("signerinfo");
        Signerinfo signerinfo = new Signerinfo();
        signerinfo = MapUtile.mapToBean(Map, signerinfo);
        if (signerinfo == null) {
            return new CodeEntity(ReCode.EXCEPTION, "非法访问");
        }
        String modifyArchive = fileManagementService.modifyArchive(companyinfoFilejurisdiction1,companyinfoFilejurisdiction2,signerinfo.getCreateId());
        return null;
    }
}
