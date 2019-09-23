package com.cqzx.feign;

import com.cqzx.domain.CodeEntity;
import com.cqzx.feign.impl.FileServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 文件微服务 模块
 */
@FeignClient(value = "EZCASIGN-FILE", fallback = FileServiceImpl.class, path = "/foreign/file")
public interface FileService {

    /**
     * 上传
     * @param file 文件的字节数组
     * @param fileName 文件名
     * @return data:{ fileId:12335}
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    CodeEntity upload(@RequestBody byte[] file, @RequestParam("fileName") String fileName);

}
