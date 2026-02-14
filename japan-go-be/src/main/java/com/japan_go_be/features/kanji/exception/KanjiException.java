package com.japan_go_be.features.kanji.exception;

import com.japan_go_be.common.exception.BaseException;

public class KanjiException extends BaseException {
    public KanjiException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
