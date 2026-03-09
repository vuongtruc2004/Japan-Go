package com.japan_go_be.business.exception.lesson;

import com.japan_go_be.business.exception.BaseException;

public class KanjiLessonException extends BaseException {
    public KanjiLessonException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
