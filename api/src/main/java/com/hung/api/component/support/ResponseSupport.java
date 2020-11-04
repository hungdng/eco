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
import com.hung.common.enums.DateTimeFormat;
import com.hung.common.enums.ResponseStatus;
import com.hung.common.utils.DateUtils;
import com.hung.data.enums.MessageCode;
import com.hung.data.support.DateSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class ResponseSupport {
    private static final String SUCCESS_MESSAGE = ResponseStatus.SUCCESS.getName();
    private static final String VALIDATION_MESSAGE = ResponseStatus.VALIDATION_ERROR.getName();

    @Autowired
    private DateSupport dateSupport;

    @Autowired
    @Qualifier("validationMessageSource")
    private MessageSource messageSource;

    /**
     * return success with data.
     *
     * @param data response data
     * @param messageCode
     * @return response object
     */
    public ApiResponse<?> fetchSuccess(final Object data , MessageCode messageCode){
        return fetchNormal(ResponseStatus.SUCCESS,messageCode, data);
    }

    /**
     * return success.
     *
     * @param code status code
     * @param messageCode status message
     * @param data response data
     * @return response object
     */
    public ApiResponse<?> fetchNormal(final ResponseStatus code,
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
    private ApiResponse<?> response(final ResponseStatus code,
                                    final String message,
                                    final Object data,
                                    final List<PartError> errors){
        return ApiResponse.builder()
                .timestamp(
                        DateUtils.convertDateTimeToString(
                                dateSupport.getLocalDateTime(),
                                DateTimeFormat.SLASH_YYYY_MM_DD_HH_MM_SS))
                .statusCode(code)
                .message(message)
                .data(data)
                .errors(errors)
                .build();
    }

}
