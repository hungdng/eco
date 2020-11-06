/**
 * ****************************************************
 * * Description :
 * * File        : RequireCheck.java
 * * Author      : hung.tran
 * * Date        : Nov 06, 2020
 * ****************************************************
 **/
package com.hung.common.validation.constraints;

import javax.validation.Constraint;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = )
public @interface RequireCheck {

    String message() default "{requirecheck}";
}
