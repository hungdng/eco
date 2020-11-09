/**
 * ****************************************************
 * * Description :
 * * File        : ResponseStatus.java
 * * Author      : hung.tran
 * * Date        : Nov 09, 2020
 * ****************************************************
 **/
package com.hung.common.annotation;

import com.hung.common.enums.ECOResponseStatus;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseStatus {
    ECOResponseStatus code() default ECOResponseStatus.SYSTEM_FAILURE;
}
