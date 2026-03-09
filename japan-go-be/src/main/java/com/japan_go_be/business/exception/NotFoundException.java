package com.japan_go_be.business.exception;

public class NotFoundException extends BaseException {
    public NotFoundException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
