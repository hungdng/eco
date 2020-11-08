/**
 * ****************************************************
 * * Description :
 * * File        : RequireCheckValidator.java
 * * Author      : hung.tran
 * * Date        : Nov 06, 2020
 * ****************************************************
 **/
package com.hung.common.validation;

import com.hung.common.validation.constraints.RequireCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequireCheckValidator extends BaseValidator<RequireCheck>
        implements ConstraintValidator<RequireCheck, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !validationSupport.isBlank(value);
    }

    @Override
    public void initialize(RequireCheck constraintAnnotation) {

    }
}
