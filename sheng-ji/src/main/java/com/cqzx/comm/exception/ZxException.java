package com.cqzx.comm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description: 自动义异常
 * @Author: cqyc
 * @Date:
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ZxException extends RuntimeException {
    private ExceptionEnums exceptionEnums;
}
