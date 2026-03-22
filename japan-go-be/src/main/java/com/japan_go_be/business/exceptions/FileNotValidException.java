package com.japan_go_be.business.exceptions;

public class FileNotValidException extends BaseException {
    public FileNotValidException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
