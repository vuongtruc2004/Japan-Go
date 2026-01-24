package com.nass.application_service.exceptions;

import com.nass.application_service.exceptions.base.BaseException;

public class FolderException extends BaseException {
    public FolderException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
