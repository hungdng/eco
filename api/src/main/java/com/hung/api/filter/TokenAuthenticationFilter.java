/**
 * ****************************************************
 * * Description :
 * * File        : TokenAuthenticationFilter.java
 * * Author      : hung.tran
 * * Date        : Nov 11, 2020
 * ****************************************************
 **/
package com.hung.api.filter;

import com.hung.api.component.support.AuthenticationSupport;
import com.hung.api.dto.AuthenticationToken;
import com.hung.api.exception.TokenFailuredException;
import com.hung.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private AuthenticationSupport authenticationSupport;

    protected TokenAuthenticationFilter(final RequestMatcher matcher) {
        super(matcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        final String token = authenticationSupport.getBearerToken(request);
        if (StringUtils.isBlank(token)){
            throw new TokenFailuredException("Authorization header cannot be blank!");
        }
        return getAuthenticationManager().authenticate(new AuthenticationToken(token));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {
        final SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }
}
