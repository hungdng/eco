/**
 * ****************************************************
 * * Description :
 * * File        : StatusEnum.java
 * * Author      : hung.tran
 * * Date        : Nov 03, 2020
 * ****************************************************
 **/
package com.hung.eco.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum implements CodeEnum {
    ACTIVE("1", "Active"),
    UNACTIVE("0", "Unactive")
    ;
    private String value;
    private String name;

    public String getStatus(Integer code) {

        for(StatusEnum statusEnum : StatusEnum.values()) {
            if(statusEnum.getValue().equals(code)) return statusEnum.getName();
        }
        return "";
    }
}
