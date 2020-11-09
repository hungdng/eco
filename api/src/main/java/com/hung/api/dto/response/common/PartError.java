/**
 * ****************************************************
 * * Description :
 * * File        : PartError.java
 * * Author      : hung.tran
 * * Date        : Nov 04, 2020
 * ****************************************************
 **/
package com.hung.api.dto.response.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({"item","message"})
public class PartError {

    @JsonProperty("item")
    private String errorItem;

    private String message;
}
