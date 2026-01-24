package com.nass.contract.enums.messages;

public enum DefaultMessageEnum {
    DEFAULT_ERROR("default.error"),
    DEFAULT_CREATE_BY("default.created.by");

    public final String key;

    DefaultMessageEnum(String key) {
        this.key = key;
    }
}
