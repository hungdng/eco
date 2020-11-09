/**
 * ****************************************************
 * * Description :
 * * File        : ApiResponse.java
 * * Author      : hung.tran
 * * Date        : Nov 04, 2020
 * ****************************************************
 **/
package com.hung.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hung.api.dto.response.common.PartError;
import com.hung.common.enums.ECOResponseStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder({"statusCode","message","data","validationError"})
public class ApiResponse<T> {
    @JsonProperty("code")
    private ECOResponseStatus statusCode;
    private String message;
    private T data;

    private List<PartError> errors;

    /**
     *
     * @param statusCode
     * @param message
     * @param data
     * @param errors
     */
    @Builder
    public ApiResponse(final ECOResponseStatus statusCode,
                       final String message,
                       final T data,
                       final List<PartError> errors) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }
}
