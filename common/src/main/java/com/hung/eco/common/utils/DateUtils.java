/**
 * ****************************************************
 * * Description :
 * * File        : DateUtils.java
 * * Author      : hung.tran
 * * Date        : Nov 03, 2020
 * ****************************************************
 **/
package com.hung.eco.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Calendar;
import java.util.Date;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    /**
     * check date format.
     *
     * @param target
     *            value
     * @param pattern
     *            date time pattern
     * @return true if date is valid, else return false
     */
    public static boolean isDate(final String target, final DateTimeFormat pattern) {
        return isDate(target, pattern, false);
    }

    /**
     * check date format.
     *
     * @param target
     *            value
     * @param pattern
     *            date time pattern
     * @param strict
     *            specify date is matching
     * @return true if date is valid, else return false
     */
    public static boolean isDate(final String target,
                                 final DateTimeFormat pattern,
                                 final boolean strict) {

        boolean ret = true;

        if (pattern == null) {
            throw new SystemException("");
        }

        if (StringUtils.isNotBlank(target)) {
            try {
                final DateFormat dateFormat = new SimpleDateFormat(pattern.getValue());
                final Date date = dateFormat.parse(target);

                String value;
                if (strict && (value = dateFormat.format(date)) != null
                        && !target.equals(value)) {
                    ret = false;

                }
            } catch (final ParseException e) {

                ret = false;
            }
        }

        return ret;
    }

    /**
     * check the consistency of the time string.
     *
     * @param target
     *            value
     * @param timeFormat
     *            date time pattern
     * @return true if time is valid else return false
     */
    public static boolean isTime(final String target, final DateTimeFormat timeFormat) {
        boolean ret = true;

        if (timeFormat == null) {
            throw new SystemException("");
        }

        if (StringUtils.isNotBlank(target)) {
            DateTimeFormatter timeFormatter;
            try {
                timeFormatter = DateTimeFormatter.ofPattern(timeFormat.getValue())
                        .withResolverStyle(ResolverStyle.LENIENT);
            } catch (final Exception e) {
                throw new SystemException("", e);
            }
            try {
                LocalTime.parse(target, timeFormatter);
            } catch (final DateTimeParseException e) {
                ret = false;
            }
        }
        return ret;
    }

    /**
     * convert LocalDateTime object to String.
     *
     * @param target
     *            dateTime : LocalDateTime object to convert
     * @param pattern
     *            pattern : time format of target character string
     * @return string
     */
    public static String convertDateTimeToString(final LocalDateTime target,
                                                 final DateTimeFormat pattern) {
        String result;
        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern);

        try {
            result = target.format(dateTimeFormatter);
        } catch (final DateTimeException e) {
            throw new SystemException("",e);
        }

        return result;
    }

    /**
     * convert LocalDate object to String.
     *
     * @param target
     *            value
     * @param pattern
     *            date time pattern
     * @return string
     */
    public static String convertDateToString(final LocalDate target, final DateTimeFormat pattern) {
        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern);
        String result;
        try {
            result = target.format(dateTimeFormatter);
        } catch (final DateTimeException e) {
            throw new SystemException(e);
        }

        return result;
    }

    private static DateTimeFormatter getDateTimeFormatter(DateTimeFormat pattern) {
        if (pattern == null) throw new SystemException("");

        DateTimeFormatter dateTimeFormatter;
        try {
            dateTimeFormatter = DateTimeFormatter.ofPattern(pattern.getValue());
        } catch (final Exception e) {
            throw new SystemException("", e);
        }
        return dateTimeFormatter;
    }

    /**
     * convert String object to LocalDate.
     *
     * @param target
     *            value
     * @param pattern
     *            date time pattern
     * @return string
     */
    public static LocalDate convertStringToLocalDate(final String target,
                                                     final DateTimeFormat pattern) {
        LocalDate localDate;
        if (pattern == null) {
            throw new SystemException("");
        }

        DateTimeFormatter dateTimeFormatter;
        try {
            dateTimeFormatter = DateTimeFormatter.ofPattern(pattern.getValue());
        } catch (final Exception e) {
            throw new SystemException("", e);
        }

        try {
            localDate = LocalDate.parse(target, dateTimeFormatter);
        } catch (final DateTimeParseException e) {
            throw new SystemException(e);
        }

        return localDate;
    }

    /**
     * convert String object to LocalDateTime.
     *
     * @param target
     *            value
     * @param pattern
     *            date time pattern
     * @return string
     */
    public static LocalDateTime convertStringToDateTime(final String target, final DateTimeFormat pattern) {
        LocalDateTime localDate;
        if (pattern == null) {
            throw new SystemException("");
        }

        DateTimeFormatter dateTimeFormatter;
        try {
            dateTimeFormatter = DateTimeFormatter.ofPattern(pattern.getValue());
        } catch (final Exception e) {
            throw new SystemException("", e);
        }

        try {
            localDate = LocalDateTime.parse(target, dateTimeFormatter);
        } catch (final DateTimeParseException e) {
            throw new SystemException(e);
        }

        return localDate;
    }

    /**
     * convert String to java.util.Date
     *
     * @param target value
     * @param pattern date time pattern
     * @return string
     */
    public static Date convertStringToDate(final String target, final DateTimeFormat pattern) {
        if (StringUtils.isBlank(target)) {
            return null;
        }

        Date result;
        try {
            result = new SimpleDateFormat(pattern.getValue()).parse(target);
        } catch (final ParseException e) {
            throw new SystemException("");
        }

        return result;
    }

    /**
     * get last date of month.
     *
     * @param target value
     * @param frmPattern from pattern
     * @param toPattern to pattern
     * @return string
     */
    public static String getLastDateOfMonth(
            final String target, final DateTimeFormat frmPattern, final DateTimeFormat toPattern) {

        final Date date = convertStringToDate(target, frmPattern);

        if (date == null) {

            return null;
        }

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));

        return convertDateToString(calendar.getTime(), toPattern);
    }

    /**
     * convert date to string.
     *
     * @param target
     *            value
     * @param pattern
     *            date time pattern
     * @return string
     */
    public static String convertDateToString(final Date target, final DateTimeFormat pattern) {

        return new SimpleDateFormat(pattern.getValue()).format(target.getTime());
    }

    /**
     * convert date time format.
     *
     * @param target
     *            value
     * @param frmPattern
     *            from pattern
     * @param toPattern
     *            to pattern
     * @return string
     */
    public static String changeDateTimeFormat(final String target, final DateTimeFormat frmPattern,
                                              final DateTimeFormat toPattern) {

        final Date date = convertStringToDate(target, frmPattern);

        if (date == null) {

            return null;
        }

        return convertDateToString(date, toPattern);
    }

    /**
     * convert date to local date.
     *
     * @param date date
     * @return string
     */
    public static LocalDate convertLocalDate(Date date) {
        if(date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * compare date.
     *
     * @param fromDate from date
     * @param toDate to date
     * @return boolean
     */
    public static boolean compareDate(final String fromDate,final String toDate) {
        return compareDate(convertStringToLocalDate(fromDate, DateTimeFormat.SLASH_YYYYMMDD),
                convertStringToLocalDate(toDate, DateTimeFormat.SLASH_YYYYMMDD));
    }

    /**
     * compare date.
     *
     * @param fromDate from date
     * @param toDate to date
     * @return boolean
     */

    public static boolean compareDate(final LocalDate fromDate ,final LocalDate toDate ) {
        return ((fromDate.compareTo(toDate)< 0) || (fromDate.compareTo(toDate) == 0));
    }
}
