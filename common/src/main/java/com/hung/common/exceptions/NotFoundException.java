/**
 * ****************************************************
 * * Description :
 * * File        : NotFoundException.java
 * * Author      : hung.tran
 * * Date        : Nov 09, 2020
 * ****************************************************
 **/
package com.hung.common.exceptions;

import com.hung.common.annotation.ResponseStatus;
import com.hung.common.enums.ECOResponseStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ResponseStatus(code = ECOResponseStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * init NotFountException
     * @param msg error messager
     */
    public NotFoundException(final String msg) {
        super(msg);
        log.error(msg);
    }

    /**
     * init NotFoundException
     * @param msg error messager
     * @param throwable
     */
    public NotFoundException(final String msg, final Throwable throwable) {
        super(msg, throwable);
        log.error(msg, throwable);
    }
}
