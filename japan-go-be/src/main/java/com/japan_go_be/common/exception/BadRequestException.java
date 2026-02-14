package com.japan_go_be.common.exception;

public class BadRequestException extends BaseException {
    public BadRequestException(String devMessage, String userMessage) {
        super(devMessage, userMessage);
    }
}
