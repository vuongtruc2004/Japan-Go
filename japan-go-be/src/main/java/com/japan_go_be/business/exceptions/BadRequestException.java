package com.japan_go_be.business.exceptions;

public class BadRequestException extends BaseException {
    public BadRequestException(String devMessage, String userMessage) {
        super(devMessage, userMessage);
    }
}
