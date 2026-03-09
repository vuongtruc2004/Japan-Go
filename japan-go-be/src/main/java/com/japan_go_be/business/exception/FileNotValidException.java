package com.japan_go_be.business.exception;

public class FileNotValidException extends BaseException {
    public FileNotValidException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
