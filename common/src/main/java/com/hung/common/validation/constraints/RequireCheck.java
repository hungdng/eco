/**
 * ****************************************************
 * * Description :
 * * File        : RequireCheck.java
 * * Author      : hung.tran
 * * Date        : Nov 06, 2020
 * ****************************************************
 **/
package com.hung.common.validation.constraints;

import com.hung.common.annotation.ErrorCode;
import com.hung.common.validation.RequireCheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = RequireCheckValidator.class)
@ErrorCode("2001")
public @interface RequireCheck {

    /** messsage */
    String message() default "{requirecheck}";

    /** */
    Class<?>[] groups() default { };

    /** */
    Class<? extends Payload>[] payload() default { };
}
