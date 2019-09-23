package com.ezcsign.aop;

import java.lang.annotation.*;

/**
 * describe:
 *
 * @author donting
 * @date 2019/07/25
 */


/**
 * @author blueriver
 * @description z注解
 * @date 2017/11/17
 * @since 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Required {
    int[] value() default {PermissionConstants.ALL};
}


