/**
 * ****************************************************
 * * Description :
 * * File        : MessageCode.java
 * * Author      : hung.tran
 * * Date        : Nov 03, 2020
 * ****************************************************
 **/
package com.hung.eco.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  MessageCode implements CodeEnum {
    SUCCESS_GET_DATA("msg00002",""),
    SUCCESS_DATA("msg00005",""),
    SUCCESS_NO_DATA("msg00006",""),
    SUCCESS_CREATE("msg00012",""),
    SUCCESS_LOAD_DATA("msg00031",""),
    SUCCESS_UPDATE("msg00015",""),
    SUCCESS_UPLOAD("msg00028",""),
    SUCCESS_DELETE("msg00034",""),
    SUCCESS_GET_DETAIL("msg00009",""),
    SUCCESS_UPDATE_PASSWORD("msg00044",""),
    SUCCESS_USER_LOGIN("msg00018",""),
    SUCCESS_PUBLIC_AUDIT("msg00025",""),
    ERROR_GET_DATA("msg00002",""),
    ERROR_LOGIN("msg00018",""),
    ERROR_FETCH("msg00007",""),
    ERROR_LOAD_DATA("msg00032",""),
    ERROR_UPDATE("msg00016",""),
    ERROR_UPLOAD("msg00029",""),
    ERROR_GET_DETAIL("msg00010",""),
    ERROR_PUBLIC_AUDIT("msg00026",""),
    ERROR_DELETE("msg00035",""),
    ERROR_CREATE("msg00013",""),
    USER_LOCK("msg00019",""),
    PASSWORD_EXPIRED("userExpiredCheck",""),
    USER_PASSWORD_ERROR("msg00020",""),
    PASSWORD_NOT_MATCH("notMatchPwdCheck",""),
    TOKEN_EXPIRED("",""),
    TOKEN_FAILURE("",""),
    SYSTEM_OBJECT_NOT_FOUND("notFound",""),
    SYSTEM_FAILURE_MESSAGE("systemError",""),
    NEW_PASSWORD_NOT_MATCH_CURRENT_PASSWORD("msg00049",""),
    NEW_PASSWORD_AND_NEW_PASSWORD_CONFIRM_NOT_MATCH("msg00047",""),
    USER_RULE_ID_LENGTH_CHECK("userRuleIdLengthCheck","");

    private final String value;
    private final String name;

    public String getValue() {
        return value;
    }
}
