package com.japan_go_be.business.exceptions.kanji;

import com.japan_go_be.business.exceptions.BaseException;

public class KanjiException extends BaseException {
    public KanjiException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
