/**
 * ****************************************************
 * * Description :
 * * File        : EmptyException.java
 * * Author      : hung.tran
 * * Date        : Nov 10, 2020
 * ****************************************************
 **/
package com.hung.common.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmptyException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    public EmptyException(final String msg) {
        super(msg);
        log.error(msg);
    }

    public EmptyException(final String msg, final Throwable throwable) {
        super(msg, throwable);
        log.error(msg,throwable);
    }
}
