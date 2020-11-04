/**
 * ****************************************************
 * * Description :
 * * File        : Charset.java
 * * Author      : hung.tran
 * * Date        : Nov 04, 2020
 * ****************************************************
 **/
package com.hung.common.enums;

import com.hung.data.enums.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Charset implements CodeEnum {

    WINDOWS_31J("Windows-31J", "")
    , WINDOWS_ISO_2022_1("X-WINDOWS-ISO2022JP", "")
    , SHIFT_JIS("Shift_JIS", "")
    , EUC_JP("EUC-JP", "")
    , ISO_2022_1("ISO-2022-JP", "")
    , UTF_8("UTF-8", "")
    , MS932("MS932", "")
    ;
    private final String value;
    private final String name;
}
