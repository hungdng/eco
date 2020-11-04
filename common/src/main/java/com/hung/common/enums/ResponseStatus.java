/**
 * ****************************************************
 * * Description :
 * * File        : ResponseStatus.java
 * * Author      : hung.tran
 * * Date        : Nov 04, 2020
 * ****************************************************
 **/
package com.hung.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.hung.data.enums.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseStatus implements CodeEnum {
    SUCCESS("0","Success"),
    ERROR("1000","error"),
    VALIDATION_ERROR("2000","Validerror"),
    SYSTEM_FAILURE("1001","error"),
    BUSINESS_FAILURE("1002",""),
    NOT_FOUND("1003","not found"),
    TOKEN_EXPIRED("1004",""),
    TOKEN_FAILURE("1005",""),
    BAD_CREDENTIALS("1006",""),
    MEDIA_TYPE_FAILURE("1007","error"),
    METHOD_FAILURE("1008","error"),
    HTTP_MESSAGE_FAILURE("1009","error"),
    PASSWORD_EXPIRED("1010","error");

    private final String value;
    private final String name;

    @JsonValue
    public String getValue() {
        return value;
    }
}
