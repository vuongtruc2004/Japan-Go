package org.japan.exception;

public class BadRequestException extends BaseException {
    public BadRequestException(String devMessage, String userMessage) {
        super(devMessage, userMessage);
    }
}
