package com.japan_go_be.features.lesson.exception;

import com.japan_go_be.common.exception.BaseException;

public class KanjiLessonException extends BaseException {
    public KanjiLessonException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
