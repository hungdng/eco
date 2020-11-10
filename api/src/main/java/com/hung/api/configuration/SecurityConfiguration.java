/**
 * ****************************************************
 * * Description :
 * * File        : SecurityConfiguration.java
 * * Author      : hung.tran
 * * Date        : Nov 10, 2020
 * ****************************************************
 **/
package com.hung.api.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hung.common.configuration.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProps;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public AuthenticationEntryPoint restAuthenticationEntryPoint() {
        return (httpServletRequest, httpServletResponse, e) -> {
            Map<String, Object> errorObject = new HashMap<>();
            int errorCode = HttpStatus.UNAUTHORIZED.value();
            errorObject.put("message", "Unauthorized access of protected resource, invalid credentials");
            errorObject.put("error", HttpStatus.UNAUTHORIZED);
            errorObject.put("code", errorCode);
            errorObject.put("timestamp", new Timestamp(new Date().getTime()));
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.setStatus(errorCode);
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(errorObject));
        };
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(securityProps.getAllowedOrigins());
        configuration.setAllowedMethods(securityProps.getAllowedMethods());
        configuration.setAllowedHeaders(securityProps.getAllowedHeaders());
        configuration.setAllowCredentials(securityProps.isAllowCredentials());
        configuration.setExposedHeaders(securityProps.getExposedHeaders());
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfigurationSource())
                .and().csrf().disable().formLogin().disable()
                .httpBasic().disable().exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint())
                .and().authorizeRequests()
                .antMatchers(securityProps.getAllowedPublicApis().stream().toArray(String[]::new)).permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated().and()
//                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
