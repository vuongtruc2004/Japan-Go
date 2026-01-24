package com.nass.application_service.exceptions;

import com.nass.application_service.exceptions.base.BaseException;

public class KanjiException extends BaseException {
    public KanjiException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
