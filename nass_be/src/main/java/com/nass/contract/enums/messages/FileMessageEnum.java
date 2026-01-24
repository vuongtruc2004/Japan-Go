package com.nass.contract.enums.messages;

public enum FileMessageEnum {

    FILE_EMPTY("file.empty"),
    FILE_ERROR("file.error"),
    FILE_NOT_EXCEL("file.not.excel"),
    FILE_NOT_XML("file.not.xml"),
    FILE_NOT_JSON("file.not.json"),
    FILE_ERROR_AT_LINE("file.error.at.line"),
    FILE_ERROR_FORMAT("file.error.format");

    public final String key;

    FileMessageEnum(String key) {
        this.key = key;
    }
}
