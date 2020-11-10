/**
 * ****************************************************
 * * Description :
 * * File        : SecurityProperties.java
 * * Author      : hung.tran
 * * Date        : Nov 10, 2020
 * ****************************************************
 **/
package com.hung.common.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("security")
@Data
public class SecurityProperties {
    private boolean allowCredentials;
    private List<String> allowedOrigins;
    private List<String> allowedHeaders;
    private List<String> exposedHeaders;
    private List<String> allowedMethods;
    private List<String> allowedPublicApis;
}
