/**
 * ****************************************************
 * * Description :
 * * File        : BusinessException.java
 * * Author      : hung.tran
 * * Date        : Nov 09, 2020
 * ****************************************************
 **/
package com.hung.common.exceptions;

import com.hung.common.annotation.ResponseStatus;
import com.hung.common.enums.ECOResponseStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ResponseStatus(code = ECOResponseStatus.BUSINESS_FAILURE)
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private ECOResponseStatus code;

    public BusinessException(final String msg) {
        super(msg);
        log.error(msg);
    }

    public BusinessException(final String msg, final Throwable throwable) {
        super(msg, throwable);
        log.error(msg, throwable);
    }

    public BusinessException(final String msg,final ECOResponseStatus errorCode) {
        super(msg);
        this.code = errorCode;
        log.error(msg,errorCode);
    }
    public ECOResponseStatus getCode() {
        return this.code;
    }
}
