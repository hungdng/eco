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
import com.hung.common.enums.ResponseStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder({"timestamp","statusCode","message","data","validation_error"})
public class ApiResponse<T> {
    private String timestamp;

    @JsonProperty("code")
    private ResponseStatus statusCode;
    private String message;
    private T data;

    @JsonProperty("validation_error")
    private List<PartError> errors;

    /**
     *
     * @param timestamp
     * @param statusCode
     * @param message
     * @param data
     * @param errors
     */
    @Builder
    public ApiResponse(final String timestamp,
                       final ResponseStatus statusCode,
                       final String message,
                       final T data,
                       final List<PartError> errors) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }
}
