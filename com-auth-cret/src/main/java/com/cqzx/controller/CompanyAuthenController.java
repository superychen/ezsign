package com.cqzx.controller;

import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.domain.Companyinfo;
import com.cqzx.service.CompanyAuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


/**
 * @Description: 企业认证控制台（对公打款，法人银行卡四要素，授权书认证）
 * @Author: cqyc
 * @Date: 2019-8-01
 */
@RestController
@RequestMapping("/company")
public class CompanyAuthenController {

    @Autowired
    private CompanyAuthenService companyAuthenService;

    /**
     * 确认打款
     * @param companyinfoCreditcode 企业信息名称
     * @return
     */
    @PostMapping("/remit")
    @ResponseStatus(HttpStatus.OK)
    public Map<String,String> remit(@RequestParam(value = "companyinfoCreditcode") String companyinfoCreditcode){

        return companyAuthenService.remit(companyinfoCreditcode);
    }


    /**
     * 注册企业,
     * companyinfoName,企业名称
     * companyinfoCreditcode 社会信用代码
     * companyinfoFile 企业营业执照id
     * companyinfoLegalperson 法人姓名
     */
    @PostMapping("/regist")
    public Boolean CompanyRegist(@RequestBody Companyinfo companyinfo){
        return companyAuthenService.companyRegist(companyinfo);
    }


    /**
     * 对公打款认证
     * @param companyinfoAmount 银行打款金额,
     * @param companyinfoVerifycode 银行打款验证码,
     * @param companyinfoCreditcode 企业信用代码
     * @return 返回认证成功或者失败
     */
    @PostMapping("/remit/auth")
    public Boolean companyRemitAuth(@RequestParam("companyinfoAmount") String companyinfoAmount,
                                    @RequestParam("companyinfoVerifycode") String companyinfoVerifycode,
                                    @RequestParam("companyinfoCreditcode") String companyinfoCreditcode){

        return companyAuthenService.companyRemitAuth(companyinfoAmount,companyinfoVerifycode,companyinfoCreditcode);
    }

    /**
     * 上传文件(营业执照)
     * @return 返回文件流水id
     */
    @PostMapping("/file")
    public String companyUploadFile(@RequestParam("file") MultipartFile file){
        try {
            byte[] bytes = file.getBytes();
            return companyAuthenService.companyUploadFile(bytes,file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ZxException(ExceptionEnums.FILE_UPLOAD_ERROR);
        }
    }

}
