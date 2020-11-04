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

@Data
public class RequestCaregoryCreate {
    private String name;

    private StatusEnum status;
}
