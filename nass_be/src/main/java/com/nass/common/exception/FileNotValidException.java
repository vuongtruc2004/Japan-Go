package com.nass.common.exception;

public class FileNotValidException extends BaseException {
    public FileNotValidException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
