package com.nass.contract.enums.messages;

public enum SinoVietnameseMessageEnum {
    SINO_VIETNAMESE_IN_KANJI_NOT_FOUND("sino.vietnamese.in.kanji.not.found"),
    FOLDER_NAME_EXISTED("folder.name.existed"),
    FOLDER_CREATED("folder.created");

    // dùng cho devMessage và clientMessage ở controller
    public static final String KEY_FOLDER_NOT_FOUND = "folder.not.found";
    public static final String KEY_FOLDER_NAME_EXISTED = "folder.name.existed";
    public static final String KEY_FOLDER_CREATED = "folder.created";

    public final String key;

    SinoVietnameseMessageEnum(String key) {
        this.key = key;
    }
}
