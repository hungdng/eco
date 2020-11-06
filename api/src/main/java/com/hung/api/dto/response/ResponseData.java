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
import com.hung.data.enums.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> {
    private String code;
    private String message;
    private T data;

    /**
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> success() {
        return new ResponseData<T>(ResponseStatus.SUCCESS.getValue(), ResponseStatus.SUCCESS.getName(), null);
    }

    /**
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> success(T data) {
        return new ResponseData<T>(ResponseStatus.SUCCESS.getValue(), ResponseStatus.SUCCESS.getName(), data);
    }

    /**
     *
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> success(T data, String message) {
        return new ResponseData<T>(ResponseStatus.SUCCESS.getValue(), message, data);
    }

    /**
     *
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> failed(CodeEnum errorCode) {
        return new ResponseData<T>(errorCode.getValue(), errorCode.getName(), null);
    }

    /**
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> failed(String code, String message) {
        return new ResponseData<T>(code, message, null);
    }

    /**
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> failed(String message) {
        return new ResponseData<T>(ResponseStatus.FAILED.getValue(), message, null);
    }

    /**
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> failed() {
        return failed(ResponseStatus.FAILED);
    }

    /**
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> validateFailed() {
        return failed(ResponseStatus.VALIDATION_ERROR);
    }

    /**
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> validateFailed(String message) {
        return new ResponseData<T>(ResponseStatus.VALIDATION_ERROR.getValue(), message, null);
    }

    /**
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> unauthorized(T data) {
        return new ResponseData<T>(ResponseStatus.UNAUTHORIZED.getValue(), ResponseStatus.UNAUTHORIZED.getName(), data);
    }

    /**
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> forbidden(T data) {
        return new ResponseData<T>(ResponseStatus.FORBIDDEN.getValue(), ResponseStatus.FORBIDDEN.getName(), data);
    }
}
