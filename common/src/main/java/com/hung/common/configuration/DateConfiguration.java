/**
 * ****************************************************
 * * Description :
 * * File        : DateConfiguration.java
 * * Author      : hung.tran
 * * Date        : Nov 05, 2020
 * ****************************************************
 **/
package com.hung.common.configuration;

import com.hung.common.component.date.function.impl.DefaultInstantFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.function.Supplier;

@Configuration
public class DateConfiguration {
    @Bean
    public Supplier<Instant> defaultInstancFactory() {

        return new DefaultInstantFactory();
    }
}
