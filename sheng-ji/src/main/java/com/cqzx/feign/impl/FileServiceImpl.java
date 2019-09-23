package com.cqzx.feign.impl;

import com.cqzx.comm.exception.ExceptionEnums;
import com.cqzx.comm.exception.ZxException;
import com.cqzx.domain.CodeEntity;
import com.cqzx.feign.FileService;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Component
public class FileServiceImpl implements FileService {
    @Override
    public CodeEntity upload(byte[] file, String fileName) {
       throw new ZxException(ExceptionEnums.FILE_UPLOAD_ERROR);
    }
}
