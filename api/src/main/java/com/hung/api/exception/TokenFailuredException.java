/**
 * ****************************************************
 * * Description :
 * * File        : TokenFailuredException.java
 * * Author      : hung.tran
 * * Date        : Nov 11, 2020
 * ****************************************************
 **/
package com.hung.api.exception;

import com.hung.common.annotation.ResponseStatus;
import com.hung.common.enums.ECOResponseStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ResponseStatus(code = ECOResponseStatus.TOKEN_FAILURE)
public class TokenFailuredException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TokenFailuredException(final String msg){
        super(msg);
        log.error(msg);
    }

    public TokenFailuredException(final String msg, final Throwable throwable){
        super(msg, throwable);
        log.error(msg, throwable);
    }
}
