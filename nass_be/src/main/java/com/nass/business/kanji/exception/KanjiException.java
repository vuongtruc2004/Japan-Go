package com.nass.business.kanji.exception;

import com.nass.common.exception.BaseException;

public class KanjiException extends BaseException {
    public KanjiException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
