/**
 * ****************************************************
 * * Description :
 * * File        : LoginRequest.java
 * * Author      : hung.tran
 * * Date        : Nov 13, 2020
 * ****************************************************
 **/
package com.hung.api.dto.request.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
