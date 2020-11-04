/**
 * ****************************************************
 * * Description :
 * * File        : ResponseCategory.java
 * * Author      : hung.tran
 * * Date        : Nov 04, 2020
 * ****************************************************
 **/
package com.hung.api.dto.response.category;

import com.hung.data.enums.StatusEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class ResponseCategoryList {
    private UUID id;

    private String name;

    private StatusEnum status;
}
