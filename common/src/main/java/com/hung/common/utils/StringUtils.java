/**
 * ****************************************************
 * * Description :
 * * File        : StringUtils.java
 * * Author      : hung.tran
 * * Date        : Nov 04, 2020
 * ****************************************************
 **/
package com.hung.common.utils;

import com.hung.common.enums.Charset;
import com.hung.common.exceptions.SystemException;
import com.hung.data.enums.CodeEnum;

import java.util.Collection;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
    /**
     * get string with byte index.
     *
     * @param target value
     * @param from from index
     * @param to to index
     * @param charset charset
     * @return string
     */
    public static String byteSubstring(final String target,
                                       final int from,
                                       final int to,
                                       final Charset charset) {

        if (from < 0) {
            return null;
        }

        if (isEmpty(target)) {
            return "";
        }

        final StringBuilder strBuf = new StringBuilder();
        try {
            int iReadByte = 0;
            for (int i = 0; i < target.length(); i++) {
                final String strChar = String.valueOf(target.charAt(i));
                final byte[] bytChars = strChar.getBytes(charset.getValue());
                int iCharSize = 0;
                iCharSize = bytChars.length;
                if (iReadByte >= from) {
                    if ((iReadByte + iCharSize) <= to  || to == -1) {
                        strBuf.append(target.charAt(i));
                    } else {
                        break;
                    }
                }
                iReadByte = iReadByte + iCharSize;
            }
        } catch (final Exception e) {
            throw new SystemException(e);
        }
        return strBuf.toString();
    }

    /**
     * get byte of string.
     *
     * @param target value
     * @param charset charset
     * @return int
     */
    public static int getBytesLength(final String target, final Charset charset) {

        if (isEmpty(target)) {
            return 0;
        }

        try {

            return target.getBytes(charset.getValue()).length;
        } catch (final Exception e) {

            throw new SystemException("");
        }
    }


    /**
     * trim tab.
     *
     * @param target value
     * @return string
     */
    public static String trimWithTab(final String target) {
        return trimWithTab(target, true);
    }

    /**
     * trim tab.
     *
     * @param target value
     * @param nullToEmpty return empty if nullToEmpty is true, else return null
     * @return string
     */
    public static String trimWithTab(final String target, final boolean nullToEmpty) {
        if (target == null) {
            return (nullToEmpty ? "" : null);
        }

        return strip(target, " 　\t");
    }

    /**
     * convert list to array
     *
     * @param collection
     * @return
     *//*
    public static String[] toStringArrays(Collection<String> collection) {
        if (collection == null) {
            return null;
        }

        return collection.toArray(new String[collection.size()]);
    }

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String str) {
        if ((str != null) && !"".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    public static String formatLike(String str) {
        if (isNotEmpty(str)) {
            return "%" + str.toLowerCase().trim() + "%";
        } else {
            return null;
        }
    }*/

    /**
     * convert list to array
     *
     * @param collection
     * @return
     */
    public static String[] toStringArray(Collection<String> collection) {
        String[] result = null;
        if(collection != null) {
            result = new String [collection.size()];
            result = collection.toArray(result);
        }
        return result;
    }
    /**
     * compare enum and string
     *
     * @param enums CodeEnum
     * @param value String
     * @return boolean
     */
    public static boolean equals (final CodeEnum enums, final String value) {
        return equals(enums.getValue(), value);
    }
}
