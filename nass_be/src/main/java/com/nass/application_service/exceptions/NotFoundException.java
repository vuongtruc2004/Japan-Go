package com.nass.application_service.exceptions;

import com.nass.application_service.exceptions.base.BaseException;

public class NotFoundException extends BaseException {
    public NotFoundException(String devMessage, String userMessage, String extraMessage) {
        super(devMessage, userMessage, extraMessage);
    }

    public NotFoundException(String devMessage, String userMessage) {
        super(devMessage, userMessage);
    }

    public NotFoundException(String devMessage) {
        super(devMessage);
    }
}
