package com.telecom.shop.config.enumcom;

/**
 * @author admin
 * 全局静态属性
 */

public enum GlobalAttribute {
    WxToken("wx_token_"),
    PhoneToken("wx_phone_"),
    UserIdToken("wx_userId_");

    private String value;
    GlobalAttribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
