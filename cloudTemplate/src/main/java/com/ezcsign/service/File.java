package com.ezcsign.service;


import com.ezcsign.config.FullLogConfiguration;
import com.ezcsign.re.CodeEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="FILE",configuration = FullLogConfiguration.class,path = "/foreign/file")
public interface File {

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    CodeEntity upload(@RequestParam("file") byte[] file, @RequestParam("fileName") String fileName);
}
