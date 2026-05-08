package org.japan.exception.lesson;

import org.japan.exception.BaseException;

public class LessonException extends BaseException {
    public LessonException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
