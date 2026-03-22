package com.japan_go_be.business.exceptions.lesson;

import com.japan_go_be.business.exceptions.BaseException;

public class LessonException extends BaseException {
    public LessonException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
