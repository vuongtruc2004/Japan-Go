package com.japan_go_be.business.exception.lesson;

import com.japan_go_be.business.exception.BaseException;

public class GrammarLessonException extends BaseException {
    public GrammarLessonException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
