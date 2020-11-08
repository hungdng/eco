/**
 * ****************************************************
 * * Description :
 * * File        : GlobalExceptionHandler.java
 * * Author      : hung.tran
 * * Date        : Nov 06, 2020
 * ****************************************************
 **/
package com.hung.api.component.handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hung.api.component.support.ResponseSupport;
import com.hung.api.dto.response.common.PartError;
import com.hung.common.annotation.ErrorCode;
import com.hung.common.annotation.FieldName;
import com.hung.common.enums.ResponseStatus;
import com.hung.common.exceptions.SystemException;
import com.hung.common.utils.StringUtils;
import com.hung.data.enums.MessageCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyAccessorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;
import java.util.*;

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

    /**
     * handle error validation of the parameter
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleParameter(final ConstraintViolationException exception) {
        log.error(exception.getMessage(), exception);

        final Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();

        final List<PartError> errors = new ArrayList<>();
        for (final ConstraintViolation violation: violations) {
            final String fieldError = ((PathImpl) violation.getPropertyPath())
                                                            .getLeafNode()
                                                            .getName();
            final String errorKey = StringUtils.removeEnd(
                    StringUtils.removeStart(violation.getMessageTemplate(), "{"), "}");

            final String errorCode  = getErrorCode(errorKey);

            final PartError error = PartError.builder()
                    .errorCode(errorCode)
                    .errorItem(fieldError)
                    .message(messageSource.getMessage(errorKey,
                            new Object[]{fieldError}, Locale.getDefault()))
                    .build();
            errors.add(error);
        }
        return ResponseEntity.ok(responseSupport.error(errors));
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException exception,
                                                         HttpHeaders headers,
                                                         HttpStatus status,
                                                         WebRequest request) {
        log.error(exception.getMessage(), exception);
        return handleBindingResult(exception, exception.getBindingResult(), headers, request);
    }

    /**
     * handler response error entity
     *
     * @param exception
     * @param bindingResult
     * @param headers
     * @param request
     * @return
     */
    private ResponseEntity<Object> handleBindingResult(final Exception exception,
                                                       final BindingResult bindingResult,
                                                       final HttpHeaders headers,
                                                       final WebRequest request){
        final BeanWrapperImpl targetWrapper = new BeanWrapperImpl(bindingResult.getTarget());
        final List<PartError> errors = new ArrayList<>();

        final List<FieldError> fieldErrors =bindingResult.getFieldErrors();
        for (final FieldError fieldError : fieldErrors){
            Class<? extends Object>clazz =bindingResult.getTarget().getClass();
            try {
                final String itemName = getPropertyShortName(targetWrapper, fieldError.getField());
                final String code  = getErrorCode(fieldError.getCode());

                final Field field = clazz.getDeclaredField(fieldError.getField());
                final FieldName fieldNameAnnotation = field.getAnnotation(FieldName.class);
                String fieldName = itemName;

                if(fieldNameAnnotation != null) {
                    fieldName = fieldNameAnnotation.value();
                }
                final PartError error = PartError
                                        .builder()
                                        .errorItem(itemName)
                                        .errorCode(code)
                                        .message(messageSource.getMessage(null,
                                                new Object[] {fieldName},
                                                fieldError.getDefaultMessage(),
                                                Locale.getDefault()))
                                        .build();
                errors.add(error);
            } catch (NoSuchFieldException e) {
                log.error(e.toString());
                e.printStackTrace();
            }catch (SecurityException e) {
                log.error(e.toString());
                e.printStackTrace();
            }
        }
        return handleExceptionInternal(exception,
                responseSupport.error(errors),
                headers,
                HttpStatus.OK,
                request);
    }

    /**
     * Get property of front-end as short name
     *
     * @param targetWrapper
     * @param fullname
     * @return
     */
    private String getPropertyShortName(final BeanWrapperImpl targetWrapper,
                                        final String fullname) {

        final StringJoiner result = new StringJoiner(DOT_CHARACTER);

        final StringJoiner stringJoiner = new StringJoiner(DOT_CHARACTER);
        final StringTokenizer stringTokenizer = new StringTokenizer(fullname, DOT_CHARACTER);

        while (stringTokenizer.hasMoreElements()) {

            final String propertyName = stringTokenizer.nextToken();

            stringJoiner.add(propertyName);

            final TypeDescriptor typeDescriptor = targetWrapper.getPropertyTypeDescriptor(
                    stringJoiner.toString());

            final JsonProperty jsonProperty = typeDescriptor.getAnnotation(JsonProperty.class);
            final String jsonPropertyValue = (String) AnnotationUtils.getValue(jsonProperty);

            if (jsonPropertyValue == null) {

                result.add(propertyName);
            } else {

                final String fullPropertyName = PropertyAccessorUtils.getPropertyName(propertyName);
                result.add(propertyName.replaceFirst(fullPropertyName, jsonPropertyValue));
            }
        }

        return result.toString();
    }

    /**
     * get error code.
     * @param errorKey key of message in validationMessage.properties
     * @return String
     */
    private String getErrorCode(final String errorKey){
        Class<?> clazz = null;
        String code = null;
        final String shortName = StringUtils.capitalize(errorKey);
        try {
            clazz = Class.forName("com.hung.common.validation.constraints."+ shortName);
        } catch (ClassNotFoundException e1){
            try {
                clazz = Class.forName("com.hung.common.validation.constraints."+ shortName);
            } catch (ClassNotFoundException e2){
                throw new SystemException(e1.getMessage());
            }
        }
        if (clazz.isAnnotationPresent(ErrorCode.class)){
            final ErrorCode errorCode = clazz.getAnnotation(ErrorCode.class);
            code =errorCode.value();
        }
        return code;
    }


}
