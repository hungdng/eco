/**
 * ****************************************************
 * * Description :
 * * File        : GlobalExceptionHandler.java
 * * Author      : hung.tran
 * * Date        : Nov 06, 2020
 * ****************************************************
 **/
package com.hung.api.component.handler;

import com.hung.api.component.support.ResponseSupport;
import com.hung.api.dto.response.common.PartError;
import com.hung.common.enums.ResponseStatus;
import com.hung.common.utils.StringUtils;
import com.hung.data.enums.MessageCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String DOT_CHARACTER = ".";

    private static final String BUSINESS_FAILURE_MESSAGE = ResponseStatus.BUSINESS_FAILURE.getName();

    @Autowired
    @Qualifier("validationMessageSource")
    private MessageSource messageSource;

    @Autowired
    private ResponseSupport responseSupport;

    /**
     * process response for SystemException
     * @param exception
     * @return
     */
    @ExceptionHandler({Exception.class, RuntimeException.class})
    protected ResponseEntity<Object> handleInternalError(final Exception exception){
        log.error(exception.getMessage(), exception);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(responseSupport.error(ResponseStatus.SYSTEM_FAILURE, MessageCode.SYSTEM_FAILURE_MESSAGE));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException exception,
                                                                         final HttpHeaders headers,
                                                                         final HttpStatus status,
                                                                         final WebRequest request){
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(responseSupport.error(ResponseStatus.METHOD_FAILURE, exception.getMessage()));
    }

    protected ResponseEntity<Object> handleParameter(final ConstraintViolationException exception){
        log.error(exception.getMessage(), exception);

        final Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();

        final List<PartError> errors = new ArrayList<>();
        for (final ConstraintViolation violation: violations) {
            final String fieldError = ((PathImpl) violation.getPropertyPath())
                                                            .getLeafNode()
                                                            .getName();
            final String errorKey = StringUtils.removeEnd(
                    StringUtils.removeStart(violation.getMessageTemplate(), "{"), "}");

//            final String errorCode get
        }
        return null;
    }

    private String getErrorCode(final String errorkey){
        Class<?> clazz = null;
        String code = null;
        final String shortName = StringUtils.capitalize(errorkey);
        try {
            clazz = Class.forName()
        }
    }


}
