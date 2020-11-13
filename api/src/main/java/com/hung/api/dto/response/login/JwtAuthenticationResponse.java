/**
 * ****************************************************
 * * Description :
 * * File        : JwtAuthenticationResponse.java
 * * Author      : hung.tran
 * * Date        : Nov 13, 2020
 * ****************************************************
 **/
package com.hung.api.dto.response.login;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
