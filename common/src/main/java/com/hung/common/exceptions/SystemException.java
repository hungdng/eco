/**
 * ****************************************************
 * * Description :
 * * File        : SystemException.java
 * * Author      : hung.tran
 * * Date        : Nov 04, 2020
 * ****************************************************
 **/
package com.hung.common.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SystemException(final String msg) {
        super(msg);
        log.error(msg);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(final String msg, final Throwable throwable) {
        super(msg, throwable);
        log.error(msg, throwable);
    }
}
