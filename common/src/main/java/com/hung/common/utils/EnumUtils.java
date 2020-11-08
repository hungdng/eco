/**
 * ****************************************************
 * * Description :
 * * File        : EnumUtils.java
 * * Author      : HUNG
 * * Date        : Nov 07, 2020
 * ****************************************************
 **/
package com.hung.common.utils;

import com.hung.data.enums.CodeEnum;

public class EnumUtils {

    /**
     * check enum is valid or not.
     *
     * @param enumClass enum class
     * @param target value
     * @return string
     */
    public static boolean isValidEnum(final Class<? extends CodeEnum> enumClass, final String target) {
        final CodeEnum[] enumValues = enumClass.getEnumConstants();

        boolean result = false;
        for (final CodeEnum codeEnum : enumValues) {
            if (StringUtils.equals(target, codeEnum.getValue())){
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * check enum is valid or not.
     *
     * @param enumClass enum class
     * @param targer value
     * @return boolean
     */
    public static CodeEnum getEnum(final Class<? extends CodeEnum> enumClass, final String targer){
        final CodeEnum[] enumValues = enumClass.getEnumConstants();

        for (final CodeEnum codeEnum: enumValues){
            if (StringUtils.equals(targer, codeEnum.getValue())){
                return codeEnum;
            }
        }
        return null;
    }
}
