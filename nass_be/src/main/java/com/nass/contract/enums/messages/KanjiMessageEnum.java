package com.nass.contract.enums.messages;

public enum KanjiMessageEnum {
    KANJI_UNICODE_EXISTED("kanji.unicode.existed"),
    FOLDER_NAME_EXISTED("folder.name.existed"),
    FOLDER_CREATED("folder.created");

    // dùng cho devMessage và clientMessage ở controller
    public static final String KEY_FOLDER_NOT_FOUND = "folder.not.found";
    public static final String KEY_FOLDER_NAME_EXISTED = "folder.name.existed";
    public static final String KEY_FOLDER_CREATED = "folder.created";

    public final String key;

    KanjiMessageEnum(String key) {
        this.key = key;
    }
}
