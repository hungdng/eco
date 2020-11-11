/**
 * ****************************************************
 * * Description :
 * * File        : AuthenticationToken.java
 * * Author      : hung.tran
 * * Date        : Nov 11, 2020
 * ****************************************************
 **/
package com.hung.api.dto;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

public class AuthenticationToken extends AbstractAuthenticationToken {

    private String accessToken;
    private String email;

    @Getter
    private Date expiredDate;

    /**
     * init constructor
     * @param accessToken access token
     */
    public AuthenticationToken(final String accessToken){
        super(null);
        this.accessToken = accessToken;
        super.setAuthenticated(false);
    }

    /**
     * init constructor
     * @param email
     * @param expiredDate expired date
     * @param authorities authorities
     */
    public AuthenticationToken(final String email,
                               final Date expiredDate,
                               final Collection<? extends GrantedAuthority> authorities){
        super(authorities);
        this.getCredentials();
        this.email = email;
        this.expiredDate = expiredDate;
        super.setAuthenticated(false);
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated){
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return accessToken;
    }

    @Override
    public Object getPrincipal() {
        return email;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.accessToken = null;
    }
}
