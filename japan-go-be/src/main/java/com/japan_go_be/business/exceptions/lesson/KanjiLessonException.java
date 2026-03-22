package com.japan_go_be.business.exceptions.lesson;

import com.japan_go_be.business.exceptions.BaseException;

public class KanjiLessonException extends BaseException {
    public KanjiLessonException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
