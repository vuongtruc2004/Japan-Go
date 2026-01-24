package com.nass.application_service.exceptions;

import com.nass.application_service.exceptions.base.BaseException;

public class FileNotValidException extends BaseException {
    public FileNotValidException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
