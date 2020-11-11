/**
 * ****************************************************
 * * Description :
 * * File        : AuthenticationSupport.java
 * * Author      : hung.tran
 * * Date        : Nov 10, 2020
 * ****************************************************
 **/
package com.hung.api.component.support;

import com.hung.data.entity.User;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthenticationSupport {

    /**
     * Get Bearer token
     * @param request
     * @return string token
     */
    public String getBearerToken(HttpServletRequest request) {
        String bearerToken = null;
        String authorization = request.getHeader("Authorization");
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            bearerToken = authorization.substring(7);
        }
        return bearerToken;
    }

    /**
     *  get current user
     * @return user
     */
    public User getCurrentUser() {
        User userPrincipal = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object principal = securityContext.getAuthentication().getPrincipal();
        if (principal instanceof User) {
            userPrincipal = ((User) principal);
        }
        return userPrincipal;
    }

}
