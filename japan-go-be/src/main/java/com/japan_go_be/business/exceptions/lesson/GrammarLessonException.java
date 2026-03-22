package com.japan_go_be.business.exceptions.lesson;

import com.japan_go_be.business.exceptions.BaseException;

public class GrammarLessonException extends BaseException {
    public GrammarLessonException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
