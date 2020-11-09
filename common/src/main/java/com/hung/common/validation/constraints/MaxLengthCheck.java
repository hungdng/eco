/**
 * ****************************************************
 * * Description :
 * * File        : MaxLengthCheck.java
 * * Author      : hung.tran
 * * Date        : Nov 09, 2020
 * ****************************************************
 **/
package com.hung.common.validation.constraints;

import com.hung.common.validation.MaxLengthCheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = MaxLengthCheckValidator.class)
public @interface MaxLengthCheck {

    /** message */
    String message() default "{maxlengthcheck}";

    /**
     * maximum length */

    long max();

    /**
     * group.
     * @return
     */
    Class<?>[] groups() default {};

    /**
     * payload.
     * @return Class<? extends Payload>[]
     */
    Class<? extends Payload>[] payload() default {};
}
