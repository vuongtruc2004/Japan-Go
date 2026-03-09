package com.japan_go_be.business.exception;

public class BadRequestException extends BaseException {
    public BadRequestException(String devMessage, String userMessage) {
        super(devMessage, userMessage);
    }
}
