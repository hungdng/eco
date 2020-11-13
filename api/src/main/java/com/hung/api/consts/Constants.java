/**
 * ****************************************************
 * * Description :
 * * File        : Constants.java
 * * Author      : hung.tran
 * * Date        : Nov 12, 2020
 * ****************************************************
 **/
package com.hung.api.consts;

public final class Constants {
    private Constants() {
        throw new IllegalStateException("Constants class");
    }

    /** Comma character */
    public static final String ROOT_URL = "/";
    public static final String TOKEN_BASED_AUTH_ENTRU_POINT = ROOT_URL + "**";
    public static final String IDV_NUM ="01";

    public static final int NOT_FOUND = -1;
    public static final int VALIDATE = -2;
    public final static String BLANK = "";

    public final static String HALF_SPACE = " ";
    public static final String MAX_DATE = "999999999";
    public static final String LIMIT_DATE = "9999/12/31";
}
