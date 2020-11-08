/**
 * ****************************************************
 * * Description :
 * * File        : ErrorCode.java
 * * Author      : hung.tran
 * * Date        : Nov 07, 2020
 * ****************************************************
 **/
package com.hung.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ErrorCode {
    String value() default "";
}
