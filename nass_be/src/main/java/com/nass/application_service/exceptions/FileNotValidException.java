package com.nass.application_service.exceptions;

import com.nass.application_service.exceptions.base.BaseException;

public class FileNotValidException extends BaseException {
    public FileNotValidException(String devMessage, String userMessage, String extraMessage) {
        super(devMessage, userMessage, extraMessage);
    }

    public FileNotValidException(String devMessage, String userMessage) {
        super(devMessage, userMessage);
    }

    public FileNotValidException(String devMessage) {
        super(devMessage);
    }
}
