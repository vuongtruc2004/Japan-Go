package com.japan_go_be.features.lesson.exception;

import com.japan_go_be.common.exception.BaseException;

public class LessonException extends BaseException {
    public LessonException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
