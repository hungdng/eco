/**
 * ****************************************************
 * * Description :
 * * File        : ValidationSupport.java
 * * Author      : HUNG
 * * Date        : Nov 07, 2020
 * ****************************************************
 **/
package com.hung.common.component.support;

import com.hung.common.utils.EnumUtils;
import com.hung.common.utils.StringUtils;
import com.hung.data.enums.CodeEnum;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidationSupport {

    public static final Pattern PATTERN_NUMBERIC = Pattern.compile("[\\p{Digit}]*");

    /**
     * check half size numeric.
     *
     * @param target
     *            value
     * @return true if target is only contain half size numeric
     */
    public boolean isNumeric(final String target){
        return check(target, PATTERN_NUMBERIC);
    }

    /**
     * check enum is valid or not.
     *
     * @param enumClass
     *            enum class
     * @param target
     *            value of enum
     * @return true if enum is valid
     */
    public boolean isValidEnum(final Class<? extends CodeEnum> enumClass, final String target){
        return EnumUtils.isValidEnum(enumClass, target);
    }

    /**
     * required check.
     *
     * @param target
     *            value
     * @return true if target is blank
     */
    public boolean isBlank(final String target){
        return com.hung.common.utils.StringUtils.isBlank(target);
    }


    /**
     * check pattern.
     * @param target
     *             value
     * @param patterns pattern check
     *
     * @return boolean
     */
    private boolean check(final String target, final Pattern... patterns){
        Assert.notNull(target, "value must not be null");
        String temp = target;
        for (final Pattern pattern: patterns) {
            final Matcher matcher = pattern.matcher(temp);
            temp = matcher.replaceAll("");
        }
        return StringUtils.isEmpty(temp);
    }
}
