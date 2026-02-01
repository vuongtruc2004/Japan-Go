package com.nass.business.lesson.exception;

import com.nass.common.exception.BaseException;

public class KanjiLessonException extends BaseException {
    public KanjiLessonException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
