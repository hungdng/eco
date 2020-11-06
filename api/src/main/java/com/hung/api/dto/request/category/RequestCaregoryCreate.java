/**
 * ****************************************************
 * * Description :
 * * File        : RequestCaregoryCreate.java
 * * Author      : hung.tran
 * * Date        : Nov 04, 2020
 * ****************************************************
 **/
package com.hung.api.dto.request.category;

import com.hung.data.enums.StatusEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class RequestCaregoryCreate {

    @NotEmpty(message = "Name is mandatory")
    private String name;

//    @NotBlank(message = "Status is mandatory")
    private StatusEnum status;
}
