package org.japan.exceptions.lesson;

import org.japan.exceptions.BaseException;

public class LessonException extends BaseException {
    public LessonException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
