package com.japan_go_be.business.exception.kanji;

import com.japan_go_be.business.exception.BaseException;

public class KanjiException extends BaseException {
    public KanjiException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
