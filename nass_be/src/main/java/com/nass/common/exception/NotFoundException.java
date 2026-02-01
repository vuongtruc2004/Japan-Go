package com.nass.common.exception;

public class NotFoundException extends BaseException {
    public NotFoundException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
