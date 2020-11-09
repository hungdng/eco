/**
 * ****************************************************
 * * Description :
 * * File        : RequestCaregoryCreate.java
 * * Author      : hung.tran
 * * Date        : Nov 04, 2020
 * ****************************************************
 **/
package com.hung.api.dto.request.category;

import com.hung.common.validation.constraints.MaxLengthCheck;
import com.hung.common.validation.constraints.RequireCheck;
import com.hung.data.enums.StatusEnum;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RequestCaregoryCreate {

    @RequireCheck
    @MaxLengthCheck(max = 3)
    private String name;

//    @NotBlank(message = "Status is mandatory")
    private StatusEnum status;
}
