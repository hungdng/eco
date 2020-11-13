/**
 * ****************************************************
 * * Description :
 * * File        : ResourceNotFoundException.java
 * * Author      : hung.tran
 * * Date        : Nov 13, 2020
 * ****************************************************
 **/
package com.hung.common.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    @Builder
    public ResourceNotFoundException( String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
