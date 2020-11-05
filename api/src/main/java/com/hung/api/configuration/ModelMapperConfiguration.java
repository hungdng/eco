/**
 * ****************************************************
 * * Description :
 * * File        : ModelMapperConfiguration.java
 * * Author      : hung.tran
 * * Date        : Nov 04, 2020
 * ****************************************************
 **/
package com.hung.api.configuration;

import com.hung.common.enums.DateTimeFormat;
import com.hung.common.utils.DateUtils;
import com.hung.common.utils.StringUtils;
import com.hung.data.enums.CodeEnum;
import org.apache.commons.lang3.EnumUtils;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Configuration
public class ModelMapperConfiguration {

    /**
     * Custom ModelMapper process
     *
     * @return ModelMapper
     */
    @Bean
    protected ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        final List<ConditionalConverter<?, ?>> converters = modelMapper.getConfiguration().getConverters();

//        modelMapper.addConverter(convertStringToLocalDate());
//        modelMapper.addConverter(convertLocalDateToString());
//        modelMapper.addConverter(convertStringToLocalDateTime());
//        modelMapper.addConverter(convertLocalDateTimeToString());
//        modelMapper.addConverter(convertStringToTimeStamp());
//        modelMapper.addConverter(convertTimeStampToString());
        modelMapper.addConverter(trimString());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        converters.add(0, objectToEnumConverter());

        return modelMapper;
    }

    /**
     * String to LocalDate Converter
     *
     * @return AbstractConverter
     */
    private AbstractConverter<String, LocalDate> convertStringToLocalDate() {

        return new AbstractConverter<>() {

            @Override
            protected LocalDate convert(final String source) {

                if (StringUtils.isEmpty(source)) {
                    return null;
                }
                return DateUtils.convertStringToLocalDate(source, DateTimeFormat.SLASH_YYYYMMDD);
            }
        };
    }

    /**
     * LocalDate to String Converter
     *
     * @return AbstractConverter
     */
    private AbstractConverter<LocalDate, String> convertLocalDateToString() {
        return new AbstractConverter<LocalDate, String>() {

            @Override
            protected String convert(final LocalDate source) {
                return DateUtils.convertDateToString(source, DateTimeFormat.SLASH_YYYYMMDD);
            }
        };
    }

    /**
     * String to LocalDateTime Converter
     *
     * @return AbstractConverter
     */
    private AbstractConverter<String, LocalDateTime> convertStringToLocalDateTime() {

        return new AbstractConverter<>() {

            @Override
            protected LocalDateTime convert(final String source) {

                if (StringUtils.isEmpty(source)) {
                    return null;
                }
                return DateUtils.convertStringToLocalDateTime(source, DateTimeFormat.SLASH_YYYY_MM_DD_HH_MM_SS);
            }
        };
    }

    /**
     * LocalDateTime to String Converter
     *
     * @return AbstractConverter
     */
    private AbstractConverter<LocalDateTime, String> convertLocalDateTimeToString() {
        System.out.println("convertLocalDateTimeToString");
        return new AbstractConverter<LocalDateTime, String>() {

            @Override
            protected String convert(final LocalDateTime source) {
                return DateUtils.convertDateToString(source, DateTimeFormat.SLASH_YYYY_MM_DD_HH_MM_SS);
            }
        };
    }

    /**
     * String to Date Converter
     *
     * @return AbstractConverter
     */
    private AbstractConverter<String, Date> convertStringToTimeStamp() {
        return new AbstractConverter<String, Date>() {

            @Override
            protected Date convert(final String source) {

                if(StringUtils.isEmpty(source)) {
                    return null;
                }
                return DateUtils.convertStringToDate(source, DateTimeFormat.SLASH_YYYY_MM_DD_HH_MM_SS);
            }
        };
    }

    /**
     * String to Date Converter
     *
     * @return AbstractConverter
     */
    private AbstractConverter<Date, String> convertTimeStampToString() {
        return new AbstractConverter<Date, String>() {

            @Override
            protected String convert(final Date source) {

                if(source == null) {
                    return null;
                }
                return DateUtils.convertDateToString(source, DateTimeFormat.SLASH_YYYY_MM_DD_HH_MM_SS);
            }
        };
    }

    /**
     * Object to CodeEnum Converter
     *
     * @return ConditionalConverter
     */
    private ConditionalConverter<String, Enum<? extends CodeEnum>> objectToEnumConverter() {
        return new ConditionalConverter<String, Enum<? extends CodeEnum>>() {
            @SuppressWarnings({ "unchecked", "rawtypes" })
            public Enum<? extends CodeEnum> convert(
                    final MappingContext<String, Enum<? extends CodeEnum>> context) {

                final Object source = context.getSource();
                if (source == null) {
                    return null;
                }

                String value = null;
                if(source.getClass() == String.class) {

                    value =(String) source;
                }else {

                    value =((CodeEnum)source).getValue();
                }


                if (value != null) {
                    final Class clazz = context.getDestinationType();
                    return (Enum) EnumUtils.getEnum(clazz, value);

                }
                return null;
            }

            public MatchResult match(final Class<?> sourceType, final Class<?> destinationType) {

                MatchResult matchResult = MatchResult.NONE;

                if(destinationType.isEnum()&& sourceType == String.class) {
                    matchResult = MatchResult.FULL;
                }
                return matchResult;
            }
        };
    }

    /**
     * String trim.
     *
     * @return AbstractConverter
     */
    private AbstractConverter<String, String> trimString(){
        return new AbstractConverter<String, String>() {

            @Override
            protected String convert(final String source) {
                return StringUtils.trim(source);
            }
        };
    }
}
