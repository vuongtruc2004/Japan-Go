package com.japan_go_be.business.exception.lesson;

import com.japan_go_be.business.exception.BaseException;

public class LessonException extends BaseException {
    public LessonException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
