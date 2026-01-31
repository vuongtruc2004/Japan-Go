package com.nass.application_service.exceptions.lesson;

import com.nass.application_service.exceptions.base.BaseException;

public class KanjiLessonException extends BaseException {
    public KanjiLessonException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
