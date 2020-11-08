/**
 * ****************************************************
 * * Description :
 * * File        : BaseValidator.java
 * * Author      : hung.tran
 * * Date        : Nov 07, 2020
 * ****************************************************
 **/
package com.hung.common.validation;

import com.hung.common.component.support.ValidationSupport;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseValidator<T> {

    @Autowired
    protected ValidationSupport validationSupport;

    /**
     * initialize method
     *
     * @param constraintAnnotation
     */
    public void initialize(final T constraintAnnotation) {
        // no implement
    }
}
