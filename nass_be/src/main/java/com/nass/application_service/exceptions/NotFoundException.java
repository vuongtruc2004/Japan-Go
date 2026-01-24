package com.nass.application_service.exceptions;

import com.nass.application_service.exceptions.base.BaseException;

public class NotFoundException extends BaseException {
    public NotFoundException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
