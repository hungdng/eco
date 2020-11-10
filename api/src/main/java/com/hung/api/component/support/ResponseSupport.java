/**
 * ****************************************************
 * * Description :
 * * File        : ResponseSupport.java
 * * Author      : hung.tran
 * * Date        : Nov 04, 2020
 * ****************************************************
 **/
package com.hung.api.component.support;

import com.hung.api.dto.response.ApiResponse;
import com.hung.api.dto.response.common.PartError;
import com.hung.common.enums.ECOResponseStatus;
import com.hung.data.enums.MessageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class ResponseSupport {
    private static final String SUCCESS_MESSAGE = ECOResponseStatus.SUCCESS.getName();
    private static final String VALIDATION_MESSAGE = ECOResponseStatus.VALIDATION_ERROR.getName();

    @Autowired
    @Qualifier("validationMessageSource")
    private MessageSource messageSource;

    /**
     * return success with data.
     *
     * @return response object
     *
     */
    public ResponseEntity<?> create(Object data){
        return ResponseEntity.created(null).body(
                response(ECOResponseStatus.SUCCESS,
                        messageSource.getMessage(MessageCode.SUCCESS_CREATE.getValue(),null,Locale.getDefault()),
                        data,
                        null));
    }

    /**
     * return success with data.
     *
     * @param data response data
     * @param messageCode
     * @return response object
     */
    public ApiResponse<?> success(final Object data , final MessageCode messageCode){
        return fetchNormal(ECOResponseStatus.SUCCESS, messageCode, data);
    }

    /**
     * return success with message, without data.
     *
     * @param messageCode messageCode
     * @return response object
     */
    public ApiResponse<?> success(final MessageCode messageCode){
        return fetchNormal(ECOResponseStatus.SUCCESS, messageCode, null);
    }

    /**
     * return empty data.
     *
     * @param messageCode messageCode of response
     * @return response object
     */
    public ApiResponse<?> empty(final MessageCode messageCode){
        return fetchNormal(ECOResponseStatus.SUCCESS, messageCode, null);
    }

    /**
     * return failure with message code enum.
     *
     * @param statusCode status of response
     * @param messageCode messageCode of response
     * @return response object
     */
    public ApiResponse<?> error(final ECOResponseStatus statusCode, final MessageCode messageCode){
        String message = messageSource.getMessage(messageCode.getValue(),null, Locale.getDefault());
        return response(statusCode, message, null, null);
    }

    /**
     * return failure with message string.
     *
     * @param statusCode status of response
     * @param message messageCode of response
     * @return response object
     */
    public ApiResponse<?> error(final ECOResponseStatus statusCode,
                                     final String message){
        return response(statusCode, message,
                null,
                null);
    }

    /**
     * return failure
     *
     * @param errors error list
     * @param errors object
     * @return
     */
    public ApiResponse<?> error(final List<PartError>errors){
        return response(ECOResponseStatus.VALIDATION_ERROR,
                VALIDATION_MESSAGE,
                null,
                errors);
    }

    /**
     * return success with status code,message and data.
     *
     * @param data response data
     * @param messageCode messageCode of response
     * @return response object
     */
    public ApiResponse<?> error(final Object data,
                                     final MessageCode messageCode){
        String message = messageSource.getMessage(messageCode.getValue(), null,Locale.getDefault());
        return response(ECOResponseStatus.ERROR, message, data, null);
    }
    
    /**
     * return success.
     *
     * @param code status code
     * @param messageCode status message
     * @param data response data
     * @return response object
     */
    public ApiResponse<?> fetchNormal(final ECOResponseStatus code,
                                      final MessageCode messageCode,
                                      final Object data){
        String message = messageSource.getMessage(messageCode.getValue(), null, Locale.getDefault());
        return response(code,message,data,null);
    }

    /**
     * return success.
     *
     * @param code status code
     * @param message status message
     * @param data response data
     * @return response object
     *
     */
    private ApiResponse<?> response(final ECOResponseStatus code,
                                    final String message,
                                    final Object data,
                                    final List<PartError> errors){
        return ApiResponse.builder()
                .statusCode(code)
                .message(message)
                .data(data)
                .errors(errors)
                .build();
    }

}
