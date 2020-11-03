/**
 * ****************************************************
 * * Description :
 * * File        : DateSupport.java
 * * Author      : hung.tran
 * * Date        : Nov 03, 2020
 * ****************************************************
 **/
package com.hung.eco.data.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Supplier;

@Component
public class DateSupport {

    @Autowired
    private Supplier<Instant> supplier;

    /**
     * get current date.
     * @return LocalDate
     */
    public LocalDate getLocalDate() {

        return supplier.get().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * get current date.
     * @return LocalDateTime
     *
     */
    public LocalDateTime getLocalDateTime() {

        return LocalDateTime.ofInstant(supplier.get(), ZoneId.systemDefault());
    }

    /**
     * get current date.
     * @return date
     */
    public Date getDate() {

        return Date.from(supplier.get());
    }
}
