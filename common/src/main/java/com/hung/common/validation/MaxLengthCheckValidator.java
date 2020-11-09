/**
 * ****************************************************
 * * Description :
 * * File        : MaxLengthCheckValidator.java
 * * Author      : hung.tran
 * * Date        : Nov 09, 2020
 * ****************************************************
 **/
package com.hung.common.validation;

import com.hung.common.utils.StringUtils;
import com.hung.common.validation.constraints.MaxLengthCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MaxLengthCheckValidator extends BaseValidator<MaxLengthCheck>
        implements ConstraintValidator<MaxLengthCheck, String> {

    private long max;

    @Override
    public void initialize(MaxLengthCheck anotation) {
        this.max = anotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) return false;
        return validationSupport.checkMax(value, max);
    }

}
