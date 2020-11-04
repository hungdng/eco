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
import lombok.Data;

@Data
@JsonPropertyOrder({"error_item","error_code","error_message"})
public class PartError {
    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("error_item")
    private String errorItem;

    @JsonProperty("error_message")
    private String message;
}
