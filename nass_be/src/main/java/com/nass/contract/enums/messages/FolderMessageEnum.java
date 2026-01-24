package com.nass.contract.enums.messages;

public enum FolderMessageEnum {
    FOLDER_NOT_FOUND("folder.not.found"),
    FOLDER_NAME_EXISTED("folder.name.existed"),
    FOLDER_CREATED("folder.created"),
    FOLDER_GET_ALL("folder.get.all");

    // dùng cho devMessage và clientMessage ở controller
    public static final String KEY_FOLDER_NOT_FOUND = "folder.not.found";
    public static final String KEY_FOLDER_NAME_EXISTED = "folder.name.existed";
    public static final String KEY_FOLDER_CREATED = "folder.created";
    public static final String KEY_FOLDER_GET_ALL = "folder.get.all";

    public final String key;

    FolderMessageEnum(String key) {
        this.key = key;
    }
}
