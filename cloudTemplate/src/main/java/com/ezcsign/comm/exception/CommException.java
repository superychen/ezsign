package com.ezcsign.comm.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 统一处理异常
 * @Author: cqyc
 * @Date: 2019-7-25
 */
@ControllerAdvice
@Slf4j
public class CommException {

    @ExceptionHandler(ZxException.class)
    @ResponseBody
    public ResponseEntity<String> handlerException(HttpServletRequest request,ZxException e){
        //执行这个请求的方法
        String requestURI = request.getRequestURI();
        log.debug("----------访问该路径是出现异常: {}-----------",requestURI);
        //获取到自定义异常的枚举类
        ExceptionEnums enums = e.getExceptionEnums();
        return ResponseEntity.status(enums.getCode()).body(enums.getMsg());
    }
}
