package org.japan.exception.kanji;

import org.japan.exception.BaseException;

public class KanjiException extends BaseException {
    public KanjiException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
