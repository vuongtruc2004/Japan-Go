package com.nass.application_service.exceptions;

import com.nass.application_service.exceptions.base.BaseException;

public class BadRequestException extends BaseException {
    public BadRequestException(String devMessage, String userMessage, String extraMessage) {
        super(devMessage, userMessage, extraMessage);
    }

    public BadRequestException(String devMessage, String userMessage) {
        super(devMessage, userMessage);
    }

    public BadRequestException(String devMessage) {
        super(devMessage);
    }
}
