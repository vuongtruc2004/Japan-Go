package com.nass.application_service.exceptions.lesson;

import com.nass.application_service.exceptions.base.BaseException;

public class GrammarLessonException extends BaseException {
    public GrammarLessonException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
