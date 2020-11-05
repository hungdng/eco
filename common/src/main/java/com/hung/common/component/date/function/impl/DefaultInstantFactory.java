/**
 * ****************************************************
 * * Description :
 * * File        : DefaultInstantFactory.java
 * * Author      : hung.tran
 * * Date        : Nov 05, 2020
 * ****************************************************
 **/
package com.hung.common.component.date.function.impl;

import java.time.Clock;
import java.time.Instant;
import java.util.Objects;
import java.util.function.Supplier;

public class DefaultInstantFactory implements Supplier<Instant> {

    private final Clock baseClock;

    /**
     * build factory base on systemUTC.
     */
    public DefaultInstantFactory() {
        this(Clock.systemUTC());
    }

    /**
     * build factory base on clock.
     *
     * @param baseClock
     */
    public DefaultInstantFactory(final Clock baseClock) {
        Objects.requireNonNull(baseClock);
        this.baseClock = baseClock;
    }

    /**
     * get current time from clocl
     *
     * @return at the moment
     */
    @Override
    public Instant get() {
        return baseClock.instant();
    }
}
