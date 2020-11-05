/**
 * ****************************************************
 * * Description :
 * * File        : ResponseData.java
 * * Author      : hung.tran
 * * Date        : Nov 05, 2020
 * ****************************************************
 **/
package com.hung.api.dto.response;

import com.hung.common.enums.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> {
    private String code;
    private String message;
    private T data;

    public static <T> ResponseData<T> success() {
        return new ResponseData<T>(ResponseStatus.SUCCESS.getValue(), ResponseStatus.SUCCESS.getName(), null);
    }

    public static <T> ResponseData<T> success(T data) {
        return new ResponseData<T>(ResponseStatus.SUCCESS.getValue(), ResponseStatus.SUCCESS.getName(), data);
    }

    public static <T> ResponseData<T> success(T data, String message) {
        return new ResponseData<T>(ResponseStatus.SUCCESS.getValue(), message, data);
    }

    public static <T> ResponseData<T> failed(IErrorCode errorCode) {
        return new ResponseData<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static <T> ResponseData<T> failed(int code, String message) {
        return new ResponseData<T>(code, message, null);
    }
}
